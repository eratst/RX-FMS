package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Enterprise;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class UserPositionDaoImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MyPageImpl getUserPositionByModel(
            com.pcitc.fms.service.model.UserPosition userPosition, Pageable pageable) throws BusiException {
	
		Map<String ,Object> parameterMap = new HashedMap();
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.UserPosition + " and 1=1");
		
		if(userPosition.getInUse()!=null){
			dataSql.append(" and up.inUse = :inUse");
			parameterMap.put("inUse", userPosition.getInUse());
		}
		
		if (StringUtils.isNotEmpty(userPosition.getPositionCode())) {
			dataSql.append(" and position.positionCode = :positionCode");
			parameterMap.put("positionCode", userPosition.getPositionCode());
		}
		
		if (StringUtils.isNotEmpty(userPosition.getPositionName())) {
			dataSql.append(" and position.positionName like :positionName");
			parameterMap.put("positionName", "%"+userPosition.getPositionName()+"%");
		}
		
		if (userPosition.getPositionCodes() != null && !userPosition.getPositionCodes().isEmpty()) {
			dataSql.append(" and position.positionCode in :positionCodes");
			parameterMap.put("positionCodes", userPosition.getPositionCodes());
		}
		
		if (StringUtils.isNotEmpty(userPosition.getOrgCode())) {
			dataSql.append(" and org.orgCode = :orgCode");
			parameterMap.put("orgCode", userPosition.getOrgCode());
		}
		
		if (StringUtils.isNotEmpty(userPosition.getOrgName())) {
			dataSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%"+userPosition.getOrgName()+"%");
		}
		
		if (userPosition.getOrgCodes() != null && !userPosition.getOrgCodes().isEmpty()) {
			dataSql.append(" and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", userPosition.getOrgCodes());
		}
		
		if (StringUtils.isNotEmpty(userPosition.getUserCode())) {
			dataSql.append(" and user.userCode = :userCode");
			parameterMap.put("userCode", userPosition.getUserCode());
		}
		
		if (StringUtils.isNotEmpty(userPosition.getUserName())) {
			dataSql.append(" and user.userName like :userName");
			parameterMap.put("userName", "%"+userPosition.getUserName()+"%");
		}
		
		if (userPosition.getUserCodes() != null && !userPosition.getUserCodes().isEmpty()) {
			dataSql.append(" and user.userCode in :userCodes");
			parameterMap.put("userCodes", userPosition.getUserCodes());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(userPosition.getRentCode())){
			RentCondition<Enterprise> rentCondition = new RentCondition<Enterprise>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(userPosition.getRentCode(),userPosition.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		Query dataQuery = em.createQuery(dataSql.toString());
		
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		
		List<com.pcitc.fms.dal.pojo.UserPosition> list = dataQuery.getResultList();
		long count = list.size();
		
		Integer skip = null;
		
		if(null != pageable){
		    skip = userPosition.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            
            MyPageImpl myPageImpl =new MyPageImpl(dataQuery.getResultList(), pageable,count);
            myPageImpl.setCount(count);
            return myPageImpl;
		} else {
			 MyPageImpl myPageImpl =new MyPageImpl(dataQuery.getResultList(), null,count);
	         myPageImpl.setCount(count);
	         return myPageImpl;
		}
	}

}
