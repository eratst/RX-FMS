package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.LieCubas;
import com.pcitc.fms.bll.itf.LieCubasService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.dal.dao.LieCubasDao;
import com.pcitc.fms.dal.pojo.CommunityArea;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class LieCubasServiceImpl implements LieCubasService {
	@Autowired
	private LieCubasDao lieCubasDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<LieCubas> getPageLieCubas(com.pcitc.fms.service.model.LieCubas lieCubasModel, 
			Pageable pageable) throws Exception {
		MyPageImpl lieCubasPage=null;
		Pager<LieCubas> pageData = new Pager<>();
		try{
			lieCubasPage = lieCubasDao.getLieCubas(lieCubasModel, pageable);
		}catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		List<LieCubas> lieCubasEntityList = ObjectConverter.listConverter(lieCubasPage.getContent(),
				LieCubas.class);
		pageData.setContent(lieCubasEntityList);
		pageData.setFirst(lieCubasPage.isFirst());
		pageData.setLast(lieCubasPage.isLast());
		pageData.setNumber(lieCubasPage.getNumber());
		pageData.setNumberOfElements(lieCubasPage.getNumberOfElements());
		pageData.setSize(lieCubasPage.getSize());
		pageData.setSort(lieCubasPage.getSort());
		pageData.setTotalElements(lieCubasPage.getCount());
		pageData.setTotalPages(lieCubasPage.getTotalPages());
		return pageData;
	}

	@Override
	public List getLieCubaDegree(DataDegree model) throws BusinessException {
		List<DataDegree> LieCubaDegreeList =new ArrayList<DataDegree>();
		
		List<Object> list=new ArrayList<Object>();
		if(StringUtils.isNotEmpty(model.getRentCode())){
			//--租户过滤
			RentCondition<CommunityArea> rentCondition = new RentCondition<CommunityArea>();
			List<String> rentOrgCodes = rentCondition.getRentOrgCodeCondition(model.getRentCode(),model.getBizCode());
			//--租户过滤
			list= lieCubasDao.findLieCubaDegree(rentOrgCodes);
		}else{
			list=lieCubasDao.findLieCubaDegrees();
		}
	
		
		for (Object object : list) {
			Object[] obj=(Object[]) object;
			DataDegree dataDegree = new DataDegree();
			BigDecimal big=(BigDecimal) obj[0];
			BigDecimal big1=(BigDecimal) obj[1];
			BigDecimal big2=(BigDecimal) obj[2];
			BigDecimal big3=(BigDecimal) obj[3];
			String str=(String) obj[4];
			String str1=(String) obj[5];
			dataDegree.setAmount(big.longValue());
			dataDegree.setInterpolateV(big1.doubleValue());
			dataDegree.setDegree(big2.longValue());
			dataDegree.setTankId(big3.longValue());
			dataDegree.setNodeCode(str);
			dataDegree.setNodeName(str1);
			LieCubaDegreeList.add(dataDegree);
		}
		
		return LieCubaDegreeList;
	}

}
