/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: EnterpriseDaoImpl
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
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Enterprise;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: EnterpriseDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class EnterpriseDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find enterprises page.
	 *
	 * @param enterpriseModel the enterprise model
	 * @param pageable the pageable
	 * @return page
	 * @Title: findEnterprises
	 * @Description: 条件查询, 根据组织机构名称与简称模糊查询
	 * @date 2017年11月21日
	 * @return: Page<com.pcitc.fms.dal.pojo.Enterprise>
	 * @author zhenqiang.zhao
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findEnterprises(com.pcitc.fms.service.model.Enterprise enterpriseModel, Pageable pageable) throws BusiException{
		
		String countBase = "select count(1) from Enterprise t,TPmOrg org,TPmOrgType orgType, EntrType entrType,TPmBizOrgDTL dtl,TPmBizOrgMain biz where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId and t.entrTypeId = entrType.entrTypeId and t.orgId = org.orgId and org.orgId = dtl.orgId and biz.bizId = dtl.bizId ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(countBase);
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.enterprises +" and 1 = 1");
		Map<String ,Object> parameterMap = new HashedMap();
		
		//组织机构link
		if (StringUtils.isNotEmpty(enterpriseModel.getParentOrgCode()) && StringUtils.isNotEmpty(enterpriseModel.getParentOrgTypeCode())) {
			String parentOrgTypeCode = getOrgTypeCode(enterpriseModel);
			String parentSql = "select o1.ORG_ID from T_PM_ORG o1,T_PM_ORGTYPE ot1 where o1.ORG_CODE = '"+enterpriseModel.getParentOrgCode()+"' and o1.ORGTYPE_ID = ot1.ORGTYPE_ID and ot1.ORGTYPE_CODE = '"+parentOrgTypeCode+"' ";
			Query query = em.createNativeQuery(parentSql);
			List parentId = query.getResultList();
			
			dataSql.append( " and dtl.parentOrgId = "+parentId.get(0)+"");
			
			if (StringUtils.isNotEmpty(enterpriseModel.getBizCode())) {
				dataSql.append( " and biz.bizCode = :linkBizCode");
				countSql.append( " and biz.bizCode = :linkBizCode");
				parameterMap.put("linkBizCode", enterpriseModel.getBizCode());
			}
		}
		
		if (null != enterpriseModel.getOrgCode()  && !StringUtils.isEmpty(enterpriseModel.getOrgCode())) {
			dataSql.append( " and org.orgCode = :orgCode");
			countSql.append( " and org.orgCode = :orgCode");
			parameterMap.put("orgCode", enterpriseModel.getOrgCode());
		}
		if (null != enterpriseModel.getOrgName() && !StringUtils.isEmpty(enterpriseModel.getOrgName())) {
			dataSql.append(" and org.orgName like :orgName");
			countSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%"+enterpriseModel.getOrgName()+"%");
		}
		if (null != enterpriseModel.getOrgAlias() && !StringUtils.isEmpty(enterpriseModel.getOrgAlias())) {
			dataSql.append(" and org.orgAlias like :orgAlias");
			countSql.append(" and org.orgAlias like :orgAlias");
			parameterMap.put("orgAlias", "%"+enterpriseModel.getOrgAlias()+"%");
		}
		if (null != enterpriseModel.getEntrTypeCode() && !StringUtils.isEmpty(enterpriseModel.getEntrTypeCode())) {
			dataSql.append(" and entrType.entrTypeCode = :entrTypeCode");
			countSql.append(" and entrType.entrTypeCode = :entrTypeCode");
			parameterMap.put("entrTypeCode", enterpriseModel.getEntrTypeCode());
		}
		//orgType
		if (null != enterpriseModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(enterpriseModel.getOrgTypeId()))) {
			dataSql.append(" and org.orgTypeId = :orgTypeId");
			countSql.append(" and org.orgTypeId = :orgTypeId");
			parameterMap.put("orgTypeId", enterpriseModel.getOrgTypeId());
		}
		if (null != enterpriseModel.getInUse() && !StringUtils.isEmpty(String.valueOf(enterpriseModel.getInUse()))) {
			dataSql.append(" and org.inUse = :inUse");
			countSql.append(" and org.inUse = :inUse");
			parameterMap.put("inUse", enterpriseModel.getInUse());
		}
		if (null != enterpriseModel.getOrgCodes() && enterpriseModel.getOrgCodes().size() > 0) {
			dataSql.append( " and org.orgCode in :orgCodes");
			countSql.append( " and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", enterpriseModel.getOrgCodes());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(enterpriseModel.getRentCode())){
			RentCondition<Enterprise> rentCondition = new RentCondition<Enterprise>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(enterpriseModel.getRentCode(),enterpriseModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(enterpriseModel.getOrderby())) {
			String value = SortParam.getSortParam(Enterprise.class, enterpriseModel.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = (long) countQuery.getResultList().get(0);
		if(null != pageable){
			int skip = enterpriseModel.getSkip();
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
	
	private String getOrgTypeCode(com.pcitc.fms.service.model.Enterprise enterprise) {
		if (enterprise.getParentOrgTypeCode().endsWith("headquarters")) {
			return "1001";
		}
		if (enterprise.getParentOrgTypeCode().endsWith("divisions")) {
			return "1002";
		}
		if (enterprise.getParentOrgTypeCode().endsWith("enterprises")) {
			return "1003";
		}
		if (enterprise.getParentOrgTypeCode().endsWith("offices")) {
			return "1004";
		}
		if (enterprise.getParentOrgTypeCode().endsWith("produceFactories")) {
			return "1005";
		}
		if (enterprise.getParentOrgTypeCode().endsWith("departments")) {
			return "1006";
		}
		if (enterprise.getParentOrgTypeCode().endsWith("workshops")) {
			return "1007";
		}
		return enterprise.getParentOrgTypeCode();
	}
}
