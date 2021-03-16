package com.pcitc.fms.dal.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.text.StrBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;

@Service
public class IcPipenettankCoefDaoImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public MyPageImpl findIcPipenettankCoefs(com.pcitc.fms.service.model.IcPipenettankCoef icPipenettankCoef, Pageable pageable){
		StrBuilder dataSql = new StrBuilder();
		dataSql.append(AreaNodeBasicSql.IcPipenettankCoef);

		String datacount = "select count(1) from IcPipenettankCoef t where 1=1 ";

		StringBuilder countSql = new StringBuilder();
		countSql.append(datacount);

		Map<String, Object> parameterMap = new HashMap<>();
		if (null != icPipenettankCoef.getPipenettankCoefId()) {
			dataSql.append(" and t.pipenettankCoefId = :pipenettankCoefId");
			countSql.append(" and t.pipenettankCoefId = :pipenettankCoefId");
			parameterMap.put("pipenettankCoefId", icPipenettankCoef.getPipenettankCoefId());
		}
		if (null != icPipenettankCoef.getInUse()) {
			dataSql.append(" and t.inUse = :inUse");
			countSql.append(" and t.inUse = :inUse");
			parameterMap.put("inUse",icPipenettankCoef.getInUse());
		}
		
		Query dataQuery = entityManager.createQuery(dataSql.toString());
		Query countQuery = entityManager.createQuery(countSql.toString());

		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = icPipenettankCoef.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		} 
		List<com.pcitc.fms.dal.pojo.IcPipenettankCoef> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}

}
