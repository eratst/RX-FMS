package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.CacheInfoParam;
import com.pcitc.fms.bll.itf.CacheRentService;
import com.pcitc.fms.bll.itf.TPmOrgService;
import com.pcitc.fms.common.CacheRentInfo;
import com.pcitc.fms.common.RedisUtil;
import com.pcitc.fms.dal.dao.BizOrgMainDao;
import com.pcitc.fms.dal.dao.CacheBizMainDao;
import com.pcitc.fms.dal.dao.CacheRentRepository;
import com.pcitc.fms.dal.dao.RentDao;
import com.pcitc.fms.dal.pojo.CacheBizDtl;
import com.pcitc.fms.dal.pojo.CacheBizMain;
import com.pcitc.fms.dal.pojo.CacheRent;

import redis.clients.jedis.Jedis;

@Service
@Component
public class CacheRentServiceImpl implements CacheRentService {

	@Autowired
	private CacheRentRepository cacheRentRepository;

	@Autowired
	private CacheBizMainDao cacheBizMainDao;

	@Autowired
	private BizOrgMainDao bizOrgMainDao;

	@Autowired
	private TPmOrgService tPmOrgService;

	@Autowired
	private RentDao rentDao;

	public void getCacheInfo() {
		Map<String, Map<String, List<Map<String, String>>>> cache = new HashMap<>();
		List<CacheRent> cacheRents = cacheRentRepository.findAll();

		if (System.getenv("USE_REDIS")!=null && System.getenv("USE_REDIS").equals("1")) {
			Jedis jedis = RedisUtil.getJedis();
			jedis.flushAll();
			setRedis(cacheRents,jedis);
		} else {
			setMap(cacheRents, cache);
			CacheRentInfo.put(cache);
		}

	}

	@Override
	public void getCacheInfoByRentCode(CacheInfoParam cacheInfoParam) {
		
		if (System.getenv("USE_REDIS")==null || !System.getenv("USE_REDIS").equals("1")) {
			String rentCode = null;
			String bizCode = null;
			String orgCode = null;
			String areaCode = null;

			if (cacheInfoParam != null) {
				rentCode = cacheInfoParam.getRentCode();
				bizCode = cacheInfoParam.getBizCode();
				orgCode = cacheInfoParam.getOrgCode();
				areaCode = cacheInfoParam.getAreaCode();
			}

			Map<String, Map<String, List<Map<String, String>>>> oldCaches = CacheRentInfo.get();
			Map<String, Map<String, List<Map<String, String>>>> newCaches = new HashMap<>();
			newCaches.putAll(oldCaches);
			List<String> rentCodes = new ArrayList<>();

			// 只有rentCode
			if (rentCode != null) {
				rentCodes.add(rentCode);
				com.pcitc.fms.dal.pojo.Rent rent = rentDao.getRentByRentCode(rentCode);
				if (rent != null) {
					refreshRent(newCaches, rentCodes, rent.getOrgId());
				}
			}

			if (rentCode == null && orgCode != null) {
				List<CacheRent> rents = cacheRentRepository.findCacheRentByOrgCode(orgCode);
				for (CacheRent rent : rents) {
					rentCodes.add(rent.getRentCode());
				}
				refreshRent(newCaches, rentCodes, null);
			}

			CacheRentInfo.put(newCaches);
		} 
	}

	private void refreshRent(Map<String, Map<String, List<Map<String, String>>>> newCaches, List<String> rentCodes,
			Long orgId) {
		List<CacheRent> cacheRents = cacheRentRepository.findCacheRentByRentCode(rentCodes);
		for (CacheRent cacheRent : cacheRents) {
			Map<String, List<Map<String, String>>> bizMaps = new ConcurrentHashMap<>();
			List<CacheBizMain> bizs = cacheRent.getCacheBizMains();
			for (CacheBizMain biz : bizs) {
				String bizCodeByQuery = biz.getBizCode();
				if (StringUtils.isNotEmpty(bizCodeByQuery)) {

					List<Map<String, String>> orgLists = new ArrayList<>();

					// 判断该业务域是否是系统创建的标准业务域
					if (bizCodeByQuery.equals(rentCodes.get(0) + "_SYSTEM_STANDARD_BIZ")) {

						// 从org表里面取标准树更新缓存

						List<com.pcitc.fms.bll.entity.OrgTree> orgTrees = tPmOrgService.getBranchTrees(orgId);
						if (null != orgId) {
							// 说明租户在标准树上
							for (com.pcitc.fms.bll.entity.OrgTree orgTree : orgTrees) {
								Map<String, String> orgMaps = new ConcurrentHashMap();
								String orgCodeByQuery = orgTree.getOrgCode();
								orgMaps.put(bizCodeByQuery, orgCodeByQuery);
								orgLists.add(orgMaps);
							}
						} else {
							// 说明租户不在标准树上
							Map<String, String> orgMaps = new ConcurrentHashMap();
							orgLists.add(orgMaps);
						}

					} else {

						// 从明细表里面取数据更新缓存
						List<CacheBizDtl> orgs = biz.getCacheBizDtls();
						for (CacheBizDtl org : orgs) {
							Map<String, String> orgMaps = new ConcurrentHashMap();
							String orgCodeByQuery = org.getOrgCode();
							orgMaps.put(bizCodeByQuery, orgCodeByQuery);
							orgLists.add(orgMaps);
						}
					}

					bizMaps.put(bizCodeByQuery, orgLists);

				}
			}
			newCaches.put(cacheRent.getRentCode(), bizMaps);
		}
	}

	private Map<String, Map<String, List<Map<String, String>>>> setMap(List<CacheRent> cacheRents,
			Map<String, Map<String, List<Map<String, String>>>> cache) {
		for (CacheRent cacheRent : cacheRents) {
			List<CacheBizMain> cacheBizMains = cacheRent.getCacheBizMains();
			Map<String, List<Map<String, String>>> cacheBizMainsMap = new HashMap<>();
			for (CacheBizMain cacheBizMain : cacheBizMains) {
				List<CacheBizDtl> cacheBizDtls = cacheBizMain.getCacheBizDtls();
				List<Map<String, String>> cacheBizDtlsList = new ArrayList<>();
				for (CacheBizDtl cacheBizDtl : cacheBizDtls) {
					Map<String, String> innerMap = new HashMap<>();
					innerMap.put(cacheBizMain.getBizCode(), cacheBizDtl.getOrgCode());
					cacheBizDtlsList.add(innerMap);
				}
				cacheBizMainsMap.put(cacheBizMain.getBizCode(), cacheBizDtlsList);
			}
			cache.put(cacheRent.getRentCode(), cacheBizMainsMap);

			// 标准树
			com.pcitc.fms.dal.pojo.Rent rent = rentDao.getRentByRentCode(cacheRent.getRentCode());
			if (rent.getOrgId() != null) {
				com.pcitc.fms.dal.pojo.TPmBizOrgMain tPmBizOrgMain = bizOrgMainDao
						.getBizOrgMainByBizCode(rent.getRentCode() + "_SYSTEM_STANDARD_BIZ");
				if (tPmBizOrgMain != null) {
					List<com.pcitc.fms.bll.entity.OrgTree> orgTrees = tPmOrgService.getBranchTrees(rent.getOrgId());
					List<Map<String, String>> lists = new ArrayList<>();

					for (com.pcitc.fms.bll.entity.OrgTree orgTree : orgTrees) {
						Map<String, String> orgMaps = new HashMap<>();
						orgMaps.put(rent.getRentCode() + "_SYSTEM_STANDARD_BIZ", orgTree.getOrgCode());
						lists.add(orgMaps);
					}
					cacheBizMainsMap.put(rent.getRentCode() + "_SYSTEM_STANDARD_BIZ", lists);
					cache.put(rent.getRentCode(), cacheBizMainsMap);
				}
			}
		}
		return cache;
	}

	private void setRedis(List<CacheRent> cacheRents,Jedis jedis) {
		StringBuilder rentBuilder = new StringBuilder();
		StringBuilder bizBuilder = new StringBuilder();
		StringBuilder orgBuilder = new StringBuilder();
		for (CacheRent cacheRent : cacheRents) {
			List<CacheBizMain> cacheBizMains = cacheRent.getCacheBizMains();
			bizBuilder.setLength(0);
			for (CacheBizMain cacheBizMain : cacheBizMains) {
				List<CacheBizDtl> cacheBizDtls = cacheBizMain.getCacheBizDtls();
				orgBuilder.setLength(0);
				for (CacheBizDtl cacheBizDtl : cacheBizDtls) {
					orgBuilder.append(cacheBizDtl.getOrgCode() + ",");
				}
				jedis.set("orgs:"+cacheRent.getRentCode() + ":" + cacheBizMain.getBizCode(), orgBuilder.toString());
				bizBuilder.append(cacheBizMain.getBizCode() + ",");
			}

			// 标准树
			com.pcitc.fms.dal.pojo.Rent rent = rentDao.getRentByRentCode(cacheRent.getRentCode());
			if (rent.getOrgId() != null) {
				com.pcitc.fms.dal.pojo.TPmBizOrgMain tPmBizOrgMain = bizOrgMainDao
						.getBizOrgMainByBizCode(rent.getRentCode() + "_SYSTEM_STANDARD_BIZ");
				if (tPmBizOrgMain != null) {
					List<com.pcitc.fms.bll.entity.OrgTree> orgTrees = tPmOrgService.getBranchTrees(rent.getOrgId());
					orgBuilder.setLength(0);
					for (com.pcitc.fms.bll.entity.OrgTree orgTree : orgTrees) {
						orgBuilder.append(orgTree.getOrgCode()+",");
					}
					jedis.set("orgs:"+cacheRent.getRentCode() + ":" + rent.getRentCode() + "_SYSTEM_STANDARD_BIZ", orgBuilder.toString());
					if (!bizBuilder.toString().contains(rent.getRentCode() + "_SYSTEM_STANDARD_BIZ")) {
						bizBuilder.append(rent.getRentCode() + "_SYSTEM_STANDARD_BIZ" + ",");
					}
				}
			}
			jedis.set("bizs:"+cacheRent.getRentCode(), bizBuilder.toString());
			rentBuilder.append(cacheRent.getRentCode()+",");
		}
		jedis.set("rents", rentBuilder.toString());
	}

	/**
	 * 缓存租户对应标准业务域
	 */
	// @Override
	// public void getCacheInfoForBiz() {
	// List<CacheRent> cacheRents = cacheRentRepository.findAll();
	// for(CacheRent c:cacheRents){
	// RentAndBiz rab = new RentAndBiz();
	// List<String> bizCodes=new ArrayList<String>();
	// rab.setRentCode(c.getRentCode());
	// List<CacheBizMain> bizMains = c.getCacheBizMains();
	// for(CacheBizMain cb:bizMains){
	//// if(cb.getIsStandard()==1){
	// bizCodes.add(cb.getBizCode());
	//// }
	// }
	// rab.setBizList(bizCodes);
	// //放入缓存。
	// CacheRentAndBizInfo.setStandardBiz(rab);
	// }
	//
	// }

}
