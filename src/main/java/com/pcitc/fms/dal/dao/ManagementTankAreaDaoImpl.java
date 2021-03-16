package com.pcitc.fms.dal.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.ManagementTankArea;

@Service
public class ManagementTankAreaDaoImpl {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ManagementTankAreaDao ManagementTankAreaDao;

	@SuppressWarnings("unchecked")
	public Page<ManagementTankArea> getManagementTankAreas(
			com.pcitc.fms.service.model.ManagementTankArea managementTankArea, Pageable pageable) {
		StringBuilder dataSql = new StringBuilder();
		Map<String ,Object> parameterMap = new HashedMap();
		List<BigDecimal> orgIds=new ArrayList<BigDecimal>();
		List<String> orgTem=new ArrayList<String>();
		List<String> orgCodes= new ArrayList<String>();
		dataSql.append(AreaNodeBasicSql.managementTankAreas);
		if(managementTankArea.getOrgCodeList()!=null && !managementTankArea.getOrgCodeList().isEmpty()){
			orgIds= ManagementTankAreaDao.getOrgIds(managementTankArea.getOrgCodeList());
			for(BigDecimal orgId : orgIds){
				orgTem=ManagementTankAreaDao.getOrgCodes(orgId.intValue());
				orgCodes.addAll(orgTem);
			}
			dataSql.append(" and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", orgCodes);
		}
		if(managementTankArea.getInUse()!=null){
			if(managementTankArea.getInUse()!=-1){
				dataSql.append(" and a.enabled = :enabled");
				parameterMap.put("enabled", managementTankArea.getInUse());
			}
		}
		if(managementTankArea.getTankAreaCodeList()!=null && !managementTankArea.getTankAreaCodeList().isEmpty()){
			dataSql.append(" and mt.areaCode in :areaCode");
			parameterMap.put("areaCode", managementTankArea.getTankAreaCodeList());
		}
		if(managementTankArea.getBizCode()!=null && !managementTankArea.getBizCode().isEmpty()){
			dataSql.append(" and bizMain.bizCode =:bizCode");
			parameterMap.put("bizCode", managementTankArea.getBizCode());
		}
		
//		dataSql.append(" order by mt.areaCode");
		
		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
	           dataQuery.setParameter(parameter, parameterMap.get(parameter));
	       }
		long count = dataQuery.getResultList().size();
		if (null != pageable) {
			int skip = managementTankArea.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<com.pcitc.fms.dal.pojo.ManagementTankArea>(dataQuery.getResultList(), pageable, count);
		} else {
			return new PageImpl<com.pcitc.fms.dal.pojo.ManagementTankArea>(dataQuery.getResultList(), null, count);
		}
	}
}
