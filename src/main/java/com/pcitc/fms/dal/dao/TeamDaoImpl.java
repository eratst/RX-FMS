/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TeamDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
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
import com.pcitc.fms.dal.pojo.Team;
import com.pcitc.fms.dal.pojo.Workshop;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: TeamDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class TeamDaoImpl {

	/**
	 * The Em.
	 */
	@PersistenceContext
    private EntityManager em;

	/**
	 * Find teams page.
	 *
	 * @param teamModel the team model
	 * @param pageable the pageable
	 * @return page
	 * @Title: findTeams
	 * @Description: 条件查询, 根据组织机构名称与简称模糊查询
	 * @date 2017年11月21日
	 * @return: Page<com.pcitc.fms.dal.pojo.Team>
	 * @author zhenqiang.zhao
	 * @throws BusiException 
	 */
	@SuppressWarnings("unchecked")
	public MyPageImpl findTeams(com.pcitc.fms.service.model.Team teamModel, Pageable pageable) throws BusiException{
		
		String countBase = "select count(1) from Team t,TPmOrg org,TPmOrgType orgType where org.orgTypeId = orgType.orgTypeId and t.orgId = org.orgId ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(countBase);
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.teams +" and 1 = 1");
		Map<String ,Object> parameterMap = new HashedMap();
		if (null != teamModel.getOrgCode()  && !StringUtils.isEmpty(teamModel.getOrgCode())) {
			dataSql.append( " and org.orgCode = :orgCode");
			countSql.append( " and org.orgCode = :orgCode");
			parameterMap.put("orgCode", teamModel.getOrgCode());
		}
		if (null != teamModel.getOrgName() && !StringUtils.isEmpty(teamModel.getOrgName())) {
			dataSql.append(" and org.orgName like :orgName");
			countSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%"+teamModel.getOrgName()+"%");
		}
		if (null != teamModel.getOrgAlias() && !StringUtils.isEmpty(teamModel.getOrgAlias())) {
			dataSql.append(" and org.orgAlias like :orgAlias");
			countSql.append(" and org.orgAlias like :orgAlias");
			parameterMap.put("orgAlias", "%"+teamModel.getOrgAlias()+"%");
		}
		if (null != teamModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(teamModel.getOrgTypeId()))) {
			dataSql.append(" and org.orgTypeId = :orgTypeId");
			countSql.append(" and org.orgTypeId = :orgTypeId");
			parameterMap.put("orgTypeId", teamModel.getOrgTypeId());
		}

		if (null != teamModel.getInUse() && !StringUtils.isEmpty(String.valueOf(teamModel.getInUse()))) {
			dataSql.append(" and org.inUse = :inUse");
			countSql.append(" and org.inUse = :inUse");
			parameterMap.put("inUse", teamModel.getInUse());
		}
		if (null != teamModel.getOrgCodes() && teamModel.getOrgCodes().size() > 0) {
			dataSql.append( " and org.orgCode in :orgCodes");
			countSql.append( " and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", teamModel.getOrgCodes());
		}
		//----------处理租户过滤
			if(StringUtils.isNotEmpty(teamModel.getRentCode())){
				RentCondition<Team> rentCondition = new RentCondition<Team>();
				String field=" org.orgCode ";
				String rentOrgCodes = rentCondition.getRentOrgCodeCondition(teamModel.getRentCode(),teamModel.getBizCode(),field);
				if(StringUtils.isNotEmpty(rentOrgCodes)){
					dataSql.append( " and "+rentOrgCodes);
					countSql.append( " and "+rentOrgCodes);
				}else{
					return new MyPageImpl(new ArrayList(), null, 0L);
				}
			}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(teamModel.getOrderby())) {
			String value = SortParam.getSortParam(Team.class, teamModel.getOrderby());
			dataSql.append(value);
		}
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
//		dataSql.append(" order by org.orgId");
		long count = (long) countQuery.getResultList().get(0);
		if(null != pageable){
			int skip = teamModel.getSkip();
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
	
}
