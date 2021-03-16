package com.pcitc.fms.dal.dao;

import java.util.ArrayList;
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
import com.pcitc.fms.dal.pojo.GlbPreCoef;
import com.pcitc.fms.dal.pojo.TankCnfg;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class GlbPreCoefDaoImpl {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @SuppressWarnings("unchecked")
	 public MyPageImpl findGlbPreCoefs(com.pcitc.fms.service.model.GlbPreCoef glbPreCoef, Pageable pageable) throws BusiException{
		
		String glbPreCoefs = "select count(1)  from GlbPreCoef glbPreCoef, Tank tank, NodeDictionary node, AreaDictionary area, TPmOrg org where glbPreCoef.nodeId = tank.nodeId and tank.nodeId = node.nodeId and node.areaId = area.areaDictionaryId and area.factoryId = org.orgId ";
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.glbPreCoefs);
		
		StringBuilder countSql = new StringBuilder();
		countSql.append(glbPreCoefs);
		
		Map<String ,Object> parameterMap = new HashedMap();
		
		if (null != glbPreCoef.getNodeCode() && !glbPreCoef.getNodeCode().isEmpty()) {
			dataSql.append(" and tank.nodeCode = :nodeCode");
			countSql.append(" and tank.nodeCode = :nodeCode");
			parameterMap.put("nodeCode", glbPreCoef.getNodeCode());
		}
		
		if (null != glbPreCoef.getNodeName() && !glbPreCoef.getNodeName().isEmpty()) {
			dataSql.append(" and node.nodeName like :nodeName");
			countSql.append(" and node.nodeName like :nodeName");
			parameterMap.put("nodeName", "%"+glbPreCoef.getNodeName()+"%");
		}
		
		if (null != glbPreCoef.getInUse()
				&& !StringUtils.isEmpty(String.valueOf(glbPreCoef.getInUse()))) {
			countSql.append(" and glbPreCoef.inUse = :inUse");
			dataSql.append(" and glbPreCoef.inUse = :inUse");
			parameterMap.put("inUse", glbPreCoef.getInUse());
		}
		
		//----------处理租户过滤
		if(StringUtils.isNotEmpty(glbPreCoef.getRentCode())){
			RentCondition<GlbPreCoef> rentCondition = new RentCondition<GlbPreCoef>();
			String field=" org.orgCode ";
			String rentOrgCodes = rentCondition.getRentOrgCodeCondition(glbPreCoef.getRentCode(),glbPreCoef.getBizCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append( " and "+rentOrgCodes);
				countSql.append( " and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
		//----------处理租户过滤
		
		
		dataSql.append(" order by glbPreCoef.mntDate desc");
		countSql.append(" order by glbPreCoef.mntDate desc");
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			countQuery.setParameter(parameter, parameterMap.get(parameter));
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		// long count = dataQuery.getResultList().size();
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = glbPreCoef.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new MyPageImpl(dataQuery.getResultList(), pageable, count);
		} else {
			return new MyPageImpl(dataQuery.getResultList(), null, count);
		}
	}
}
