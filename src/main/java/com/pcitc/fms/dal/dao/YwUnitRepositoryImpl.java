package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.YwUnit;
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
public class YwUnitRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Page<YwUnit> findYwUnits(com.pcitc.fms.service.model.YwUnit YwUnitModel, Pageable pageable) {
        String ywUnitCount = "select count(1) "
                + " from YwUnit ywUnit,Org org, BizorgMain biz ,UnitType unitType,Technic technic,Rent rent  "
                + " where ywUnit.orgId = org.orgId and ywUnit.bizId = biz.bizId and ywUnit.unitTypeId=unitType.unitTypeId "
                + "and ywUnit.technicId=technic.technicId and ywUnit.rentId=rent.rentId ";

        StringBuilder dataSql = new StringBuilder();
        dataSql.append(AreaNodeBasicSql.ywUnit);

        StringBuilder countSql = new StringBuilder();
        countSql.append(ywUnitCount);
        Map<String, Object> parameterMap = new HashMap<>();
        if (null != YwUnitModel.getOrgAlias() && !StringUtils.isEmpty(YwUnitModel.getOrgAlias())) {
            countSql.append(" and org.orgAlias like :orgAlias");
            dataSql.append(" and org.orgAlias like :orgAlias");
            parameterMap.put("orgAlias", "%" + YwUnitModel.getOrgAlias() + "%");
        }

        if (null != YwUnitModel.getBizCode() && !StringUtils.isEmpty(YwUnitModel.getBizCode())) {
            countSql.append(" and biz.bizCode = :bizCode");
            dataSql.append(" and biz.bizCode = :bizCode");
            parameterMap.put("bizCode", YwUnitModel.getBizCode());
        }

        if (null != YwUnitModel.getOrgName() && !StringUtils.isEmpty(YwUnitModel.getOrgName())) {
            countSql.append(" and org.orgName in :orgName");
            dataSql.append(" and org.orgName in :orgName");
            parameterMap.put("orgName", YwUnitModel.getOrgName());
        }

        if (null != YwUnitModel.getOrgCode() && !StringUtils.isEmpty(YwUnitModel.getOrgCode())) {
            countSql.append(" and org.orgCode in :orgCode");
            dataSql.append(" and org.orgCode in :orgCode");
            parameterMap.put("orgCode", YwUnitModel.getOrgCode());
        }

        if (null != YwUnitModel.getAreaCode() && !StringUtils.isEmpty(YwUnitModel.getAreaCode())) {
            countSql.append(" and ywUnit.areaCode like :areaCode");
            dataSql.append(" and ywUnit.areaCode like :areaCode");
            parameterMap.put("areaCode", "%" + YwUnitModel.getAreaCode() + "%");
        }

        if (null != YwUnitModel.getAreaName() && !StringUtils.isEmpty(YwUnitModel.getAreaName())) {
            countSql.append(" and ywUnit.areaName like :areaName");
            dataSql.append(" and ywUnit.areaName like :areaName");
            parameterMap.put("areaName", "%" + YwUnitModel.getAreaName() + "%");
        }
        if (null != YwUnitModel.getAreaAlias() && !StringUtils.isEmpty(YwUnitModel.getAreaAlias())) {
            countSql.append(" and ywUnit.areaAlias like :areaAlias");
            dataSql.append(" and ywUnit.areaAlias like :areaAlias");
            parameterMap.put("areaAlias", "%" + YwUnitModel.getAreaAlias() + "%");
        }

        if (null != YwUnitModel.getDataStatus()
                && !StringUtils.isEmpty(String.valueOf(YwUnitModel.getDataStatus()))) {
            countSql.append(" and ywUnit.dataStatus = :dataStatus");
            dataSql.append(" and ywUnit.dataStatus = :dataStatus");
            parameterMap.put("dataStatus", YwUnitModel.getDataStatus());
        }
        if (null != YwUnitModel.getCodeList() && !YwUnitModel.getCodeList().isEmpty()) {
            countSql.append(" and ywUnit.areaCode in :codeList");
            dataSql.append(" and ywUnit.areaCode in :codeList");
            parameterMap.put("codeList", YwUnitModel.getCodeList());
        }

        if (null != YwUnitModel.getUnitTypeName() && !StringUtils.isEmpty(YwUnitModel.getUnitTypeName())) {
            countSql.append(" and unitType.unitTypeName like :unitTypeName");
            dataSql.append(" and unitType.unitTypeName like :unitTypeName");
            parameterMap.put("unitTypeName", "%" + YwUnitModel.getUnitTypeName() + "%");
        }
        if (null != YwUnitModel.getTechnicName() && !StringUtils.isEmpty(YwUnitModel.getTechnicName())) {
            countSql.append(" and technic.technicName like :technicName");
            dataSql.append(" and technic.technicName like :technicName");
            parameterMap.put("technicName", "%" + YwUnitModel.getTechnicName() + "%");
        }

        if (null != YwUnitModel.getRentCode() && !StringUtils.isEmpty(YwUnitModel.getRentCode())) {
            dataSql.append(" and rent.rentCode = :rentCode");
            countSql.append(" and rent.rentCode = :rentCode");
            parameterMap.put("rentCode", YwUnitModel.getRentCode());
        }

        dataSql.append(" order by ywUnit.sortNum asc");
        countSql.append(" order by ywUnit.sortNum asc");


        Query dataQuery = entityManager.createQuery(dataSql.toString());
        Query countQuery = entityManager.createQuery(countSql.toString());
        for (String parameter : parameterMap.keySet()) {
            countQuery.setParameter(parameter, parameterMap.get(parameter));
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        long count = (long) countQuery.getResultList().get(0);
        if (null != pageable) {
            int skip = YwUnitModel.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<YwUnit>(dataQuery.getResultList(), pageable, count);
        } else {
            return new PageImpl<YwUnit>(dataQuery.getResultList(), null, count);
        }
    }
}
