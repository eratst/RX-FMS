/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DictionaryTableDaoImpl
 * Date:18-3-9 上午8:33
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

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

import com.pcitc.fms.dal.pojo.DictionaryTable;

/**
 * The type Dictionary table dao.
 */
@Service
public class DictionaryTableDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Find dictionary tables page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 */
	@SuppressWarnings("unchecked")
	public Page<com.pcitc.fms.dal.pojo.DictionaryTable> findDictionaryTables(
			com.pcitc.fms.service.model.DictionaryTable model, Pageable pageable) {
		Map<String ,Object> parameterMap = new HashedMap();
		StringBuilder dataSql = new StringBuilder();
		dataSql.append("select t from DictionaryTable t where 1 = 1");
		if (null != model.getEntityId()) {
			dataSql.append(" and t.entityId = :entityId");
			parameterMap.put("entityId", model.getEntityId());
		}
		if (null != model.getFieldName() && !StringUtils.isEmpty(model.getFieldName())) {
			dataSql.append(" and t.fieldName = :fieldName");
			parameterMap.put("fieldName", model.getFieldName());
		}
		if (null != model.getFieldValue() && !StringUtils.isEmpty(model.getFieldValue())) {
			dataSql.append(" and t.fieldValue = :fieldValue");
			parameterMap.put("fieldValue", model.getFieldValue());
		}
		if (null != model.getName() && !StringUtils.isEmpty(model.getName())) {
			dataSql.append( " and t.name like :name");
			parameterMap.put("name", "%"+model.getName()+"%");
		}
		if (null != model.getIdList() && model.getIdList().size() > 0) {
			dataSql.append( " and t.dictionaryTableId in :ids");
			parameterMap.put("ids", model.getIdList());
		}
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		dataSql.append(" order by t.dictionaryTableId");
		
		long count = dataQuery.getResultList().size();
		if(null != pageable){
			int skip = model.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<com.pcitc.fms.dal.pojo.DictionaryTable>(dataQuery.getResultList(), pageable, count);
		}else{
			return new PageImpl<com.pcitc.fms.dal.pojo.DictionaryTable>(dataQuery.getResultList(), null, count);
			
		}
	
	}
}
