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
public class PositionOrgDaoImpl {
	
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MyPageImpl findPagePositionOrgs(com.pcitc.fms.service.model.PositionOrg positionOrg, Pageable pageable) throws BusiException{

	
		Map<String ,Object> parameterMap = new HashedMap();
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.PositionOrg +" and 1 = 1");
		
		if(positionOrg.getInUse()!=null){
			dataSql.append(" and po.inUse = :inUse");
			parameterMap.put("inUse", positionOrg.getInUse());
		}
		
		if (StringUtils.isNotEmpty(positionOrg.getPositionCode())) {
			dataSql.append(" and position.positionCode = :positionCode");
			parameterMap.put("positionCode", positionOrg.getPositionCode());
		}
		
		if (StringUtils.isNotEmpty(positionOrg.getPositionName())) {
			dataSql.append(" and position.positionName like :positionName");
			parameterMap.put("positionName", "%"+positionOrg.getPositionName()+"%");
		}
		
		if (StringUtils.isNotEmpty(positionOrg.getPositionAlias())) {
			dataSql.append(" and position.positionAlias like :positionAlias");
			parameterMap.put("positionAlias", "%"+positionOrg.getPositionAlias()+"%");
		}
		
		if (positionOrg.getPositionCodes() != null && !positionOrg.getPositionCodes().isEmpty()) {
			dataSql.append(" and position.positionCode in :positionCodes");
			parameterMap.put("positionCodes", positionOrg.getPositionCodes());
		}
		
		if (StringUtils.isNotEmpty(positionOrg.getOrgCode())) {
			dataSql.append(" and org.orgCode = :orgCode");
			parameterMap.put("orgCode", positionOrg.getOrgCode());
		}
		
		if (StringUtils.isNotEmpty(positionOrg.getOrgName())) {
			dataSql.append(" and org.orgName like :orgName");
			parameterMap.put("orgName", "%"+positionOrg.getOrgName()+"%");
		}
		
		if (StringUtils.isNotEmpty(positionOrg.getOrgAlias())) {
			dataSql.append(" and org.orgAlias like :orgAlias");
			parameterMap.put("orgAlias", "%"+positionOrg.getOrgAlias()+"%");
		}
		
		if (positionOrg.getOrgCodes() != null && !positionOrg.getOrgCodes().isEmpty()) {
			dataSql.append(" and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", positionOrg.getOrgCodes());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(positionOrg.getRentCode())){
			RentCondition<Enterprise> rentCondition = new RentCondition<Enterprise>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(positionOrg.getRentCode(),positionOrg.getBizCode(),field);
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
		
		List<com.pcitc.fms.dal.pojo.PositionOrg> list = dataQuery.getResultList();
		long count = list.size();
		
		Integer skip = null;
		
		if(null != pageable){
		    skip = positionOrg.getSkip();
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
