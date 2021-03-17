package com.pcitc.fms.dal.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.CapacityUnit;

@Service
public class CapacityUnitDaoImpl {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Page<CapacityUnit> findCapacityUnits(com.pcitc.fms.service.model.CapacityUnit capacityUnit,
			Pageable pageable) {
		
		String capacityUnitCountSql = "select count(1) from CapacityUnit capacityUnit where 1=1 ";

		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.capacityUnit);

		StringBuilder countSql = new StringBuilder();
		countSql.append(capacityUnitCountSql);
		Map<String, Object> parameterMap = new HashMap<>();
		if (StringUtils.isNotEmpty(capacityUnit.getCapacityUnitCode())) {
			countSql.append(" and capacityUnit.capacityUnitCode = :capacityUnitCode");
			dataSql.append(" and capacityUnit.capacityUnitCode = :capacityUnitCode");
			parameterMap.put("capacityUnitCode", capacityUnit.getCapacityUnitCode());
		}
		if (StringUtils.isNotEmpty(capacityUnit.getCapacityUnitName())) {
			countSql.append(" and capacityUnit.capacityUnitName like :capacityUnitName");
			dataSql.append(" and capacityUnit.capacityUnitName like :capacityUnitName");
			parameterMap.put("capacityUnitName", "%" + capacityUnit.getCapacityUnitName() + "%");
		}
		if (null != capacityUnit.getInUse()) {
			countSql.append(" and capacityUnit.inUse = :inUse");
			dataSql.append(" and capacityUnit.inUse = :inUse");
			parameterMap.put("inUse", capacityUnit.getInUse());
		}
		if (null != capacityUnit.getCodeList() && capacityUnit.getCodeList().size() > 0) {
			countSql.append(" and capacityUnit.capacityUnitCode in :codeList");
			dataSql.append(" and capacityUnit.capacityUnitCode in :codeList");
			parameterMap.put("codeList", capacityUnit.getCodeList());
		}

		Query dataQuery = entityManager.createQuery(dataSql.toString());
		Query countQuery = entityManager.createQuery(countSql.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));

		}

		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			int skip = capacityUnit.getSkip();
			dataQuery.setFirstResult(skip);
			dataQuery.setMaxResults(pageable.getPageSize());
			return new PageImpl<CapacityUnit>(dataQuery.getResultList(), pageable, count);
		} else {
			return new PageImpl<CapacityUnit>(dataQuery.getResultList(), null, count);
		}
	}

}
