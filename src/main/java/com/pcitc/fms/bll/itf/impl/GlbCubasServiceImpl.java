package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.DataDegree;
import com.pcitc.fms.bll.entity.GlbCubas;
import com.pcitc.fms.bll.itf.GlbCubasService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.dal.dao.GlbCubasDao;
import com.pcitc.fms.dal.pojo.CommunityArea;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class GlbCubasServiceImpl implements GlbCubasService {
	
	@Autowired
	private GlbCubasDao glbCubasDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<GlbCubas> getPageGlbCubas(com.pcitc.fms.service.model.GlbCubas glbCubasModel, Pageable pageable)
			throws Exception {
		Pager<GlbCubas> pageData = new Pager<>();
		MyPageImpl glbCubasPage=null;
		try{
			glbCubasPage = glbCubasDao.findGlbCubas(glbCubasModel, pageable);
		 }catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		List<GlbCubas> glbCubasEntityList = ObjectConverter.listConverter(glbCubasPage.getContent(),
				GlbCubas.class);
		
		pageData.setContent(glbCubasEntityList);
		pageData.setFirst(glbCubasPage.isFirst());
		pageData.setLast(glbCubasPage.isLast());
		pageData.setNumber(glbCubasPage.getNumber());
		pageData.setNumberOfElements(glbCubasPage.getNumberOfElements());
		pageData.setSize(glbCubasPage.getSize());
		pageData.setSort(glbCubasPage.getSort());
		pageData.setTotalElements(glbCubasPage.getCount());
		pageData.setTotalPages(glbCubasPage.getTotalPages());
		
		return pageData;
	}
	@SuppressWarnings("unused")
	public List<DataDegree> getGlbCubaDegree(com.pcitc.fms.service.model.DataDegree model) throws BusinessException{
		List<DataDegree> GlbCubaDegreeList =new ArrayList<DataDegree>();
		
		List<Object> list=new ArrayList<Object>();
		if(StringUtils.isNotEmpty(model.getRentCode())){
			//--租户过滤
			RentCondition<DataDegree> rentCondition = new RentCondition<DataDegree>();
			List<String> rentOrgCodes = rentCondition.getRentOrgCodeCondition(model.getRentCode(),model.getBizCode());
			//--租户过滤
			list= glbCubasDao.findGlbCubaDegree(rentOrgCodes);
		}else{
			list=glbCubasDao.findGlbCubaDegrees();
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
			GlbCubaDegreeList.add(dataDegree);
		}
		
		return GlbCubaDegreeList;
	}

}
