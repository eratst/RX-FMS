/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: SiloDaoImpl
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
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.dal.pojo.Silo;
import com.pcitc.fms.dal.pojo.Tank;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.imp.common.exception.BusiException;

/**
 * The type Silo dao.
 */
@Service
public class SiloDaoImpl{

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find page silos page.
	 *
	 * @param modelStr the model str
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findPageSilos(com.pcitc.fms.service.model.Silo model,Pageable pageable) throws BusiException{
		Map<String ,Object> mapParameter = new HashedMap();
		final String Silo = "select new Silo(a.nodeId,a.nodeCode,a.siloTypeId,a.cubage,a.siloHgt,a.maxSiloHgt,a.minSiloHgt,a.maxSiloStoarge,a.minSiloStoarge,b.crtUserCode, b.crtUserName, b.crtDate, b.mntUserCode, b.mntUserName, b.mntDate,b.des,b.areaId,b.nodeLongitude,b.nodeLatitude,b.nodeTypeId,b.dataStatus,b.nodeAltitude,b.nodeName,b.nodeAlias,c.nodeTypeName,d.siloTypeName,d.siloTypeCode,e.areaCode,e.name,e.shortName,a.sortNum) from Silo a,NodeDictionary b,NodeType c,SiloType d,AreaDictionary e,TPmOrg org where "
			     + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.siloTypeId = d.siloTypeId and b.areaId = e.areaDictionaryId and e.factoryId=org.orgId ";
		String silo_count = "select count(1)from Silo a,NodeDictionary b,NodeType c,SiloType d,AreaDictionary e,TPmOrg org where " 
						     + " a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and a.siloTypeId = d.siloTypeId and b.areaId = e.areaDictionaryId and e.factoryId=org.orgId ";
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(Silo);
		
		StringBuilder countSql = new StringBuilder();
		countSql.append(silo_count);
		
		if(null != model.getNodeCode() && !StringUtils.isEmpty(model.getNodeCode())){
			dataSql.append( " and a.nodeCode = :nodeCode");
			countSql.append( " and a.nodeCode = :nodeCode");
			mapParameter.put("nodeCode", model.getNodeCode());
		}
		if(null != model.getAreaCode() && !StringUtils.isEmpty(model.getAreaCode())){
			dataSql.append( " and e.areaCode = :areaCode");
			countSql.append( " and e.areaCode = :areaCode");
			mapParameter.put("areaCode", model.getAreaCode());
		}
		if(null != model.getNodeName() && !StringUtils.isEmpty(model.getNodeName())){
			dataSql.append(" and b.nodeName like :nodeName");
			countSql.append(" and b.nodeName like :nodeName");
			mapParameter.put("nodeName", "%"+model.getNodeName()+"%");
		}
		if(null != model.getNodeAlias() && !StringUtils.isEmpty(model.getNodeAlias())){
			dataSql.append(" and b.nodeAlias like :nodeAlias") ;
			countSql.append(" and b.nodeAlias like :nodeAlias") ;
			mapParameter.put("nodeAlias",  "%"+model.getNodeAlias()+"%");
		}
		if(null != model.getInUse() && !StringUtils.isEmpty(String.valueOf(model.getInUse()))){
			dataSql.append( " and b.dataStatus  = :inUse");
			countSql.append( " and b.dataStatus  = :inUse");
			mapParameter.put("inUse", model.getInUse());
		}

		if (null != model && null != model.getNodeCodes() && model.getNodeCodes().size() > 0) {
			dataSql.append(" and a.nodeCode in :codes");
			countSql.append(" and a.nodeCode in :codes");
			mapParameter.put("codes", model.getNodeCodes());
		}
		
		if (null != model && null != model.getSiloTypeCode() && !StringUtils.isEmpty(model.getSiloTypeCode())) {
			dataSql.append(" and d.siloTypeCode = :siloTypeCode");
			countSql.append(" and d.siloTypeCode = :siloTypeCode");
			mapParameter.put("siloTypeCode", model.getSiloTypeCode());
		}

		if (null != model.getAreaCodes() && model.getAreaCodes().size() > 0) {
			dataSql.append(" and e.areaCode in :areaCodes");
			countSql.append(" and e.areaCode in :areaCodes");
			mapParameter.put("areaCodes", model.getAreaCodes());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<Silo> rentCondition = new RentCondition<Silo>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(model.getRentCode(),model.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(Silo.class, model.getOrderby());
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
			dataQuery.setFirstResult( model.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());

		}
		
		List<com.pcitc.fms.dal.pojo.Silo> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
