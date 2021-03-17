/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TeeDaoImpl
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
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.ProduceFactory;
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.imp.common.exception.BusiException;


/**
 * The type Tee dao.
 */
public class TeeDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Get tees by model page.
	 *
	 * @param teeModel the tee model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl getTeesByModel(com.pcitc.fms.service.model.Tee teeModel, Pageable pageable) throws BusiException{
		Map<String ,Object> mapParameter = new HashedMap();
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.Tee);
		
		String tee_count = "select count(1) from Tee a,NodeDictionary b,NodeType c,AreaDictionary d,TPmOrg org where " + 
				" a.nodeCode = b.nodeCode and b.nodeTypeId = c.nodeTypeId and b.areaId = d.areaDictionaryId and d.factoryId=org.orgId ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(tee_count);
		
		if(null != teeModel.getNodeCode() && !StringUtils.isEmpty(teeModel.getNodeCode()) ){
			dataSql.append( " and a.nodeCode = :nodeCode");
			countSql.append( " and a.nodeCode = :nodeCode");
			mapParameter.put("nodeCode", teeModel.getNodeCode());
		}
		if(null != teeModel.getNodeName() && !StringUtils.isEmpty(teeModel.getNodeName())){
			dataSql.append(" and b.nodeName like :nodeName");
			countSql.append(" and b.nodeName like :nodeName");
			mapParameter.put("nodeName", "%"+teeModel.getNodeName()+"%");
		}
		if(null != teeModel.getNodeAlias() && !StringUtils.isEmpty(teeModel.getNodeAlias())){
			dataSql.append(" and b.nodeAlias like :nodeAlias") ;
			countSql.append(" and b.nodeAlias like :nodeAlias") ;
			mapParameter.put("nodeAlias",  "%"+teeModel.getNodeAlias()+"%");
		}
		if(null != teeModel.getInUse() && !StringUtils.isEmpty(String.valueOf(teeModel.getInUse()))){
			dataSql.append( " and b.dataStatus  = :inUse");
			countSql.append( " and b.dataStatus  = :inUse");
			mapParameter.put("inUse", teeModel.getInUse());
		}
		if(null != teeModel && null != teeModel.getNodeCodes() && teeModel.getNodeCodes().size() > 0){
			dataSql.append(" and a.nodeCode in :codes");
			countSql.append(" and a.nodeCode in :codes");
			mapParameter.put("codes", teeModel.getNodeCodes());
        }
		if(null != teeModel.getAreaCodes() && teeModel.getAreaCodes().size() > 0){
			dataSql.append(" and d.areaCode in :areaCodes");
			countSql.append(" and d.areaCode in :areaCodes");
			mapParameter.put("areaCodes", teeModel.getAreaCodes());
        }
		if(null != teeModel.getAreaCode() && !StringUtils.isEmpty(String.valueOf(teeModel.getAreaCode()))){
			dataSql.append(" and d.areaCode = :areaCode");
			countSql.append(" and d.areaCode = :areaCode");
			mapParameter.put("areaCode", teeModel.getAreaCode());
        }
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(teeModel.getRentCode())){
			RentCondition<SamplePoint> rentCondition = new RentCondition<SamplePoint>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(teeModel.getRentCode(),teeModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(teeModel.getOrderby())) {
			String value = SortParam.getSortParam(Tee.class, teeModel.getOrderby());
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
			dataQuery.setFirstResult( teeModel.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());

		}
		List<com.pcitc.fms.dal.pojo.Tee> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;	}

}
