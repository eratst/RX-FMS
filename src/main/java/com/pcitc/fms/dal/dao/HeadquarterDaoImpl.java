/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: HeadquarterDaoImpl
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
import com.pcitc.fms.dal.pojo.Headquarter;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: HeadquarterDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class HeadquarterDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find headquarters page.
	 *
	 * @param headquarterModel the headquarter model
	 * @param pageable the pageable
	 * @return page
	 * @Title: findHeadquarters
	 * @Description: 条件查询, 根据组织机构名称与简称模糊查询
	 * @date 2017年11月21日
	 * @return: Page<com.pcitc.fms.dal.pojo.Headquarter>
	 * @author zhenqiang.zhao
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findHeadquarters(com.pcitc.fms.service.model.Headquarter headquarterModel, Pageable pageable) throws BusiException{
		
		String countBase = "select count(1) from Headquarter t,TPmOrg org,TPmOrgType orgType where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(countBase);
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.headquarters +" and 1 = 1");
		Map<String ,Object> parameterMap = new HashedMap();
		if (null != headquarterModel.getOrgCode()  && !StringUtils.isEmpty(headquarterModel.getOrgCode())) {
			dataSql.append( " and org.orgCode = :orgCode");
			countSql.append( " and org.orgCode = :orgCode");
			parameterMap.put("orgCode", headquarterModel.getOrgCode());
		}
		if (null != headquarterModel.getOrgName() && !StringUtils.isEmpty(headquarterModel.getOrgName())) {
			dataSql.append(" and org.orgName like :orgName");
			countSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%"+headquarterModel.getOrgName()+"%");
		}
		if (null != headquarterModel.getOrgAlias() && !StringUtils.isEmpty(headquarterModel.getOrgAlias())) {
			dataSql.append(" and org.orgAlias like :orgAlias");
			countSql.append(" and org.orgAlias like :orgAlias");
			parameterMap.put("orgAlias", "%"+headquarterModel.getOrgAlias()+"%");
		}
		if (null != headquarterModel.getOrgTypeName() && !StringUtils.isEmpty(headquarterModel.getOrgTypeName())) {
			dataSql.append(" and orgType.orgTypeName = :orgType");
			countSql.append(" and orgType.orgTypeName = :orgType");
			parameterMap.put("orgType", headquarterModel.getOrgTypeName());
		}
		if (null != headquarterModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(headquarterModel.getOrgTypeId()))) {
			dataSql.append(" and org.orgTypeId = :orgTypeId");
			countSql.append(" and org.orgTypeId = :orgTypeId");
			parameterMap.put("orgTypeId", headquarterModel.getOrgTypeId());
		}

		if (null != headquarterModel.getInUse() && !StringUtils.isEmpty(String.valueOf(headquarterModel.getInUse()))) {
			dataSql.append(" and org.inUse = :inUse");
			countSql.append(" and org.inUse = :inUse");
			parameterMap.put("inUse", headquarterModel.getInUse());
		}
		if (null != headquarterModel.getOrgCodes() && headquarterModel.getOrgCodes().size() > 0) {
			dataSql.append( " and org.orgCode in :orgCodes");
			countSql.append( " and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", headquarterModel.getOrgCodes());
		}
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(headquarterModel.getRentCode())){
			RentCondition<Headquarter> rentCondition = new RentCondition<Headquarter>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(headquarterModel.getRentCode(),headquarterModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(headquarterModel.getOrderby())) {
			String value = SortParam.getSortParam(Headquarter.class, headquarterModel.getOrderby());
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
			int skip = headquarterModel.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			MyPageImpl MyPageImpl = new MyPageImpl(dataQuery.getResultList(), pageable, count);
			MyPageImpl.setCount(count);	
			return MyPageImpl;
		}else{
			MyPageImpl MyPageImpl = new MyPageImpl(dataQuery.getResultList(), null, count);
			MyPageImpl.setCount(count);	
			return MyPageImpl;		
		}
	 
	}
	
}
