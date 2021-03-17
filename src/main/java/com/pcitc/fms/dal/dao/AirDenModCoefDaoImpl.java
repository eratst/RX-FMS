package com.pcitc.fms.dal.dao;

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
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.AirDenModCoef;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class AirDenModCoefDaoImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	public MyPageImpl getAllDenModCoefs(com.pcitc.fms.service.model.AirDenModCoef model, Pageable pageable) throws BusiException{
		
		StringBuilder dataSql = new StringBuilder();
		StringBuilder countSql = new StringBuilder();
		Map<String ,Object> parameterMap = new HashedMap();
		dataSql.append(AreaNodeBasicSql.airDenModCoef);
		countSql.append("select count(1) from AirDenModCoef a where 1=1 ");
		
		if(model.getInUse()!=null){
			dataSql.append(" and a.inUse = :inUse");
			countSql.append(" and a.inUse = :inUse");
			parameterMap.put("inUse", model.getInUse());
		}
		
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(AirDenModCoef.class, model.getOrderby());
			dataSql.append(value);
		}
		Query dataQuery = em.createQuery(dataSql.toString());
		Query countQuery = em.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
	           dataQuery.setParameter(parameter, parameterMap.get(parameter));
	           countQuery.setParameter(parameter, parameterMap.get(parameter));
	       }
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = model.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		List<com.pcitc.fms.dal.pojo.AirDenModCoef> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}

}
