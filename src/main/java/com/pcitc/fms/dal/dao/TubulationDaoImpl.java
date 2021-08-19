/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TubulationDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.dal.pojo.Outlet;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.fms.dal.pojo.Tubulation;
import com.pcitc.imp.common.exception.BusiException;



/**
 * The type Tubulation dao.
 */
public class TubulationDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Get tees by model page.
	 *
	 * @param tubulationModel the tubulation model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl getTeesByModel(com.pcitc.fms.service.model.Tubulation tubulationModel,Pageable pageable) throws BusiException{
		Map<String ,Object> mapParameter = new HashedMap();
		
		final String Tubulation = "select new Tubulation(a.nodeId,a.nodeCode,b.crtUserCode, b.crtUserName, b.crtDate, "
                + "b.mntUserCode, b.mntUserName, b.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,"
                + "b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName,d.areaCode,d.name,d.shortName,a.sortNum,b.nodeLevel,b.nodeModel) " +
                "from Tubulation a,NodeDictionary b,NodeType c,AreaDictionary d,TPmOrg e   "
                + "where a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId and e.orgId = d.factoryId ";
		String tuba_count = "select count(1) from Tubulation a,NodeDictionary b,NodeType c,AreaDictionary d  ,TPmOrg e  where "
				+ " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId and e.orgId = d.factoryId ";
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(Tubulation);
		StringBuilder countSql = new StringBuilder();
		countSql.append(tuba_count);
		if(null != tubulationModel.getNodeCode() && !StringUtils.isEmpty(tubulationModel.getNodeCode())){
			dataSql.append( " and a.nodeCode = :nodeCode");
			countSql.append( " and a.nodeCode = :nodeCode");
			mapParameter.put("nodeCode", tubulationModel.getNodeCode());
		}
		if(null != tubulationModel.getAreaCode() && !StringUtils.isEmpty(tubulationModel.getAreaCode())){
			dataSql.append( " and d.areaCode = :areaCode");
			countSql.append( " and d.areaCode = :areaCode");
			mapParameter.put("areaCode", tubulationModel.getAreaCode());
		}
		if(null != tubulationModel.getOrgCode() && !StringUtils.isEmpty(tubulationModel.getOrgCode())){
			dataSql.append( " and e.orgCode = :orgCode");
			countSql.append( " and e.orgCode = :orgCode");
			mapParameter.put("orgCode", tubulationModel.getOrgCode());
		}
		if(null != tubulationModel.getNodeName() && !StringUtils.isEmpty(tubulationModel.getNodeName())){
			dataSql.append(" and b.nodeName like :nodeName");
			countSql.append(" and b.nodeName like :nodeName");
			mapParameter.put("nodeName", "%"+tubulationModel.getNodeName()+"%");
		}
		if(null != tubulationModel.getNodeAlias() && !StringUtils.isEmpty(tubulationModel.getNodeAlias())){
			dataSql.append(" and b.nodeAlias like :nodeAlias") ;
			countSql.append(" and b.nodeAlias like :nodeAlias") ;
			mapParameter.put("nodeAlias", "%"+tubulationModel.getNodeAlias()+"%");
		}
		if(null != tubulationModel.getInUse() && !StringUtils.isEmpty(String.valueOf(tubulationModel.getInUse()))){
			dataSql.append( " and b.dataStatus  = :inUse");
			countSql.append( " and b.dataStatus  = :inUse");
			mapParameter.put("inUse", tubulationModel.getInUse());
		}

		if (null != tubulationModel && null != tubulationModel.getNodeCodes() && tubulationModel.getNodeCodes().size() > 0) {
			dataSql.append(" and a.nodeCode in :codes");
			countSql.append(" and a.nodeCode in :codes");
			mapParameter.put("codes", tubulationModel.getNodeCodes());
		}

		if (null != tubulationModel.getAreaCodes() && tubulationModel.getAreaCodes().size() > 0) {
			dataSql.append(" and d.areaCode in :areaCodes");
			countSql.append(" and d.areaCode in :areaCodes");
			mapParameter.put("areaCodes", tubulationModel.getAreaCodes());
		}
		if (StringUtils.isNotEmpty(tubulationModel.getOrderby())) {
			String value = SortParam.getSortParam(Tubulation.class, tubulationModel.getOrderby());
			dataSql.append(value);
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(tubulationModel.getRentCode())){
			RentCondition<Tubulation> rentCondition = new RentCondition<Tubulation>();
			String field=" e.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(tubulationModel.getRentCode(),tubulationModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤

		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		
		for (String parameter : mapParameter.keySet()) {
			dataQuery.setParameter(parameter, mapParameter.get(parameter));
			countQuery.setParameter(parameter, mapParameter.get(parameter));
		}
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			dataQuery.setFirstResult( tubulationModel.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.Tubulation> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
