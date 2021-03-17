/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: ConnectionsDaoImpl
 * Date:18-3-9 上午8:33
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

import com.pcitc.fms.dal.pojo.Connections;
import com.pcitc.fms.dal.pojo.OrgRelation;

/**
 * Title: ConnectionsDaoImpl Description:TODO
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月16日
 */
public class ConnectionsDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Gets connections.
	 *
	 * @param connectionsModel the connections model
	 * @return the connections
	 * @Title: getConnections
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public List<Connections>   getConnections(com.pcitc.fms.service.model.Connections connectionsModel) {
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		dataSql.append("select t from Connections  t where 1=1 ");
		Query dataQuery = em.createQuery(dataSql.toString());
		
		if (null != connectionsModel && null != connectionsModel.getSourceId() && 0!=connectionsModel.getSourceId()) {
			dataSql.append(" and t.sourceId = :sourceId") ;
			mapParameter.put("sourceId", connectionsModel.getSourceId());
		}
		
		if (null != connectionsModel && "".equals(connectionsModel.getSourceCode())) {
			dataSql.append(" and t.sourceCode = :sourceCode") ;
			mapParameter.put("sourceCode", connectionsModel.getSourceCode());
		}
		
		if (null != connectionsModel && StringUtils.isNotEmpty(connectionsModel.getSourceType())) {
			dataSql.append(" and t.sourceType = :sourceType") ;
			mapParameter.put("sourceType", connectionsModel.getSourceType());
		}
		
		if (null != connectionsModel && null !=connectionsModel.getTargetType()) {
			dataSql.append(" and t.targetType = :targetType") ;
			mapParameter.put("targetType", connectionsModel.getTargetType());
		}
		
		dataQuery = em.createQuery(dataSql.toString());
		
		for (String key  : mapParameter.keySet()) {
			dataQuery.setParameter(key, mapParameter.get(key));
		}
		return dataQuery.getResultList();
	}


	/**
	 * Gets page connections.
	 *
	 * @param connectionsModel the connections model
	 * @param pageable the pageable
	 * @param factoryCode the factory code
	 * @return page connections
	 * @Title: getPageConnections
	 * @Description: TODO task mark zhenqiang.zhao
	 * @date 2017年7月27日
	 * @return: Page<Connections>
	 * @author zhenqiang.zhao
	 */
	@SuppressWarnings("unchecked")
	public Page<Connections>   getPageConnections(com.pcitc.fms.service.model.Connections connectionsModel,Pageable pageable,String factoryCode) {
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		dataSql.append("select t from Connections  t where 1=1 ");
		Query dataQuery = em.createQuery(dataSql.toString());
		
		if (null != factoryCode ) {
			dataSql.append(" and t.sourceCode ="+"'"+factoryCode+"'") ;
		}
		if (null != connectionsModel && null != connectionsModel.getSourceId() && 0!=connectionsModel.getSourceId()) {
			dataSql.append(" and t.sourceId = :sourceId") ;
			mapParameter.put("sourceId", connectionsModel.getSourceId());
		}
		
		if (null != connectionsModel && StringUtils.isNotEmpty(connectionsModel.getSourceCode())) {
			dataSql.append(" and t.sourceCode = :sourceCode") ;
			mapParameter.put("sourceCode", connectionsModel.getSourceCode());
		}
		
		if (null != connectionsModel && StringUtils.isNotEmpty(connectionsModel.getSourceType())) {
			dataSql.append(" and t.sourceType = :sourceType") ;
			mapParameter.put("sourceType", connectionsModel.getSourceType());
		}
		
		if (null != connectionsModel && null != connectionsModel.getTargetId() && 0!=connectionsModel.getTargetId()) {
			dataSql.append(" and t.targetId = :targetId") ;
			mapParameter.put("targetId", connectionsModel.getTargetId());
		}
		
		if (null != connectionsModel && null !=connectionsModel.getTargetCode()) {
			dataSql.append(" and t.targetCode = :targetCode") ;
			mapParameter.put("targetCode", connectionsModel.getTargetCode());
		}
		
		if (null != connectionsModel && null !=connectionsModel.getTargetType()) {
			dataSql.append(" and t.targetType = :targetType") ;
			mapParameter.put("targetType", connectionsModel.getTargetType());
		}
		
		if (null != connectionsModel && null != connectionsModel.getCodeList() && connectionsModel.getCodeList().size()>0) {
			dataSql.append(" and t.code in :codeList") ;
			mapParameter.put("codeList", connectionsModel.getCodeList());
		}
		dataQuery = em.createQuery(dataSql.toString());
		
		for (String key  : mapParameter.keySet()) {
			dataQuery.setParameter(key, mapParameter.get(key));
		}

		long count = dataQuery.getResultList().size();
		if(null != pageable){
			int skip = connectionsModel.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		return new PageImpl<Connections>(dataQuery.getResultList(), pageable, count);
		/*if (pageable != null) {
			long  count  = dataQuery.getResultList().size();
			dataQuery.setFirstResult(connectionsModel.getTop());
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<Connections>(dataQuery.getResultList(), pageable, count);
		}else {
			if (null != connectionsModel && null != connectionsModel.getTop() && connectionsModel.getTop() >=0 ) {
				dataQuery.setMaxResults(connectionsModel.getTop());
			}
			if(null != connectionsModel && null != connectionsModel.getSkip() && connectionsModel.getSkip()>=0){
				dataQuery.setFirstResult(connectionsModel.getSkip());
			}
			return new PageImpl<Connections>(dataQuery.getResultList(), null, 0);
		}*/
	}


	/**
	 * Gets unique connections.
	 *
	 * @param connectionsModle the connections modle
	 * @return unique connections
	 * @Title: getUniqueConnections
	 * @Description: 唯一性校验
	 * @date 2017年8月10日
	 * @return: com.pcitc.fms.dal.pojo.Connections
	 * @author 赵振强
	 */
	public com.pcitc.fms.dal.pojo.Connections getUniqueConnections(com.pcitc.fms.service.model.Connections connectionsModle) {
		StringBuffer dataSql = new StringBuffer();
		Map<String, Object> mapParameter = new HashMap<String, Object>();
		dataSql.append("select t from Connections  t where 1=1 ");
		Query dataQuery = em.createQuery(dataSql.toString());
//		
//		if (null != connectionsModle && null != connectionsModle.getFactoryId()) {
//			dataSql.append(" and t.factoryId = :factoryId ");
//			mapParameter.put("factoryId", connectionsModle.getFactoryId());
//		}
		if (null != connectionsModle && null != connectionsModle.getSourceId()) {
			dataSql.append(" and t.sourceId = :sourceId ");
			mapParameter.put("sourceId", connectionsModle.getSourceId());
		}
		
		if (null != connectionsModle && null != connectionsModle.getSourceCode()) {
			dataSql.append(" and t.sourceCode = :sourceCode ");
			mapParameter.put("sourceCode", connectionsModle.getSourceCode());
		}
		
		if (null != connectionsModle && StringUtils.isNotEmpty(connectionsModle.getSourceType())) {
			dataSql.append(" and t.sourceType  = :sourceType") ;
			mapParameter.put("sourceType", connectionsModle.getSourceType());
		}
		
		if (null != connectionsModle && null != connectionsModle.getTargetId()) {
			dataSql.append(" and t.targetId  = :targetId") ;
			mapParameter.put("targetId", connectionsModle.getTargetId());
		}
		
		if (null != connectionsModle && null != connectionsModle.getTargetCode()) {
			dataSql.append(" and t.targetCode  = :targetCode") ;
			mapParameter.put("targetCode", connectionsModle.getTargetCode());
		}
		
		if (null != connectionsModle && StringUtils.isNotEmpty(connectionsModle.getTargetType())) {
			dataSql.append(" and t.targetType   =:targetType ");
			mapParameter.put("targetType", connectionsModle.getTargetType());
		}
		dataQuery = em.createQuery(dataSql.toString());
		
		for (String key  : mapParameter.keySet()) {
			dataQuery.setParameter(key, mapParameter.get(key));
		}
		 List results = dataQuery.getResultList();
		return null != results && results.size()>0  ?(Connections) results.get(0) :null;
	}
	
}
