package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TeamAndUser;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class TeamAndUserDaoImpl {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public MyPageImpl getTeamAndUsers(com.pcitc.fms.service.model.TeamAndUser teamAndUser, Pageable pageable) throws BusiException {

		String teamAndUsers = "select count(1) from TeamAndUser teamAndUser,User user,Team team,TPmOrg org,TPmOrg org1 "
				+ "where teamAndUser.userId =user.userId and teamAndUser.orgId = team.orgId and team.orgId =org.orgId and org1.orgId = user.orgId ";

		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.teamAndUsers);

		StringBuilder countSql = new StringBuilder();
		countSql.append(teamAndUsers);

		Map<String, Object> parameterMap = new HashMap<>();
		if (null != teamAndUser.getUserCode() && !StringUtils.isEmpty(teamAndUser.getUserCode())) {
			dataSql.append(" and user.userCode =:userCode");
			countSql.append(" and user.userCode =:userCode");
			parameterMap.put("userCode", teamAndUser.getUserCode());
		}
		if (null != teamAndUser.getUserName() && !StringUtils.isEmpty(teamAndUser.getUserName())) {
			dataSql.append(" and user.userName like:userName");
			countSql.append(" and user.userName like:userName");
			parameterMap.put("userName", "%" + teamAndUser.getUserName() + "%");
		}
		if (null != teamAndUser.getOrgCode() && !StringUtils.isEmpty(teamAndUser.getOrgCode())) {
			dataSql.append(" and team.orgCode =:orgCode");
			countSql.append(" and team.orgCode =:orgCode");
			parameterMap.put("orgCode", teamAndUser.getOrgCode());
		}
		if (null != teamAndUser.getOrgName() && !StringUtils.isEmpty(teamAndUser.getOrgName())) {
			dataSql.append(" and org.orgName like:orgName");
			countSql.append(" and org.orgName like:orgName");
			parameterMap.put("orgName", "%" + teamAndUser.getOrgName() + "%");
		}
		if (null != teamAndUser.getInUse()) {
			dataSql.append(" and teamAndUser.inUse =:inUse");
			countSql.append(" and teamAndUser.inUse =:inUse");
			parameterMap.put("inUse", teamAndUser.getInUse());
		}

		//----------处理租户过滤
		if(StringUtils.isNotEmpty(teamAndUser.getRentCode())){
			RentCondition<TeamAndUser> rentCondition = new RentCondition<TeamAndUser>();
			String field=" org1.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(teamAndUser.getRentCode(),teamAndUser.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(teamAndUser.getOrderby())) {
			String value = SortParam.getSortParam(TeamAndUser.class, teamAndUser.getOrderby());
			dataSql.append(value);
		}
		
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			countQuery.setParameter(parameter, parameterMap.get(parameter));
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = teamAndUser.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		} 
		List<com.pcitc.fms.dal.pojo.TeamAndUser> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
		

	}
}
