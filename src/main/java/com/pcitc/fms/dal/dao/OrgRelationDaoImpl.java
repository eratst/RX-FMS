/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OrgRelationDaoImpl
 * Date:18-3-9 上午8:34
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
import com.pcitc.fms.dal.pojo.OrgRelation;

/**
 * Title: OrgRelationDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年7月1日
 */
@Service
public class OrgRelationDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Gets org relations.
	 *
	 * @param orgRelationModle the org relation modle
	 * @param pageable the pageable
	 * @return org relations
	 * @Title: getOrgRelations
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月1日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public Page<com.pcitc.fms.dal.pojo.OrgRelation> getOrgRelations(com.pcitc.fms.service.model.OrgRelation orgRelationModle,Pageable pageable) {
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		dataSql.append("select t from OrgRelation  t where 1=1 ");
		Query dataQuery = em.createQuery(dataSql.toString());
		
		if (null != orgRelationModle && null != orgRelationModle.getFactoryCode()) {
			dataSql.append(" and t.factoryCode = :factoryCode ");
			mapParameter.put("factoryCode", orgRelationModle.getFactoryCode());
		}
		
		if (null != orgRelationModle && null != orgRelationModle.getOrgRelationType()) {
			dataSql.append(" and t.orgRelationType = :orgRelationType ");
			mapParameter.put("orgRelationType", orgRelationModle.getOrgRelationType());
		}
		
		if (null != orgRelationModle && null != orgRelationModle.getSourceId()) {
			dataSql.append(" and t.sourceId = :sourceId ");
			mapParameter.put("sourceId", orgRelationModle.getSourceId());
		}
		
		if (null != orgRelationModle && null != orgRelationModle.getSourceCode()) {
			dataSql.append(" and t.sourceCode = :sourceCode ");
			mapParameter.put("sourceCode", orgRelationModle.getSourceCode());
		}
		
		if (null != orgRelationModle && StringUtils.isNotEmpty(orgRelationModle.getSourceType())) {
			dataSql.append(" and t.sourceType  = :sourceType") ;
			mapParameter.put("sourceType", orgRelationModle.getSourceType());
		}
		
		if (null != orgRelationModle && null != orgRelationModle.getTargetId()) {
			dataSql.append(" and t.targetId  = :targetId") ;
			mapParameter.put("targetId", orgRelationModle.getTargetId());
		}
		
		if (null != orgRelationModle && null != orgRelationModle.getTargetCode()) {
			dataSql.append(" and t.targetCode  = :targetCode") ;
			mapParameter.put("targetCode", orgRelationModle.getTargetCode());
		}
		
		if (null != orgRelationModle && StringUtils.isNotEmpty(orgRelationModle.getTargetType())) {
			dataSql.append(" and t.targetType   =:targetType ");
			mapParameter.put("targetType", orgRelationModle.getTargetType());
		}
		if (null != orgRelationModle && StringUtils.isNotEmpty(orgRelationModle.getOrgRelationType())) {
			dataSql.append(" and t.orgRelationType   =:orgRelationType ");
			mapParameter.put("orgRelationType", orgRelationModle.getOrgRelationType());
		}
		
		if (null != orgRelationModle && null != orgRelationModle.getIdList() && orgRelationModle.getIdList().size()>0) {
			dataSql.append(" and t.orgRelationId in :idList") ;
			mapParameter.put("idList", orgRelationModle.getIdList());
		}

		dataQuery = em.createQuery(dataSql.toString());
		
		for (String key  : mapParameter.keySet()) {
			dataQuery.setParameter(key, mapParameter.get(key));
		}

		long count = dataQuery.getResultList().size();
		int skip = orgRelationModle.getSkip();
		dataQuery.setFirstResult(skip);
		if(null != pageable){
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		
		return new PageImpl<OrgRelation>(dataQuery.getResultList(), pageable, count);
		/*if (pageable != null) {
			long  count  = dataQuery.getResultList().size();
			dataQuery.setFirstResult(orgRelationModle.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<OrgRelation>(dataQuery.getResultList(), pageable, count);
		}else {
			if (null != orgRelationModle && null != orgRelationModle.getTop() && orgRelationModle.getTop() >=0 ) {
				dataQuery.setMaxResults(orgRelationModle.getTop());
			}
			if(null != orgRelationModle && null != orgRelationModle.getSkip() && orgRelationModle.getSkip()>=0){
				dataQuery.setFirstResult(orgRelationModle.getSkip());
			}
			return new PageImpl<OrgRelation>(dataQuery.getResultList(), null, 0);
		}*/
	}

	/**
	 * Gets unique org relations.
	 *
	 * @param orgRelationModle the org relation modle
	 * @return unique org relations
	 * @Title: getUniqueOrgRelations
	 * @Description:用于验证数据唯一性的查询语句,不允许重复的记录添加
	 * @date 2017年7月25日
	 * @return: Page<com.pcitc.fms.dal.pojo.OrgRelation>
	 * @author zhenqiang.zhao
	 */
	public com.pcitc.fms.dal.pojo.OrgRelation getUniqueOrgRelations(com.pcitc.fms.service.model.OrgRelation orgRelationModle) {
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		dataSql.append("select t from OrgRelation  t where 1=1 ");
		Query dataQuery = em.createQuery(dataSql.toString());
		
		if (null != orgRelationModle && null != orgRelationModle.getFactoryId()) {
			dataSql.append(" and t.factoryId = :factoryId ");
			mapParameter.put("factoryId", orgRelationModle.getFactoryId());
		}
		
		if (null != orgRelationModle && null != orgRelationModle.getOrgRelationType()) {
			dataSql.append(" and t.orgRelationType = :orgRelationType ");
			mapParameter.put("orgRelationType", orgRelationModle.getOrgRelationType());
		}
		
//		if (null != orgRelationModle && null != orgRelationModle.getSourceId()) {
//			dataSql.append(" and t.sourceId = :sourceId ");
//			mapParameter.put("sourceId", orgRelationModle.getSourceId());
//		}
		
		if (null != orgRelationModle && null != orgRelationModle.getSourceCode()) {
			dataSql.append(" and t.sourceCode = :sourceCode ");
			mapParameter.put("sourceCode", orgRelationModle.getSourceCode());
		}
		if (null != orgRelationModle && StringUtils.isNotEmpty(orgRelationModle.getSourceType())) {
			dataSql.append(" and t.sourceType  = :sourceType") ;
			mapParameter.put("sourceType", orgRelationModle.getSourceType());
		}
		
//		if (null != orgRelationModle && null != orgRelationModle.getTargetId()) {
//			dataSql.append(" and t.targetId  = :targetId") ;
//			mapParameter.put("targetId", orgRelationModle.getTargetId());
//		}
		
		if (null != orgRelationModle && null != orgRelationModle.getTargetCode()) {
			dataSql.append(" and t.targetCode  = :targetCode") ;
			mapParameter.put("targetCode", orgRelationModle.getTargetCode());
		}
		
		if (null != orgRelationModle && StringUtils.isNotEmpty(orgRelationModle.getTargetType())) {
			dataSql.append(" and t.targetType   =:targetType ");
			mapParameter.put("targetType", orgRelationModle.getTargetType());
		}
		dataQuery = em.createQuery(dataSql.toString());
		
		for (String key  : mapParameter.keySet()) {
			dataQuery.setParameter(key, mapParameter.get(key));
		}
		 List results = dataQuery.getResultList();
		return null != results && results.size()>0  ?(OrgRelation) results.get(0) :null;
	}
}
