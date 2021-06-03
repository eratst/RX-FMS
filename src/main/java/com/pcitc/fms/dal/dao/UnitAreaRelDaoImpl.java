package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.UnitAreaRel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Service
public class UnitAreaRelDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Page<UnitAreaRel> findUnitAreaRels(com.pcitc.fms.service.model.UnitAreaRel UnitAreaRelModel, Pageable pageable) {
        String unitAreaRels = "select count(1) "
                + " from UnitAreaRel unitAreaRel,UnitArea unitArea, BizorgMain biz "
                + " where unitAreaRel.unitAreaId = unitArea.unitAreaId and unitAreaRel.bizId = biz.bizId";

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

        if (null != UnitAreaRelModel.getAreaCode() && !StringUtils.isEmpty(UnitAreaRelModel.getAreaCode())) {
            countSql.append(" and (unitAreaRel.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaCode like :areaCode) " +
                    "or unitAreaRel.areaId in (select plantId from Plant unit where unit.plantCode like :areaCode))");
            dataSql.append("  and (unitAreaRel.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaCode like :areaCode) " +
                    "or unitAreaRel.areaId in (select plantId from Plant unit where unit.plantCode like :areaCode))");
            parameterMap.put("areaCode", "%" + UnitAreaRelModel.getAreaCode() + "%");
        }

        if (null != UnitAreaRelModel.getAreaName() && !StringUtils.isEmpty(UnitAreaRelModel.getAreaName())) {
            countSql.append(" and (unitAreaRel.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaName like :areaName) " +
                    "or unitAreaRel.areaId in (select areaId from Plant unit where unit.areaCode in " +
                    "(select area.areaCode from Area area where area.areaName like :areaName)))");
            dataSql.append("  and (unitAreaRel.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaName like :areaName) " +
                    "or unitAreaRel.areaId in (select areaId from Plant unit where unit.areaCode in " +
                    "(select area.areaCode from Area area where area.areaName like :areaName)))");
            parameterMap.put("areaName", "%" + UnitAreaRelModel.getAreaName() + "%");
        }

        if (null != UnitAreaRelModel.getAreaAlias() && !StringUtils.isEmpty(UnitAreaRelModel.getAreaAlias())) {
            countSql.append(" and (unitAreaRel.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaAlias like :areaAlias) or unitAreaRel.areaId in (select areaId from Plant unit where unit.areaCode in (select area.areaCode from Area area where area.areaAlias like :areaAlias)))");
            dataSql.append("  and (unitAreaRel.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaAlias like :areaAlias) or unitAreaRel.areaId in (select areaId from Plant unit where unit.areaCode in (select area.areaCode from Area area where area.areaAlias like :areaAlias)))");
            parameterMap.put("areaAlias", "%" + UnitAreaRelModel.getAreaAlias() + "%");
        }

        if (null != UnitAreaRelModel.getUnitAreaCode() && !StringUtils.isEmpty(UnitAreaRelModel.getUnitAreaCode())) {
            countSql.append(" and unitArea.unitAreaCode like :unitAreaCode");
            dataSql.append(" and unitArea.unitAreaCode like :unitAreaCode");
            parameterMap.put("unitAreaCode", "%" + UnitAreaRelModel.getUnitAreaCode() + "%");
        }

        if (null != UnitAreaRelModel.getUnitAreaName() && !StringUtils.isEmpty(UnitAreaRelModel.getUnitAreaName())) {
            countSql.append(" and unitArea.unitAreaName like :unitAreaName");
            dataSql.append(" and unitArea.unitAreaName like :unitAreaName");
            parameterMap.put("unitAreaName", "%" + UnitAreaRelModel.getUnitAreaName() + "%");
        }

        if (null != UnitAreaRelModel.getUnitAreaAlias() && !StringUtils.isEmpty(UnitAreaRelModel.getUnitAreaAlias())) {
            countSql.append(" and unitArea.unitAreaAlias like :unitAreaAlias");
            dataSql.append(" and unitArea.unitAreaAlias like :unitAreaAlias");
            parameterMap.put("unitAreaAlias", "%" + UnitAreaRelModel.getUnitAreaAlias() + "%");
        }

        if (null != UnitAreaRelModel.getBizCode() && !StringUtils.isEmpty(UnitAreaRelModel.getBizCode())) {
            countSql.append(" and biz.bizCode like :bizCode");
            dataSql.append(" and biz.bizCode like :bizCode");
            parameterMap.put("bizCode", "%" + UnitAreaRelModel.getBizCode() + "%");
        }

        if (UnitAreaRelModel.getDataStatus() != null) {
            countSql.append(" and unitAreaRel.dataStatus = :dataStatus");
            dataSql.append(" and unitAreaRel.dataStatus = :dataStatus");
            parameterMap.put("dataStatus", +UnitAreaRelModel.getDataStatus());
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
            return new PageImpl<UnitAreaRel>(dataQuery.getResultList(), pageable, count);
        }
    }
}
