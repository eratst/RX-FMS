/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: ProduceFactoryDaoImpl
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Office;
import com.pcitc.fms.dal.pojo.ProduceFactory;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: ProduceFactoryDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class ProduceFactoryDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find produce factories page.
	 *
	 * @param produceFactoryModel the produce factory model
	 * @param pageable the pageable
	 * @return page
	 * @Title: findProduceFactories
	 * @Description: 条件查询, 根据组织机构名称与简称模糊查询
	 * @date 2017年11月21日
	 * @return: Page<com.pcitc.fms.dal.pojo.ProduceFactory>
	 * @author zhenqiang.zhao
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findProduceFactories(com.pcitc.fms.service.model.ProduceFactory produceFactoryModel, Pageable pageable) throws BusiException{
		
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.produceFactories +" and 1 = 1");
		Map<String ,Object> parameterMap = new HashedMap();
		
		//组织机构link
		if (StringUtils.isNotEmpty(produceFactoryModel.getParentOrgCode()) && StringUtils.isNotEmpty(produceFactoryModel.getParentOrgTypeCode())) {
			String parentOrgTypeCode = getOrgTypeCode(produceFactoryModel);
			String parentSql = "select o1.ORG_ID from T_PM_ORG o1,T_PM_ORGTYPE ot1 where o1.ORG_CODE = '"+produceFactoryModel.getParentOrgCode()+"' and o1.ORGTYPE_ID = ot1.ORGTYPE_ID and ot1.ORGTYPE_CODE = '"+parentOrgTypeCode+"' ";
			Query query = em.createNativeQuery(parentSql);
			List parentId = query.getResultList();
			
			dataSql.append( " and dtl.parentOrgId = "+parentId.get(0)+"");
			
			if (StringUtils.isNotEmpty(produceFactoryModel.getBizCode())) {
				dataSql.append( " and biz.bizCode = :linkBizCode");
				parameterMap.put("linkBizCode", produceFactoryModel.getBizCode());
			}
		}
		
		if (null != produceFactoryModel.getOrgCode()  && !StringUtils.isEmpty(produceFactoryModel.getOrgCode())) {
			dataSql.append( " and org.orgCode = :orgCode");
			parameterMap.put("orgCode", produceFactoryModel.getOrgCode());
		}
		
		if (null != produceFactoryModel.getFctrBlockCode()  && !StringUtils.isEmpty(produceFactoryModel.getFctrBlockCode())) {
			dataSql.append( " and fctrBlockType.fctrBlockTypeCode = :fctrBlockTypeCode");
			parameterMap.put("fctrBlockTypeCode", produceFactoryModel.getFctrBlockCode());
		}
		
		if (null != produceFactoryModel.getBusinessTypeCode()  && !StringUtils.isEmpty(produceFactoryModel.getBusinessTypeCode())) {
			if (produceFactoryModel.getBusinessTypeCode().equals("1")) {
				dataSql.append( " and t.businessTypeId = 1");
			} else
			if (produceFactoryModel.getBusinessTypeCode().equals("2")) {
				dataSql.append( " and t.businessTypeId = 2");
			} else
			if (produceFactoryModel.getBusinessTypeCode().equals("3")) {
				dataSql.append( " and t.businessTypeId = 3");
			} else
			if (produceFactoryModel.getBusinessTypeCode().equals("4")) {
				dataSql.append( " and t.businessTypeId = 4");
			} else
			if (produceFactoryModel.getBusinessTypeCode().equals("9")) {
				dataSql.append( " and t.businessTypeId = 9");
			} else
			if (produceFactoryModel.getBusinessTypeCode().equals("0")) {
				dataSql.append( " and t.businessTypeId = 0");
			} else {
				dataSql.append( " and t.businessTypeId = 5");
			}
			
		}
		
		if (null != produceFactoryModel.getOrgName() && !StringUtils.isEmpty(produceFactoryModel.getOrgName())) {
			dataSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%"+produceFactoryModel.getOrgName()+"%");
		}
		if (null != produceFactoryModel.getOrgAlias() && !StringUtils.isEmpty(produceFactoryModel.getOrgAlias())) {
			dataSql.append(" and org.orgAlias like :orgAlias");
			parameterMap.put("orgAlias", "%"+produceFactoryModel.getOrgAlias()+"%");
		}
		if (null != produceFactoryModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(produceFactoryModel.getOrgTypeId()))) {
			dataSql.append(" and org.orgTypeId = :orgTypeId");
			parameterMap.put("orgTypeId", produceFactoryModel.getOrgTypeId());
		}
		if (null != produceFactoryModel.getInUse() && !StringUtils.isEmpty(String.valueOf(produceFactoryModel.getInUse()))) {
			dataSql.append(" and org.inUse = :inUse");
			parameterMap.put("inUse", produceFactoryModel.getInUse());
		}
		if (null != produceFactoryModel.getOrgCodes() && produceFactoryModel.getOrgCodes().size() > 0) {
			dataSql.append( " and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", produceFactoryModel.getOrgCodes());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(produceFactoryModel.getRentCode())){
			RentCondition<ProduceFactory> rentCondition = new RentCondition<ProduceFactory>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(produceFactoryModel.getRentCode(),produceFactoryModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
	    //----------处理租户过滤
		
		if (StringUtils.isNotEmpty(produceFactoryModel.getOrderby())) {
			String value = SortParam.getSortParam(ProduceFactory.class, produceFactoryModel.getOrderby());
			dataSql.append(value);
		}
			
		
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = 0;
		count = (long) dataQuery.getResultList().size();
		if(null != pageable){
			int skip = produceFactoryModel.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			
			MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), pageable, count);
			myPageImpl.setCount(count);
			return myPageImpl;
		}else{
			MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), null, count);
			myPageImpl.setCount(count);
			return myPageImpl;
		}
	 
	}
	
	private String getOrgTypeCode(com.pcitc.fms.service.model.ProduceFactory produceFactoryModel) {
		if (produceFactoryModel.getParentOrgTypeCode().endsWith("headquarters")) {
			return "1001";
		}
		if (produceFactoryModel.getParentOrgTypeCode().endsWith("divisions")) {
			return "1002";
		}
		if (produceFactoryModel.getParentOrgTypeCode().endsWith("enterprises")) {
			return "1003";
		}
		if (produceFactoryModel.getParentOrgTypeCode().endsWith("offices")) {
			return "1004";
		}
		if (produceFactoryModel.getParentOrgTypeCode().endsWith("produceFactories")) {
			return "1005";
		}
		if (produceFactoryModel.getParentOrgTypeCode().endsWith("departments")) {
			return "1006";
		}
		if (produceFactoryModel.getParentOrgTypeCode().endsWith("workshops")) {
			return "1007";
		}
		return produceFactoryModel.getParentOrgTypeCode();
	}
	
}
