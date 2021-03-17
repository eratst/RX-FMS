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
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.StanDen;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class StanDenDaoImpl {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @SuppressWarnings("unchecked")
	 public MyPageImpl findStanDens(com.pcitc.fms.service.model.StanDen stanDen, Pageable pageable) throws BusiException{
		
		String stanDens = "select count(1) from StanDen stanDen, MtrlType mtrlType where stanDen.mtrlTypeId = mtrlType.mtrlTypeId ";
		
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.stanDens);
		
		StringBuilder countSql = new StringBuilder();
		countSql.append(stanDens);
		
		Map<String ,Object> parameterMap = new HashedMap();
		
		if (null != stanDen.getMtrlTypeCode() && !StringUtils.isEmpty(stanDen.getMtrlTypeCode())) {
			countSql.append(" and mtrlType.mtrlTypeCode = :mtrlTypeCode");
			dataSql.append(" and mtrlType.mtrlTypeCode = :mtrlTypeCode");
			parameterMap.put("mtrlTypeCode", stanDen.getMtrlTypeCode());
		}
		if (null != stanDen.getInUse()
				&& !StringUtils.isEmpty(String.valueOf(stanDen.getInUse()))) {
			countSql.append(" and stanDen.inUse = :inUse");
			dataSql.append(" and stanDen.inUse = :inUse");
			parameterMap.put("inUse", stanDen.getInUse());
		}
		
		dataSql.append(" order by stanDen.mntDate desc");
		countSql.append(" order by stanDen.mntDate desc");
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			countQuery.setParameter(parameter, parameterMap.get(parameter));
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		// long count = dataQuery.getResultList().size();
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = stanDen.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new MyPageImpl(dataQuery.getResultList(), pageable, count);
		} else {
			return new MyPageImpl(dataQuery.getResultList(), null, count);
		}
	}
}
