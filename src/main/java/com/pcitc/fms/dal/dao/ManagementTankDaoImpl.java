package com.pcitc.fms.dal.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.ManagementTank;

@Service
public class ManagementTankDaoImpl {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ManagementTankDao managementTankDao;

	@SuppressWarnings("unchecked")
	public Page<ManagementTank> findManagementTanks(com.pcitc.fms.service.model.ManagementTank managementTank,
			Pageable pageable) {

		Map<String, Object> parameterMap = new HashedMap();
		StringBuffer dataSql = new StringBuffer();

		List<BigDecimal> orgIds = new ArrayList<>();
		List<String> orgCodesTemp = new ArrayList<>();
		List<String> orgCodes = new ArrayList<>();

		if (null != managementTank.getOrgCodeList() && !managementTank.getOrgCodeList().isEmpty()) {
			orgIds = managementTankDao.getOrgIds(managementTank.getOrgCodeList());
			for (BigDecimal orgId : orgIds) {
				orgCodesTemp = managementTankDao.getOrgCodes(orgId.intValue());
				orgCodes.addAll(orgCodesTemp);
			}
		}

		dataSql.append(AreaNodeBasicSql.managementTank);

		if (null != managementTank.getBizCode() && !StringUtils.isEmpty(managementTank.getBizCode())) {
			dataSql.append(" and bizMain.bizCode = :bizCode");
			parameterMap.put("bizCode", managementTank.getBizCode());
		} else {
			dataSql.append(" and bizMain.bizCode = :bizCode");
			parameterMap.put("bizCode", "standard");
		}

		if (null != orgCodes && !orgCodes.isEmpty()) {
			dataSql.append(" and org.orgCode in :orgCodes");
			parameterMap.put("orgCodes", orgCodes);
		}

		if (null != managementTank.getTankAreaList() && !managementTank.getTankAreaList().isEmpty()) {
			dataSql.append(" and tankArea.tankAreaCode in :tankAreaCodes");
			parameterMap.put("tankAreaCodes", managementTank.getTankAreaList());
		}

		if (null != managementTank.getNodeCodeList() && !managementTank.getNodeCodeList().isEmpty()) {
			dataSql.append(" and tank.nodeCode in :nodeCodes");
			parameterMap.put("nodeCodes", managementTank.getNodeCodeList());
		}

		Integer inUse = managementTank.getInUse();
		if (null != inUse) {
			if (1 == inUse || 0 == inUse) {
				dataSql.append(" and node.dataStatus = :dataStatus");
				parameterMap.put("dataStatus", managementTank.getInUse());
			}
		}

		dataSql.append(" order by tank.nodeCode");

		Query dataQuery = em.createQuery(dataSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}

		long total = dataQuery.getResultList().size();

		if (null != pageable) {
			dataQuery.setFirstResult(managementTank.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());
		}
		return new PageImpl<com.pcitc.fms.dal.pojo.ManagementTank>(dataQuery.getResultList(), pageable, total);
	}
}
