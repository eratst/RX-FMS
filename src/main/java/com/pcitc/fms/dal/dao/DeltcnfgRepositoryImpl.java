package com.pcitc.fms.dal.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;

public class DeltcnfgRepositoryImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	
	public MyPageImpl findAllDeltCnfgs(com.pcitc.fms.service.model.Deltcnfg deltcnfgModel,
			Pageable pageable){
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.deltcnfgs);
		StringBuilder countSql = new StringBuilder();
		countSql.append("select count(1) from Deltcnfg d where 1=1 ");
		Map<String, Object> parameterMap = new HashedMap();
		
		if(deltcnfgModel.getInUse()!=null){
			dataSql.append(" and d.inUse in :inUse");
			countSql.append(" and d.inUse in :inUse");
			parameterMap.put("inUse", deltcnfgModel.getInUse());
		}
		
		
		dataSql.append(" order by d.deltcnfgId asc");
		countSql.append(" order by d.deltcnfgId asc");
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = deltcnfgModel.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		} 
		List<com.pcitc.fms.dal.pojo.Deltcnfg> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}

}
