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

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.ManagementMtrl;

@Service
public class ManagementMtrlDaoImpl {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public Page<ManagementMtrl> findManagementMtrls(com.pcitc.fms.service.model.ManagementMtrl managementMtrl, Pageable pageable) {
		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.managementMtrls +" and 1 = 1");
		Map<String ,Object> parameterMap = new HashedMap();
	       if (null != managementMtrl.getMtrlCode() && !StringUtils.isEmpty(managementMtrl.getMtrlCode())) {
	           dataSql.append(" and mm.mtrlCode like :mtrlCode");
	           parameterMap.put("mtrlCode", "%"+managementMtrl.getMtrlCode()+"%");
	       }
	       if (null != managementMtrl.getMtrlTypeCode() && !StringUtils.isEmpty(managementMtrl.getMtrlTypeCode())) {
	           dataSql.append(" and mt.mtrlTypeCode like :mtrlTypeCode");
	           parameterMap.put("mtrlTypeCode", "%"+managementMtrl.getMtrlTypeCode()+"%");
	       }
	       if (null != managementMtrl.getInUse()) {
	    	   if (1 != managementMtrl.getInUse() || 0 != managementMtrl.getInUse()) {
	    		   dataSql.append(" and ds.dataStatus = :dataStatus");
		           parameterMap.put("dataStatus", managementMtrl.getInUse());
	    	   }
	       }
	       Query dataQuery = em.createQuery(dataSql.toString());
	       for (String parameter : parameterMap.keySet()) {
	           dataQuery.setParameter(parameter, parameterMap.get(parameter));
	       }
	       long count = dataQuery.getResultList().size();
	       if(null != pageable){
	           int skip = managementMtrl.getSkip();
	           dataQuery.setFirstResult(skip);
	           dataQuery.setMaxResults(pageable.getPageSize());
	           return new PageImpl<com.pcitc.fms.dal.pojo.ManagementMtrl>(dataQuery.getResultList(), pageable, count);
	       }else{
	           return new PageImpl<com.pcitc.fms.dal.pojo.ManagementMtrl>(dataQuery.getResultList(), null, count);

	       }
	}
}
