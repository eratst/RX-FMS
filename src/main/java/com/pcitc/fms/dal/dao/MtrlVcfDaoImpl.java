package com.pcitc.fms.dal.dao;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.imp.common.exception.BusiException;

public class MtrlVcfDaoImpl {
	
	@PersistenceContext
	 private EntityManager em;
	
	public MyPageImpl findAllDeltCnfgs(com.pcitc.fms.service.model.MtrlVcf model, Pageable pageable) throws BusiException{

		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.mtrlVcf);
		
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(1) from MtrlVcf mv,Material m where mv.mtrlId=m.mtrlId ");
		
		Map<String ,Object> parameterMap = new HashedMap();
		
		if (null != model.getMtrlCode() && !StringUtils.isEmpty(model.getMtrlCode())) {
			countSql.append(" and m.mtrlCode = :mtrlCode");
			dataSql.append(" and m.mtrlCode = :mtrlCode");
			parameterMap.put("mtrlCode", model.getMtrlCode());
		}
		if (null != model.getInUse()
				&& !StringUtils.isEmpty(String.valueOf(model.getInUse()))) {
			countSql.append(" and mv.inUse = :inUse");
			dataSql.append(" and mv.inUse = :inUse");
			parameterMap.put("inUse", model.getInUse());
		}
		
		dataSql.append(" order by mv.mntDate desc");
		countSql.append(" order by mv.mntDate desc");
		
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			countQuery.setParameter(parameter, parameterMap.get(parameter));
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		// long count = dataQuery.getResultList().size();
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = model.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new MyPageImpl(dataQuery.getResultList(), pageable, count);
		} else {
			return new MyPageImpl(dataQuery.getResultList(), null, count);
		}
	
	}


}
