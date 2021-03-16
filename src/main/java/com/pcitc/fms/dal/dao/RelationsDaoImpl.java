/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: RelationsDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

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
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.pojo.Connections;
import com.pcitc.fms.dal.pojo.Relations;

/**
 * Title: RelationsDaoImpl Description:TODO
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月16日
 */
@Service
public class RelationsDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Gets relations.
	 *
	 * @param relationsModel the relations model
	 * @return the relations
	 * @Title: getConnections
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public List<Relations> getRelations(com.pcitc.fms.service.model.Relations relationsModel) {
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		dataSql.append("select t from Relations  t where 1=1 ");
		Query dataQuery = em.createQuery(dataSql.toString());
		
		
		if (null != relationsModel && StringUtils.isNotEmpty(relationsModel.getSourceType())) {
			dataSql.append(" and t.sourceType = :sourceType") ;
			mapParameter.put("sourceType", relationsModel.getSourceType());
		}
		
		if (null != relationsModel && null !=relationsModel.getTargetType()) {
			dataSql.append(" and t.targetType = :targetType") ;
			mapParameter.put("targetType", relationsModel.getTargetType());
		}
		
		if (null != relationsModel && null != relationsModel.getSourceId() && !"".equals(relationsModel.getSourceCode())) {
			dataSql.append(" and t.sourceCode = :sourceCode") ;
			mapParameter.put("sourceCode", relationsModel.getSourceCode());
		}
		
		if (null != relationsModel && null != relationsModel.getTargetCode() && !"".equals(relationsModel.getSourceCode())) {
			dataSql.append(" and t.targetCode = :targetCode") ;
			mapParameter.put("targetCode", relationsModel.getTargetCode());
		}
		dataQuery = em.createQuery(dataSql.toString());
		
		for (String key  : mapParameter.keySet()) {
			dataQuery.setParameter(key, mapParameter.get(key));
		}
		return dataQuery.getResultList();
	}

	/**
	 * Gets page relations.
	 *
	 * @param relationsModel the relations model
	 * @param pageable the pageable
	 * @return page relations
	 * @Title: getPageRelations
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月27日
	 * @return: Page<Relations>
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public Page<Relations> getPageRelations(com.pcitc.fms.service.model.Relations relationsModel,Pageable pageable) {
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		dataSql.append("select t from Relations  t where 1=1 ");
		Query dataQuery = em.createQuery(dataSql.toString());
		
		if (null != relationsModel && null != relationsModel.getSourceId() && 0!=relationsModel.getSourceId()) {
			dataSql.append(" and t.sourceId = :sourceId") ;
			mapParameter.put("sourceId", relationsModel.getSourceId());
		}
		
		if (null != relationsModel && StringUtils.isNotEmpty(relationsModel.getSourceCode())) {
			dataSql.append(" and t.sourceCode = :sourceCode") ;
			mapParameter.put("sourceCode", relationsModel.getSourceCode());
		}
		
		if (null != relationsModel && StringUtils.isNotEmpty(relationsModel.getSourceType())) {
			dataSql.append(" and t.sourceType = :sourceType") ;
			mapParameter.put("sourceType", relationsModel.getSourceType());
		}
		
		if (null != relationsModel && null != relationsModel.getTargetId() && 0!=relationsModel.getTargetId()) {
			dataSql.append(" and t.targetId = :targetId") ;
			mapParameter.put("targetId", relationsModel.getTargetId());
		}
		
		if (null != relationsModel && null !=relationsModel.getTargetCode()) {
			dataSql.append(" and t.targetCode = :targetCode") ;
			mapParameter.put("targetCode", relationsModel.getTargetCode());
		}
		
		if (null != relationsModel && null !=relationsModel.getTargetType()) {
			dataSql.append(" and t.targetType = :targetType") ;
			mapParameter.put("targetType", relationsModel.getTargetType());
		}
		
		if (null != relationsModel && null != relationsModel.getCodeList() && relationsModel.getCodeList().size()>0) {
			dataSql.append(" and t.code in :codeList") ;
			mapParameter.put("codeList", relationsModel.getCodeList());
		}
		
		dataQuery = em.createQuery(dataSql.toString());
		
		for (String key  : mapParameter.keySet()) {
			dataQuery.setParameter(key, mapParameter.get(key));
		}

		if (pageable != null) {
			long  count  = dataQuery.getResultList().size();
			dataQuery.setFirstResult(relationsModel.getTop());
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<Relations>(dataQuery.getResultList(), pageable, count);
		}else {
			if (null != relationsModel && null != relationsModel.getTop() && relationsModel.getTop() >=0 ) {
				dataQuery.setMaxResults(relationsModel.getTop());
			}
			if(null != relationsModel && null != relationsModel.getSkip() && relationsModel.getSkip()>=0){
				dataQuery.setFirstResult(relationsModel.getSkip());
			}
			return new PageImpl<Relations>(dataQuery.getResultList(), null, 0);
		}
	}

	/**
	 * Gets unique relations.
	 *
	 * @param relationsModle the relations modle
	 * @return unique relations
	 * @Title: getUniqueConnections
	 * @Description: 唯一性校验
	 * @date 2017年8月10日
	 * @return: com.pcitc.fms.dal.pojo.Connections
	 * @author 赵振强
	 */
	public com.pcitc.fms.dal.pojo.Relations getUniqueRelations(com.pcitc.fms.service.model.Relations relationsModle) {
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		dataSql.append("select t from Relations  t where 1=1 ");
		Query dataQuery = em.createQuery(dataSql.toString());
//		
//		if (null != relationsModle && null != relationsModle.getFactoryId()) {
//			dataSql.append(" and t.factoryId = :factoryId ");
//			mapParameter.put("factoryId", relationsModle.getFactoryId());
//		}
		if (null != relationsModle && null != relationsModle.getSourceCode()) {
			dataSql.append(" and t.sourceCode = :sourceCode ");
			mapParameter.put("sourceCode", relationsModle.getSourceCode());
		}
		
		if (null != relationsModle && StringUtils.isNotEmpty(relationsModle.getSourceType())) {
			dataSql.append(" and t.sourceType  = :sourceType") ;
			mapParameter.put("sourceType", relationsModle.getSourceType());
		}
		
		if (null != relationsModle && null != relationsModle.getTargetCode()) {
			dataSql.append(" and t.targetCode  = :targetCode") ;
			mapParameter.put("targetCode", relationsModle.getTargetCode());
		}
		
		if (null != relationsModle && StringUtils.isNotEmpty(relationsModle.getTargetType())) {
			dataSql.append(" and t.targetType   =:targetType ");
			mapParameter.put("targetType", relationsModle.getTargetType());
		}
		dataQuery = em.createQuery(dataSql.toString());
		
		for (String key  : mapParameter.keySet()) {
			dataQuery.setParameter(key, mapParameter.get(key));
		}
		 List results = dataQuery.getResultList();
		return null != results && results.size()>0  ?(Relations) results.get(0) :null;
	}
	
}
