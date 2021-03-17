/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: EntityDaoImpl
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

/**
 * The type Entity dao.
 */
public class EntityDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Find entities page.
	 *
	 * @param findEntityModel the find entity model
	 * @param pageable the pageable
	 * @return the page
	 */
	@SuppressWarnings("unchecked")
	public Page<com.pcitc.fms.dal.pojo.EntityMeta> findEntities(
			com.pcitc.fms.service.model.EntityMeta findEntityModel, Pageable pageable) {
		StringBuilder dataSql = new StringBuilder();
		Map<String, Object> parameterMap = new HashedMap();
		dataSql.append("select t from EntityMeta t where 1 = 1");
		if (null != findEntityModel.getCode()) {
			dataSql.append(" and t.code = :code");
			parameterMap.put("code", findEntityModel.getCode());
		}
		
		if (null != findEntityModel.getEntityName()
				&& !StringUtils.isEmpty(String.valueOf(findEntityModel.getEntityName()))) {
			dataSql.append(" and t.entityName like :entityName");
			parameterMap.put("entityName", "%" + findEntityModel.getEntityName() + "%");
		}
		if (null != findEntityModel.getEntityType()
				&& !StringUtils.isEmpty(String.valueOf(findEntityModel.getEntityType()))) {
			dataSql.append(" and t.entityType = :entityType");
			parameterMap.put("entityType", findEntityModel.getEntityType());
		}
		if (null != findEntityModel.getEntityTableName()
				&& !StringUtils.isEmpty(String.valueOf(findEntityModel.getEntityTableName()))) {
			dataSql.append(" and t.entityTableName = :entityTableName");
			parameterMap.put("entityTableName", findEntityModel.getEntityTableName());
		}
		if (null != findEntityModel.getIdList() && findEntityModel.getIdList().size() > 0) {
			dataSql.append(" and t.entityId in :ids");
			parameterMap.put("ids", findEntityModel.getIdList());

		}
		if (null != findEntityModel.getCodeList() && findEntityModel.getCodeList().size() > 0) {
			dataSql.append(" and t.code in :codes");
			parameterMap.put("codes", findEntityModel.getCodeList());

		}

		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		dataSql.append(" order by t.entityId");
		
	
		long count = dataQuery.getResultList().size();
		if(null != pageable){
			int skip = findEntityModel.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<com.pcitc.fms.dal.pojo.EntityMeta>(dataQuery.getResultList(), pageable, count);
		}else{
			return new PageImpl<com.pcitc.fms.dal.pojo.EntityMeta>(dataQuery.getResultList(), null, count);
			
		}
	}
}
