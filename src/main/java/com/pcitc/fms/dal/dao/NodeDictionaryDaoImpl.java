/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: NodeDictionaryDaoImpl
 * Date:18-3-9 上午8:34
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

import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.imp.common.exception.BusiException;


/**
 * The type Node dictionary dao.
 */
public class NodeDictionaryDaoImpl {

  /**
   * The Em.
   */
  @PersistenceContext
    private EntityManager em;

  /**
   * Get node dictionary tables by model page.
   *
   * @param nodeDictionaryTableModel the node dictionary table model
   * @param pageable the pageable
   * @return the page
 * @throws BusiException 
   */
  @SuppressWarnings("unchecked")
	public MyPageImpl getNodeDictionaryTablesByModel (com.pcitc.fms.service.model.NodeDictionary model, Pageable pageable) throws BusiException{

		Map<String ,Object> parameterMap = new HashedMap();
		String dataSql = AreaNodeBasicSql.NodeDictionary;
		if(model.getNodeCode()!=null){
			dataSql+=" and a.nodeCode="+"'"+model.getNodeCode()+"'";
		}
		if(model.getNodeName()!=null){
			dataSql+=" and a.nodeName like "+"'%"+model.getNodeName()+"%'";
		}
		if(model.getNodeAlias()!=null){
			dataSql+=" and a.nodeAlias like "+"'%"+model.getNodeAlias()+"%'";
		}
		if(model.getNodeTypeCode()!=null){
			dataSql+=" and b.nodeTypeCode="+"'"+model.getNodeTypeCode()+"'";
		}
		if(model.getInUse()!=null){
			dataSql+=" and a.dataStatus="+"'"+model.getInUse()+"'";
		}
		if(null != model.getAreaCodes() && model.getAreaCodes().size() > 0){
			dataSql += " and ad.areaCode in "+CheckUtil.getList(model.getAreaCodes());
		}
		if(null != model.getNodeCodes() && model.getNodeCodes().size() > 0){
			dataSql += " and a.nodeCode in "+CheckUtil.getList(model.getNodeCodes());
		}
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(model.getRentCode())){
			RentCondition<NodeDictionary> rentCondition = new RentCondition<NodeDictionary>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(model.getRentCode(),model.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql +=" and "+rentOrgCodes;
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤	
		
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(NodeDictionary.class, model.getOrderby());
			dataSql += value;
		}
		
		Query dataQuery = em.createQuery(dataSql);
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer skip = null;
		
//		long count = dataQuery.getResultList().size();
		long count =0L;
		if(null != pageable){
		    skip = model.getSkip();
          dataQuery = em.createQuery(dataSql);
          dataQuery.setFirstResult(skip);
          dataQuery.setMaxResults(pageable.getPageSize());
          
		}
		List<com.pcitc.fms.dal.pojo.NodeDictionary> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;

	
  }
	
	
}
