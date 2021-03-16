/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DepartmentDaoImpl
 * Date:18-3-9 上午8:33
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Department;
import com.pcitc.fms.dal.pojo.ProduceFactory;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: DepartmentDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class DepartmentDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find departments page.
	 *
	 * @param departmentModel the department model
	 * @param pageable the pageable
	 * @return page
	 * @Title: findDepartments
	 * @Description: 条件查询, 根据组织机构名称与简称模糊查询
	 * @date 2017年11月21日
	 * @return: Page<com.pcitc.fms.dal.pojo.Department>
	 * @author zhenqiang.zhao
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findDepartments(com.pcitc.fms.service.model.Department departmentModel, Pageable pageable) throws BusiException{
		
		String countBase = "select count(1) from Department t,TPmOrg org,TPmOrgType orgType where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(countBase);
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.departments +" and 1 = 1");
		
		Map<String ,Object> parameterMap = new HashedMap();
		
		//组织机构link
		if (StringUtils.isNotEmpty(departmentModel.getParentOrgCode()) && StringUtils.isNotEmpty(departmentModel.getParentOrgTypeCode())) {
			String parentOrgTypeCode = getOrgTypeCode(departmentModel);
			String parentSql = "select o1.ORG_ID from T_PM_ORG o1,T_PM_ORGTYPE ot1 where o1.ORG_CODE = '"+departmentModel.getParentOrgCode()+"' and o1.ORGTYPE_ID = ot1.ORGTYPE_ID and ot1.ORGTYPE_CODE = '"+parentOrgTypeCode+"' ";
			Query query = em.createNativeQuery(parentSql);
			List parentId = query.getResultList();
			
			dataSql.append( " and dtl.parentOrgId = "+parentId.get(0)+"");
			
			if (StringUtils.isNotEmpty(departmentModel.getBizCode())) {
				dataSql.append( " and biz.bizCode = :linkBizCode");
				parameterMap.put("linkBizCode", departmentModel.getBizCode());
			}
		}
		
		if (null != departmentModel.getOrgCode()  && !StringUtils.isEmpty(departmentModel.getOrgCode())) {
			dataSql.append( " and org.orgCode = :orgCode");
			countSql.append( " and org.orgCode = :orgCode");
			parameterMap.put("orgCode", departmentModel.getOrgCode());
		}
		if (null != departmentModel.getOrgName() && !StringUtils.isEmpty(departmentModel.getOrgName())) {
			dataSql.append(" and org.orgName like :orgName");
			countSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%"+departmentModel.getOrgName()+"%");
		}
		if (null != departmentModel.getOrgAlias() && !StringUtils.isEmpty(departmentModel.getOrgAlias())) {
			dataSql.append(" and org.orgAlias like :orgAlias");
			countSql.append(" and org.orgAlias like :orgAlias");
			parameterMap.put("orgAlias", "%"+departmentModel.getOrgAlias()+"%");
		}

		if (null != departmentModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(departmentModel.getOrgTypeId()))) {
			dataSql.append(" and org.orgTypeId = :orgTypeId");
			countSql.append(" and org.orgTypeId = :orgTypeId");
			parameterMap.put("orgTypeId", departmentModel.getOrgTypeId());
		}
		if (null != departmentModel.getInUse() && !StringUtils.isEmpty(String.valueOf(departmentModel.getInUse()))) {
			dataSql.append(" and org.inUse = :inUse");
			countSql.append(" and org.inUse = :inUse");
			parameterMap.put("inUse", departmentModel.getInUse());
		}
		if (null != departmentModel.getOrgCodes() && departmentModel.getOrgCodes().size() > 0) {
			dataSql.append( " and org.orgCode in :orgCodes");
			countSql.append( " and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", departmentModel.getOrgCodes());
		}
		
		
		if(StringUtils.isNotEmpty(departmentModel.getRentCode())){
			RentCondition<Department> rentCondition = new RentCondition<Department>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(departmentModel.getRentCode(),departmentModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		
		
		if (StringUtils.isNotEmpty(departmentModel.getOrderby())) {
			String value = SortParam.getSortParam(Department.class, departmentModel.getOrderby());
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
			int skip = departmentModel.getSkip();
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
	
	private String getOrgTypeCode(com.pcitc.fms.service.model.Department departmentModel) {
		if (departmentModel.getParentOrgTypeCode().endsWith("headquarters")) {
			return "1001";
		}
		if (departmentModel.getParentOrgTypeCode().endsWith("divisions")) {
			return "1002";
		}
		if (departmentModel.getParentOrgTypeCode().endsWith("enterprises")) {
			return "1003";
		}
		if (departmentModel.getParentOrgTypeCode().endsWith("offices")) {
			return "1004";
		}
		if (departmentModel.getParentOrgTypeCode().endsWith("produceFactories")) {
			return "1005";
		}
		if (departmentModel.getParentOrgTypeCode().endsWith("departments")) {
			return "1006";
		}
		if (departmentModel.getParentOrgTypeCode().endsWith("workshops")) {
			return "1007";
		}
		return departmentModel.getParentOrgTypeCode();
	}
	
}
