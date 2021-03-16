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

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TankCnfg;
import com.pcitc.imp.common.exception.BusiException;

public class TankCnfgRepositoryImpl {

	@PersistenceContext
	private EntityManager em;

	String counts = "select count(1) from TankCnfg tc, Tank t, NodeDictionary n,AreaDictionary a,TPmOrg org where tc.nodeId=t.nodeId and t.nodeId=n.nodeId and a.areaDictionaryId=n.areaId and a.factoryId=org.orgId ";

	@SuppressWarnings("unchecked")
	public MyPageImpl findTankCnfgs(com.pcitc.fms.service.model.TankCnfg tankCnfg,
			Pageable pageable) throws BusiException {
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.tankCnfgs);

		StringBuilder countSql = new StringBuilder();
		countSql.append(counts);
		Map<String, Object> parameterMap = new HashedMap();
		if (null != tankCnfg.getTankCnfgId() && !StringUtils.isEmpty(String.valueOf(tankCnfg.getTankCnfgId()))) {
			dataSql.append(" and tc.tankCnfgId = :tankCnfgId");
			countSql.append(" and tc.tankCnfgId = :tankCnfgId");
			parameterMap.put("tankCnfgId", tankCnfg.getTankCnfgId());
		}
		if(StringUtils.isNotEmpty(tankCnfg.getNodeCode())){
			dataSql.append(" and t.nodeCode = :nodeCode");
			countSql.append(" and t.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", tankCnfg.getNodeCode());
		}
		if(tankCnfg.getInUse()!=null){
			dataSql.append(" and tc.inUse = :inUse");
			countSql.append(" and tc.inUse = :inUse");
			parameterMap.put("inUse", tankCnfg.getInUse());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(tankCnfg.getRentCode())){
			RentCondition<TankCnfg> rentCondition = new RentCondition<TankCnfg>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(tankCnfg.getRentCode(),tankCnfg.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		dataSql.append(" order by tc.tankCnfgId asc");
		countSql.append(" order by tc.tankCnfgId asc");
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = tankCnfg.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		} 
		List<com.pcitc.fms.dal.pojo.TankCnfg> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;	
	}
}
