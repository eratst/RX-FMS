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
import com.pcitc.fms.dal.pojo.SamplePoint;
import com.pcitc.fms.dal.pojo.User;
import com.pcitc.imp.common.exception.BusiException;


@Service
public class UserDaoImpl {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public MyPageImpl findUsers(com.pcitc.fms.service.model.User user, Pageable pageable) throws BusiException {
		String users = "select count(1) from User user,TPmOrg org where user.orgId=org.orgId";

		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.users + " and 1=1");

		StringBuilder countSql = new StringBuilder();
		countSql.append(users + " and 1=1");

		Map<String, Object> parameterMap = new HashMap<>();
		if (null != user.getUserCode() && !StringUtils.isEmpty(user.getUserCode())) {
			countSql.append(" and user.userCode = :userCode");
			dataSql.append(" and user.userCode = :userCode");
			parameterMap.put("userCode", user.getUserCode());
		}
		if (null != user.getUserName() && !StringUtils.isEmpty(user.getUserName())) {
			countSql.append(" and user.userName like :userName");
			dataSql.append(" and user.userName like :userName");
			parameterMap.put("userName", "%" + user.getUserName() + "%");
		}
		if (null != user.getOrgAlias() && !StringUtils.isEmpty(user.getOrgAlias())) {
			countSql.append(" and org.orgAlias like :orgAlias");
			dataSql.append(" and org.orgAlias like :orgAlias");
			parameterMap.put("orgAlias", "%" + user.getOrgAlias() + "%");
		}
		if (null != user.getOrgName() && !StringUtils.isEmpty(user.getOrgName())) {
			countSql.append(" and org.orgName like :orgName");
			dataSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%" + user.getOrgName() + "%");
		}
		if (null != user.getOrgCode() && !StringUtils.isEmpty(user.getOrgCode())) {
			countSql.append(" and org.orgCode = :orgCode");
			dataSql.append(" and org.orgCode = :orgCode");
			parameterMap.put("orgCode", user.getOrgCode() );
		}
		if (null != user.getOrgCodes() && user.getOrgCodes().size()>0) {
			countSql.append(" and org.orgCode in :orgCodes");
			dataSql.append(" and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", user.getOrgCodes() );
		}
		if (null != user.getInUse() && !StringUtils.isEmpty(String.valueOf(user.getInUse()))) {
			countSql.append(" and user.inUse = :inUse");
			dataSql.append(" and user.inUse = :inUse");
			parameterMap.put("inUse", user.getInUse());
		}
		if (null != user.getCodeList() && !user.getCodeList().isEmpty()) {
			countSql.append(" and user.userCode in :codeList");
			dataSql.append(" and user.userCode in :codeList");
			parameterMap.put("codeList", user.getCodeList());
		}
		
		//----------处理租户过滤
			if(StringUtils.isNotEmpty(user.getRentCode())){
				RentCondition<User> rentCondition = new RentCondition<User>();
				String field=" org.orgCode ";
				String rentOrgCodes = rentCondition.getRentOrgCodeCondition(user.getRentCode(),user.getBizCode(),field);
				if(StringUtils.isNotEmpty(rentOrgCodes)){
					dataSql.append( " and "+rentOrgCodes);
					countSql.append( " and "+rentOrgCodes);
				}else{
					return new MyPageImpl(new ArrayList(), null, 0L);
				}
			}
		//----------处理租户过滤
		
		if (StringUtils.isNotEmpty(user.getOrderby())) {
			String value = SortParam.getSortParam(User.class, user.getOrderby());
			dataSql.append(value);
		}

		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			countQuery.setParameter(parameter, parameterMap.get(parameter));
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		// long count = dataQuery.getResultList().size();
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = user.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		} 
		List<com.pcitc.fms.dal.pojo.User> list = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(list, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
}
