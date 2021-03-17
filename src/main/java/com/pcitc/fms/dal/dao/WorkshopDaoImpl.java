/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: WorkshopDaoImpl
 * Date:18-3-9 上午8:36
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
import com.pcitc.fms.dal.pojo.Department;
import com.pcitc.fms.dal.pojo.Office;
import com.pcitc.fms.dal.pojo.Workshop;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: WorkshopDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class WorkshopDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find workshops page.
	 *
	 * @param workshopModel the workshop model
	 * @param pageable the pageable
	 * @return page
	 * @Title: findWorkshops
	 * @Description: 条件查询, 根据组织机构名称与简称模糊查询
	 * @date 2017年11月21日
	 * @return: Page<com.pcitc.fms.dal.pojo.Workshop>
	 * @author zhenqiang.zhao
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findWorkshops(com.pcitc.fms.service.model.Workshop workshopModel, Pageable pageable) throws BusiException{
		
		String countBase = "select count(1) from Workshop t,TPmOrg org,TPmOrgType orgType where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(countBase);
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.workshops +" and 1 = 1");
		Map<String ,Object> parameterMap = new HashedMap();
		
		//组织机构link
		if (StringUtils.isNotEmpty(workshopModel.getParentOrgCode()) && StringUtils.isNotEmpty(workshopModel.getParentOrgTypeCode())) {
			String parentOrgTypeCode = getOrgTypeCode(workshopModel);
			String parentSql = "select o1.ORG_ID from T_PM_ORG o1,T_PM_ORGTYPE ot1 where o1.ORG_CODE = '"+workshopModel.getParentOrgCode()+"' and o1.ORGTYPE_ID = ot1.ORGTYPE_ID and ot1.ORGTYPE_CODE = '"+parentOrgTypeCode+"' ";
			Query query = em.createNativeQuery(parentSql);
			List parentId = query.getResultList();
			
			dataSql.append( " and dtl.parentOrgId = "+parentId.get(0)+"");
			
			if (StringUtils.isNotEmpty(workshopModel.getBizCode())) {
				dataSql.append( " and biz.bizCode = :linkBizCode");
				parameterMap.put("linkBizCode", workshopModel.getBizCode());
			}
		}
		
		if (null != workshopModel.getOrgCode()  && !StringUtils.isEmpty(workshopModel.getOrgCode())) {
			dataSql.append( " and org.orgCode = :orgCode");
			countSql.append( " and org.orgCode = :orgCode");
			parameterMap.put("orgCode", workshopModel.getOrgCode());
		}
		if (null != workshopModel.getOrgName() && !StringUtils.isEmpty(workshopModel.getOrgName())) {
			dataSql.append(" and org.orgName like :orgName");
			countSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%"+workshopModel.getOrgName()+"%");
		}
		if (null != workshopModel.getOrgAlias() && !StringUtils.isEmpty(workshopModel.getOrgAlias())) {
			dataSql.append(" and org.orgAlias like :orgAlias");
			countSql.append(" and org.orgAlias like :orgAlias");
			parameterMap.put("orgAlias", "%"+workshopModel.getOrgAlias()+"%");
		}
		if (null != workshopModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(workshopModel.getOrgTypeId()))) {
			dataSql.append(" and org.orgTypeId = :orgTypeId");
			countSql.append(" and org.orgTypeId = :orgTypeId");
			parameterMap.put("orgTypeId", workshopModel.getOrgTypeId());
		}

		if (null != workshopModel.getInUse() && !StringUtils.isEmpty(String.valueOf(workshopModel.getInUse()))) {
			dataSql.append(" and org.inUse = :inUse");
			countSql.append(" and org.inUse = :inUse");
			parameterMap.put("inUse", workshopModel.getInUse());
		}
		if (null != workshopModel.getOrgCodes() && workshopModel.getOrgCodes().size() > 0) {
			dataSql.append( " and org.orgCode in :orgCodes");
			countSql.append( " and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", workshopModel.getOrgCodes());
		}
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(workshopModel.getRentCode())){
			RentCondition<Workshop> rentCondition = new RentCondition<Workshop>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(workshopModel.getRentCode(),workshopModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
	   //----------处理租户过滤
		if (StringUtils.isNotEmpty(workshopModel.getOrderby())) {
			String value = SortParam.getSortParam(Workshop.class, workshopModel.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		dataSql.append(" order by org.orgId");
		long count = (long) countQuery.getResultList().get(0);
		if(null != pageable){
			int skip = workshopModel.getSkip();
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
	
	private String getOrgTypeCode(com.pcitc.fms.service.model.Workshop workshopModel) {
		if (workshopModel.getParentOrgTypeCode().endsWith("headquarters")) {
			return "1001";
		}
		if (workshopModel.getParentOrgTypeCode().endsWith("divisions")) {
			return "1002";
		}
		if (workshopModel.getParentOrgTypeCode().endsWith("enterprises")) {
			return "1003";
		}
		if (workshopModel.getParentOrgTypeCode().endsWith("offices")) {
			return "1004";
		}
		if (workshopModel.getParentOrgTypeCode().endsWith("produceFactories")) {
			return "1005";
		}
		if (workshopModel.getParentOrgTypeCode().endsWith("departments")) {
			return "1006";
		}
		if (workshopModel.getParentOrgTypeCode().endsWith("workshops")) {
			return "1007";
		}
		return workshopModel.getParentOrgTypeCode();
	}
	
}
