/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PropertyDaoImpl
 * Date:18-3-9 上午8:35
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

/**
 * The type Property dao.
 */
public class PropertyDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Find properties page.
	 *
	 * @param findPropertyModel the find property model
	 * @param pageable the pageable
	 * @return the page
	 */
	@SuppressWarnings("unchecked")
	public Page<com.pcitc.fms.dal.pojo.PropertyMeta> findProperties(com.pcitc.fms.service.model.PropertyMeta findPropertyModel, Pageable pageable) {
		Map<String ,Object> parameterMap = new HashedMap();
		StringBuilder dataSql = new StringBuilder();
		dataSql.append("select t from PropertyMeta t where 1 = 1");
		if (null != findPropertyModel.getEntityCode() && !StringUtils.isEmpty(findPropertyModel.getEntityCode())) {
			dataSql.append(" and t.entityCode = :entityCode");
			parameterMap.put("entityCode", findPropertyModel.getEntityCode());
		}
		if (null != findPropertyModel.getShortName() && !StringUtils.isEmpty(findPropertyModel.getPropertyName())) {
			dataSql.append(" and t.propertyName like :propertyName");
			parameterMap.put("propertyName", "%"+findPropertyModel.getPropertyName()+"%");
		}
		if (null != findPropertyModel.getShortName() && !StringUtils.isEmpty(findPropertyModel.getShortName())) {
			dataSql.append(" and t.shortName like :shortName");
			parameterMap.put("shortName", "%"+findPropertyModel.getShortName()+"%");
		}
		if (null != findPropertyModel.getDataType() && !StringUtils.isEmpty(findPropertyModel.getDataType())) {
			dataSql.append(" and t.dataType = :dataType");
			parameterMap.put("dataType", findPropertyModel.getDataType());
		}
		if (null != findPropertyModel.getPropertyId() && !StringUtils.isEmpty(String.valueOf(findPropertyModel.getPropertyId()))) {
			dataSql.append(" and t.propertyId = :propertyId");
			parameterMap.put("propertyId", findPropertyModel.getPropertyId());
		}
		if (null != findPropertyModel.getCodeList() && findPropertyModel.getCodeList().size() > 0) {
			dataSql.append(" and t.propertyCode in :codes");
			parameterMap.put("codes", findPropertyModel.getCodeList());
		}
		if (null != findPropertyModel.getIdList() && findPropertyModel.getIdList().size() > 0) {
			dataSql.append(" and t.propertyId in :ids");
			parameterMap.put("ids", findPropertyModel.getIdList());
		}
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		dataSql.append(" order by t.propertyId");
		long count = dataQuery.getResultList().size();
		if(null != pageable){
			int skip = findPropertyModel.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<com.pcitc.fms.dal.pojo.PropertyMeta>(dataQuery.getResultList(), pageable, count);
		}else{
			return new PageImpl<com.pcitc.fms.dal.pojo.PropertyMeta>(dataQuery.getResultList(), null, count);
			
		}

	}
}
