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
import com.pcitc.fms.dal.pojo.UnitAreaRel;

/**
 *
 * zhao.li
 */
@Service
public class UnitAreaRelDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Page<UnitAreaRel> findUnitAreaRels(com.pcitc.fms.service.model.UnitAreaRel UnitAreaRelModel, Pageable pageable) {
		String unitAreaRels = "select count(1) "
				+ "from UnitAreaRel unitAreaRel, Plant plant, Area area, UnitArea unitArea, BizorgMain biz "
				+ "where unitAreaRel.unitId = plant.plantId and area.areaId = plant.plantId "
				+ "and unitAreaRel.unitAreaId = unitArea.unitAreaId and unitAreaRel.bizId = biz.bizId";

		StringBuilder dataSql = new StringBuilder();
		dataSql.append(AreaNodeBasicSql.unitAreaRel);

		StringBuilder countSql = new StringBuilder();
		countSql.append(unitAreaRels);
		Map<String, Object> parameterMap = new HashMap<>();
		if (null != UnitAreaRelModel.getUnitAreaRelId()) {
			countSql.append(" and unitAreaRel.unitAreaRelId like :unitAreaRelId");
			dataSql.append(" and unitAreaRel.unitAreaRelId like :unitAreaRelId");
			parameterMap.put("unitAreaRelId", "%" + UnitAreaRelModel.getUnitAreaRelId() + "%");
		}
		if (null != UnitAreaRelModel.getUnitCode() && !StringUtils.isEmpty(UnitAreaRelModel.getUnitCode())) {
			countSql.append(" and plant.plantCode like :plantCode");
			dataSql.append(" and plant.plantCode like :plantCode");
			parameterMap.put("plantCode", "%" + UnitAreaRelModel.getUnitCode() + "%");
		}
		if (null != UnitAreaRelModel.getUnitAreaCode() && !StringUtils.isEmpty(UnitAreaRelModel.getUnitAreaCode())) {
			countSql.append(" and unitArea.unitAreaCode like :unitAreaCode");
			dataSql.append(" and unitArea.unitAreaCode like :unitAreaCode");
			parameterMap.put("unitAreaCode", "%" + UnitAreaRelModel.getUnitAreaCode() + "%");
		}
		if (null != UnitAreaRelModel.getUnitName() && !StringUtils.isEmpty(UnitAreaRelModel.getUnitName())) {
			countSql.append(" and plant.plantName like :plantName");
			dataSql.append(" and plant.plantName like :plantName");
			parameterMap.put("plantName", "%" + UnitAreaRelModel.getUnitName() + "%");
		}
		if (null != UnitAreaRelModel.getUnitAreaName() && !StringUtils.isEmpty(UnitAreaRelModel.getUnitAreaName())) {
			countSql.append(" and unitArea.unitAreaName like :unitAreaName");
			dataSql.append(" and unitArea.unitAreaName like :unitAreaName");
			parameterMap.put("unitAreaName", "%" + UnitAreaRelModel.getUnitAreaName() + "%");
		}
		if (null != UnitAreaRelModel.getBizCode() && !StringUtils.isEmpty(UnitAreaRelModel.getBizCode())) {
			countSql.append(" and biz.bizCode like :bizCode");
			dataSql.append(" and biz.bizCode like :bizCode");
			parameterMap.put("bizCode", "%" + UnitAreaRelModel.getBizCode() + "%");
		}
        if (UnitAreaRelModel.getDataStatus() != null) {
            countSql.append(" and unitAreaRel.dataStatus = :dataStatus");
            dataSql.append(" and unitAreaRel.dataStatus = :dataStatus");
            parameterMap.put("dataStatus", + UnitAreaRelModel.getDataStatus());
        }

        dataSql.append(" order by unitAreaRel.sortNum asc");
        countSql.append(" order by unitAreaRel.sortNum asc");

		
        Query dataQuery = entityManager.createQuery(dataSql.toString());
        Query countQuery = entityManager.createQuery(countSql.toString());
        for (String parameter : parameterMap.keySet()) {
            countQuery.setParameter(parameter, parameterMap.get(parameter));
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }

        long count = (long) countQuery.getResultList().get(0);
        if (null != pageable) {
            Integer skip = UnitAreaRelModel.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<UnitAreaRel>(dataQuery.getResultList(), pageable, count);
        } else {
            return new PageImpl<UnitAreaRel>(dataQuery.getResultList(),pageable,count);
        }
	}
}
