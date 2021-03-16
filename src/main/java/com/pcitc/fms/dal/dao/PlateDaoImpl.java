/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PlateDaoImpl
 * Date:18-3-9 上午8:34
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.dal.pojo.Plate;
import com.pcitc.fms.dal.pojo.ProduceFactory;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: PlateDaoImpl Description:TODO
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月16日
 */
public class PlateDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Gets plates.
	 *
	 * @param plateModel the plate model
	 * @param pageable the pageable
	 * @return plates
	 * @Title: getPlates
	 * @Description: TODO
	 * @date 2017年6月16日
	 * @return: void
	 * @author zhenqiang.zhao
	 * @throws BusiException 
	 */
	public MyPageImpl getPlates(com.pcitc.fms.service.model.Plate plateModel, Pageable pageable) throws BusiException {

		 String Plate = "select new Plate(a.nodeId,a.nodeCode,a.thickness,a.diameter,b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, b.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName, a.sortNum, d.areaCode, d.name, d.shortName) from Plate a,NodeDictionary b,NodeType c,AreaDictionary d,TPmOrg org where "
				  + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId and d.factoryId=org.orgId ";
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(Plate);
		String plates_count = "select count(1) from Plate a,NodeDictionary b,NodeType c,AreaDictionary d,TPmOrg org where "
				+ " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId and d.factoryId=org.orgId ";
		
		StringBuilder countSql = new StringBuilder();
		countSql.append(plates_count);
		
		if(null != plateModel.getNodeCode() && !StringUtils.isEmpty(plateModel.getNodeCode())){
			dataSql.append( " and a.nodeCode = :nodeCode");
			countSql.append( " and a.nodeCode = :nodeCode");
			mapParameter.put("nodeCode", plateModel.getNodeCode());
		}
		if(null != plateModel.getNodeName() && !StringUtils.isEmpty(plateModel.getNodeName())){
			dataSql.append(" and b.nodeName like :nodeName");
			countSql.append(" and b.nodeName like :nodeName");
			mapParameter.put("nodeName", "%"+plateModel.getNodeName()+"%");
		}
		if(null != plateModel.getNodeAlias() && !StringUtils.isEmpty(plateModel.getNodeAlias())){
			dataSql.append(" and b.nodeAlias like :nodeAlias") ;
			countSql.append(" and b.nodeAlias like :nodeAlias") ;
			mapParameter.put("nodeAlias", "%"+plateModel.getNodeAlias()+"%");
		}
		if(null != plateModel.getInUse() && !StringUtils.isEmpty(String.valueOf(plateModel.getInUse()))){
			dataSql.append( " and b.dataStatus  = :inUse");
			countSql.append( " and b.dataStatus  = :inUse");
			mapParameter.put("inUse", plateModel.getInUse());
		}

		if (null != plateModel && null != plateModel.getNodeCodes() && plateModel.getNodeCodes().size() > 0) {
			dataSql.append(" and a.nodeCode in :codes");
			countSql.append(" and a.nodeCode in :codes");
			mapParameter.put("codes", plateModel.getNodeCodes());
		}

		if (null != plateModel.getAreaCodes() && plateModel.getAreaCodes().size() > 0) {
			dataSql.append(" and d.areaCode in :areaCodes");
			countSql.append(" and d.areaCode in :areaCodes");
			mapParameter.put("areaCodes", plateModel.getAreaCodes());
		}
		if (null != plateModel.getAreaCode() && !StringUtils.isEmpty(plateModel.getAreaCode())) {
			dataSql.append(" and d.areaCode = :areaCode");
			countSql.append(" and d.areaCode = :areaCode");
			mapParameter.put("areaCode", plateModel.getAreaCode());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(plateModel.getRentCode())){
			RentCondition<SamplePoint> rentCondition = new RentCondition<SamplePoint>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(plateModel.getRentCode(),plateModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(plateModel.getOrderby())) {
			String value = SortParam.getSortParam(Plate.class, plateModel.getOrderby());
			dataSql.append(value);
		}
			
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : mapParameter.keySet()) {
			dataQuery.setParameter(parameter, mapParameter.get(parameter));
			countQuery.setParameter(parameter, mapParameter.get(parameter));
		}
		
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			dataQuery.setFirstResult(plateModel.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());

		}
		List<com.pcitc.fms.dal.pojo.Plate> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;	}
}
