/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DivisionDaoImpl
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Division;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: DivisionDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class DivisionDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find divisions page.
	 *
	 * @param divisionModel the division model
	 * @param pageable the pageable
	 * @return page
	 * @Title: findDivisions
	 * @Description: 条件查询, 根据组织机构名称与简称模糊查询
	 * @date 2017年11月21日
	 * @return: Page<com.pcitc.fms.dal.pojo.Division>
	 * @author zhenqiang.zhao
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findDivisions(com.pcitc.fms.service.model.Division divisionModel, Pageable pageable) throws BusiException{
		StringBuilder countSql=new StringBuilder();
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.divisions +" and 1 = 1");
		
		Map<String ,Object> parameterMap = new HashedMap();
		
		//组织机构link
		if (StringUtils.isNotEmpty(divisionModel.getParentOrgCode()) && StringUtils.isNotEmpty(divisionModel.getParentOrgTypeCode())) {
			String parentOrgTypeCode = getOrgTypeCode(divisionModel);
			String parentSql = "select o1.ORG_ID from T_PM_ORG o1,T_PM_ORGTYPE ot1 where o1.ORG_CODE = '"+divisionModel.getParentOrgCode()+"' and o1.ORGTYPE_ID = ot1.ORGTYPE_ID and ot1.ORGTYPE_CODE = '"+parentOrgTypeCode+"' ";
			Query query = em.createNativeQuery(parentSql);
			List parentId = query.getResultList();
			
			dataSql.append( " and dtl.parentOrgId = "+parentId.get(0)+"");
			
			if (StringUtils.isNotEmpty(divisionModel.getBizCode())) {
				dataSql.append( " and biz.bizCode = :linkBizCode");
				parameterMap.put("linkBizCode", divisionModel.getBizCode());
			}
		}
		
		if (null != divisionModel.getOrgCode()  && !StringUtils.isEmpty(divisionModel.getOrgCode())) {
			dataSql.append( " and org.orgCode = :orgCode");
			parameterMap.put("orgCode", divisionModel.getOrgCode());
		}
		if (null != divisionModel.getOrgName() && !StringUtils.isEmpty(divisionModel.getOrgName())) {
			dataSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%"+divisionModel.getOrgName()+"%");
		}
		if (null != divisionModel.getOrgAlias() && !StringUtils.isEmpty(divisionModel.getOrgAlias())) {
			dataSql.append(" and org.orgAlias like  :orgAlias");
			parameterMap.put("orgAlias", "%"+divisionModel.getOrgAlias()+"%");
		}

		if (null != divisionModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(divisionModel.getOrgTypeId()))) {
			dataSql.append(" and org.orgTypeId = :orgTypeId");
			parameterMap.put("orgTypeId", divisionModel.getOrgTypeId());
		}
		if (null != divisionModel.getInUse() && !StringUtils.isEmpty(String.valueOf(divisionModel.getInUse()))) {
			dataSql.append(" and org.inUse = :inUse");
			parameterMap.put("inUse", divisionModel.getInUse());
		}
		if (null != divisionModel.getOrgCodes() && divisionModel.getOrgCodes().size() > 0) {
			dataSql.append( " and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", divisionModel.getOrgCodes());
		}
		boolean flag = new RentCondition<Division>().getRentCondition(divisionModel.getRentCode(),divisionModel.getBizCode(), dataSql, countSql, parameterMap, " and org.orgCode in :");
		if (flag == false) {
			return new MyPageImpl(new ArrayList<Division>(), null, 0L);
		}	
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(divisionModel.getRentCode())){
			RentCondition<Division> rentCondition = new RentCondition<Division>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(divisionModel.getRentCode(),divisionModel.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count =dataQuery.getResultList().size();
		int skip = divisionModel.getSkip();
		dataQuery.setFirstResult(skip);
		if(null != pageable){
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List resultList = dataQuery.getResultList();
		
		MyPageImpl myPageImpl = new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	 
	}

	private String getOrgTypeCode(com.pcitc.fms.service.model.Division divisionModel) {
		if (divisionModel.getParentOrgTypeCode().endsWith("headquarters")) {
			return "1001";
		}
		if (divisionModel.getParentOrgTypeCode().endsWith("divisions")) {
			return "1002";
		}
		if (divisionModel.getParentOrgTypeCode().endsWith("enterprises")) {
			return "1003";
		}
		if (divisionModel.getParentOrgTypeCode().endsWith("offices")) {
			return "1004";
		}
		if (divisionModel.getParentOrgTypeCode().endsWith("produceFactories")) {
			return "1005";
		}
		if (divisionModel.getParentOrgTypeCode().endsWith("departments")) {
			return "1006";
		}
		if (divisionModel.getParentOrgTypeCode().endsWith("workshops")) {
			return "1007";
		}
		return divisionModel.getParentOrgTypeCode();
	}
	
}
