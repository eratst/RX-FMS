package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Rent;
import com.pcitc.fms.bll.itf.RentService;
import com.pcitc.fms.bll.itf.TPmOrgService;
import com.pcitc.fms.common.CacheRentInfo;
import com.pcitc.fms.common.RedisUtil;
import com.pcitc.fms.dal.dao.BizOrgMainDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.RentDao;
import com.pcitc.fms.dal.pojo.TPmBizOrgMain;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;
import redis.clients.jedis.Jedis;

@Service
public class RentServiceImpl implements RentService{
	@Autowired
	private RentDao rentDao;
	
	@Autowired
	private DbPrimaryIdDao dbPrimaryIdDao;
	
	@Autowired
	private BizOrgMainDao bizOrgMainDao;
	
	@Autowired
	private TPmOrgService tPmOrgService;
	
	@Override
	public Pager<Rent> getRents(com.pcitc.fms.service.model.Rent rent, Pageable pageable) throws BusinessException {

		Pager<Rent> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.Rent> properties = null;
		List<Rent> EntityList = new ArrayList<>();
		try {
			properties = rentDao.findRents(rent, pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(), Rent.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
			throw new BusinessException("", "", e.getMessage());
		}
		pageData.setContent(EntityList);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getTotalElements());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rent> addRent(List<Rent> entityList) throws Exception {
		List<Rent> result = new ArrayList<>();
		if (entityList != null && !entityList.isEmpty()) {
			if (entityList.get(0) != null) {
				com.pcitc.fms.dal.pojo.Rent rent = rentDao
						.getRentCode(entityList.get(0).getRentCode());
				com.pcitc.fms.dal.pojo.Rent rent1 = rentDao
						.getRentName(entityList.get(0).getRentName());
				if (null != rent || null != rent1) {
					result.addAll(entityList);
				} else {
					List<com.pcitc.fms.dal.pojo.Rent> pojos = ObjectConverter.listConverter(entityList,
							com.pcitc.fms.dal.pojo.Rent.class);
					List<BigDecimal> orgDateSeqId = dbPrimaryIdDao.getSeqId(pojos.size(), "T_PM_RENT");
					
					for(int i=0;i<entityList.size();i++){
						pojos.get(i).setRentId(orgDateSeqId.get(i).longValue());
						pojos.get(i).setCrtUserCode("1");
						pojos.get(i).setCrtUserName("1");
						pojos.get(i).setCrtDate(new Date());
						pojos.get(i).setMntUserCode("1");
						pojos.get(i).setMntUserName("1");
						pojos.get(i).setMntDate(new Date());
					}
					List<com.pcitc.fms.dal.pojo.Rent> pojoResns = rentDao.save(pojos);
					result = ObjectConverter.listConverter(pojoResns, Rent.class);
				}
			}
		}
		
		if (System.getenv("USE_REDIS")==null || !System.getenv("USE_REDIS").equals("1")) {
			//创建该租户的标准业务域
			creatStandardBiz(result);
			
			//将新租户及标准业务域加入缓存
			Map<String, Map<String, List<Map<String, String>>>> cache = CacheRentInfo.get();
			Map<String, List<Map<String, String>>> maps = new HashMap<>();
			
			maps.put(entityList.get(0).getRentCode()+"_SYSTEM_STANDARD_BIZ", new ArrayList<Map<String, String>>());
			cache.put(entityList.get(0).getRentCode(), maps);
		} else {
			Jedis jedis = RedisUtil.getJedis();
			String rentStr = jedis.get("rents");
			jedis.set("rents", rentStr+entityList.get(0).getRentCode()+",");
			jedis.set("bizs:"+entityList.get(0).getRentCode(), entityList.get(0).getRentCode()+"_SYSTEM_STANDARD_BIZ");
		}
		
		return result;
	}
	
	private void creatStandardBiz(List<Rent> entityList) {
		List<BigDecimal> primaryIds = dbPrimaryIdDao.getSeqId(entityList.size(), "T_PM_BIZORGMAIN"); 
		List<TPmBizOrgMain> bizorgMains = new ArrayList<>();
		for (int i=0;i<entityList.size();i++) {
			TPmBizOrgMain bizorgMain = new TPmBizOrgMain();
			bizorgMain.setBizId(primaryIds.get(i).longValue());
			bizorgMain.setRentId(entityList.get(i).getRentId());
			bizorgMain.setBizCode(entityList.get(i).getRentCode()+"_SYSTEM_STANDARD_BIZ");
			bizorgMain.setBizName(entityList.get(i).getRentName()+"标准业务域");
			bizorgMain.setBizAlias(entityList.get(i).getRentName()+"标准业务域");
			bizorgMain.setCrtUserCode("1");
			bizorgMain.setCrtUserName("1");
			bizorgMain.setCrtDate(new Date());
			bizorgMain.setMntUserCode("1");
			bizorgMain.setMntUserName("1");
			bizorgMain.setMntDate(new Date());
			bizorgMain.setInUse(1);
			bizorgMain.setDes("标准业务域");
			bizorgMain.setIsStandard(1);
			bizorgMain.setSortNum(1);
			bizorgMain.setVersion(1);
			bizorgMains.add(bizorgMain);
		}
		bizOrgMainDao.save(bizorgMains);
	}
	
	@Override
	public void deleteByRentCode(String rentCode) throws Exception {
//		com.pcitc.fms.dal.pojo.Rent rent = rentDao.getRentCode(rentCode);
//		if (null == rent) {
//			throw new BusinessException("", "", "该租户不能存在，无法删除！");
//		}
		rentDao.deleteByRentCode(rentCode);
	}
}
