package com.pcitc.fms.dal.dao;

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
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.MtrlMolar;
import com.pcitc.imp.common.exception.BusiException;

public class MtrlMolarDaoImpl {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @SuppressWarnings("unchecked")
	 public MyPageImpl findMtrlMolars(com.pcitc.fms.service.model.MtrlMolar mtrlMolar, Pageable pageable) throws BusiException{
		
		String mtrlMolars = "select count(1) from MtrlMolar mtrlMolar, Material material where mtrlMolar.mtrlId = material.mtrlId ";
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.mtrlMolars);
		
		StringBuilder countSql = new StringBuilder();
		countSql.append(mtrlMolars);
		
		Map<String ,Object> parameterMap = new HashedMap();
		
		if (null != mtrlMolar.getMtrlCode() && !StringUtils.isEmpty(mtrlMolar.getMtrlCode())) {
			countSql.append(" and material.mtrlCode = :mtrlCode");
			dataSql.append(" and material.mtrlCode = :mtrlCode");
			parameterMap.put("mtrlCode", mtrlMolar.getMtrlCode());
		}
		if (null != mtrlMolar.getInUse()
				&& !StringUtils.isEmpty(String.valueOf(mtrlMolar.getInUse()))) {
			countSql.append(" and mtrlMolar.inUse = :inUse");
			dataSql.append(" and mtrlMolar.inUse = :inUse");
			parameterMap.put("inUse", mtrlMolar.getInUse());
		}
		
		dataSql.append(" order by mtrlMolar.mntDate desc");
		countSql.append(" order by mtrlMolar.mntDate desc");
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			countQuery.setParameter(parameter, parameterMap.get(parameter));
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		// long count = dataQuery.getResultList().size();
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = mtrlMolar.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new MyPageImpl(dataQuery.getResultList(), pageable, count);
		} else {
			return new MyPageImpl(dataQuery.getResultList(), null, count);
		}
	}
}
