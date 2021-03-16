/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: PostionDaoImpl
 * Date:18-3-9 上午8:34
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

/**
 * The type Postion dao.
 */
public class PostionDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
	private EntityManager em;

	/**
	 * Find postions list.
	 *
	 * @param findPostionModel the find postion model
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<com.pcitc.fms.dal.pojo.PostionMeta> findPostions(com.pcitc.fms.service.model.PostionMeta findPostionModel) {
		Map<String, Object> parameterMap = new HashedMap();
		StringBuilder dataSql = new StringBuilder();
		dataSql.append("select t from PostionMeta t where 1 = 1");
		if (null != findPostionModel.getPostionId() && !StringUtils.isEmpty(String.valueOf(findPostionModel.getPostionId()))) {
			dataSql.append(" and t.postionId = :postionId");
			parameterMap.put("postionId", findPostionModel.getPostionId());
		}
		
		if (null != findPostionModel && !StringUtils.isEmpty(findPostionModel.getName())) {
			dataSql.append(" and t.name like :name");
			parameterMap.put("name", "%" + findPostionModel.getName() + "%");
		}
		if (null != findPostionModel.getShortName() && !StringUtils.isEmpty(findPostionModel.getShortName())) {
			dataSql.append(" and t.shortName like :shortName");
			parameterMap.put("shortName", "%" + findPostionModel.getShortName() + "%");
		}
		if (null != findPostionModel.getEnabled() && !StringUtils.isEmpty(String.valueOf(findPostionModel.getEnabled()))) {
			dataSql.append(" and t.enabled = :enabled");
			parameterMap.put("enabled", findPostionModel.getEnabled());
		}
		if (null != findPostionModel && findPostionModel.getCodeList().size() > 0) {
			dataSql.append(" and t.code in :codes");
			parameterMap.put("codes", findPostionModel.getCodeList());
		}
		if (null != findPostionModel && findPostionModel.getIdList().size() > 0) {
			dataSql.append(" and t.postionId in :ids");
			parameterMap.put("ids", findPostionModel.getIdList());
		}
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		dataSql.append(" order by t.entityId");
		List resultList = null;
		if (findPostionModel.getSkip() >= 0 && findPostionModel.getTop() > 0) {
			resultList = dataQuery.setMaxResults(findPostionModel.getTop()).setFirstResult(findPostionModel.getSkip())
					.getResultList();
		} else {

			resultList = dataQuery.getResultList();
		}
		return resultList;
	}
}
