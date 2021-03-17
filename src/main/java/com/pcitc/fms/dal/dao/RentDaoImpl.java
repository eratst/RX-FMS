package com.pcitc.fms.dal.dao;

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
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.service.model.Rent;
import com.pcitc.imp.common.exception.BusiException;

@Service
public class RentDaoImpl {
	
	 @PersistenceContext
	 private EntityManager em;
	 
	 @SuppressWarnings("unchecked")
	 public Page<com.pcitc.fms.dal.pojo.Rent> findRents(com.pcitc.fms.service.model.Rent rent, Pageable pageable) throws BusiException{
		String sql=AreaNodeBasicSql.rents+"where 1=1 ";
		StringBuilder dataSql = new StringBuilder(sql);
		Map<String ,Object> parameterMap = new HashedMap();
		if(rent!=null){
			if(null != rent.getRentName() && !StringUtils.isEmpty(rent.getRentName())){
				dataSql.append(" and r.rentName like :rentName");
				parameterMap.put("rentName", "%"+rent.getRentName()+"%");
			}
			if(null != rent.getRentCode() && !StringUtils.isEmpty(rent.getRentCode())){
				dataSql.append(" and r.rentCode = :rentCode");
				parameterMap.put("rentCode", rent.getRentCode());
			}
			if (StringUtils.isNotEmpty(rent.getOrderby())) {
		    	  String value = SortParam.getSortParam(com.pcitc.fms.dal.pojo.Rent.class, rent.getOrderby());
		    	  dataSql.append(value);
		       }
		}
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
	           dataQuery.setParameter(parameter, parameterMap.get(parameter));
	       }
		
		long count = dataQuery.getResultList().size();
		
	       if(null != pageable){
	           int skip = rent.getSkip();
	           dataQuery.setFirstResult(skip);
	           dataQuery.setMaxResults(pageable.getPageSize());
	       }
	       
	       return new PageImpl<com.pcitc.fms.dal.pojo.Rent>(dataQuery.getResultList(), pageable, count);
	}
}
