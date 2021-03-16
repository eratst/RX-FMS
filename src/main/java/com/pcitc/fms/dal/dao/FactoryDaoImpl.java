/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: FactoryDaoImpl
 * Date:18-3-9 上午8:34
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

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

import com.pcitc.fms.dal.pojo.Factory;
import com.pcitc.fms.service.model.FactoryModelStr;

/**
 * The type Factory dao.
 */
@Service
public class FactoryDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find factories page.
	 *
	 * @param modelStr the model str
	 * @param pageable the pageable
	 * @return the page
	 */
	@SuppressWarnings("unchecked")
	public Page<Factory> findFactories(FactoryModelStr modelStr, Pageable pageable){
		
		String countBase = "select count(1) from Factory t where 1 = 1";
		StringBuffer countSql = new StringBuffer();
		countSql.append(countBase);
		
		Map<String ,Object> parameterMap = new HashedMap();
		//List<Factory> factoryPojoList = new ArrayList<>();
		StringBuilder dataSql = new StringBuilder();
		dataSql.append("select t from Factory t where 1 = 1");
		if(null != modelStr.getFactoryId() && !StringUtils.isEmpty(String.valueOf(modelStr.getFactoryId()))){
			dataSql.append(" and t.factoryId = :factoryId ");
			countSql.append(" and t.factoryId = :factoryId ");
			parameterMap.put("factoryId ", modelStr.getFactoryId());
		}
		if(null != modelStr.getBusinessType() && !StringUtils.isEmpty(modelStr.getBusinessType())){
			dataSql.append(" and t.businessType = :businessType");
			countSql.append(" and t.businessType = :businessType");
			parameterMap.put("businessType", modelStr.getBusinessType());
		}
		if(null != modelStr.getEnabled() && !StringUtils.isEmpty(String.valueOf(modelStr.getEnabled()))){
			dataSql.append( " and t.enabled = :enabled");
			countSql.append( " and t.enabled = :enabled");
			parameterMap.put("enabled", Integer.parseInt(modelStr.getEnabled()));
		}
		if(null != modelStr.getName() && !StringUtils.isEmpty(modelStr.getName())){
			dataSql.append( " and t.name like :name");
			countSql.append( " and t.name like :name");
			parameterMap.put("name", "%"+modelStr.getName()+"%");
		}
		if(null != modelStr.getShortName() && !StringUtils.isEmpty(modelStr.getShortName())){
			dataSql.append( " and t.shortName like :shortName");
			countSql.append( " and t.shortName like :shortName");
			parameterMap.put("shortName", "%"+modelStr.getShortName()+"%");
		}
		if(null != modelStr.getCodeList() && modelStr.getCodeList().size() > 0){
			dataSql.append( " and t.code in :codes");
			countSql.append( " and t.code in :codes");
			parameterMap.put("codes", modelStr.getCodeList());
		}
		if(null != modelStr.getIdList() && modelStr.getIdList().size() > 0){
			dataSql.append( " and t.factoryId in :ids");
			countSql.append( " and t.factoryId in :ids");
			parameterMap.put("ids", modelStr.getIdList());
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = (long) countQuery.getResultList().get(0);
		int skip = modelStr.getSkip();
		dataQuery.setFirstResult(skip);
		if(null != pageable){
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		return new PageImpl<com.pcitc.fms.dal.pojo.Factory>(dataQuery.getResultList(), pageable, count);
		
	}	
}
