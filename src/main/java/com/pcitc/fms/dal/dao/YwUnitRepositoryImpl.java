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
                + " from YwUnit ywUnit,Org org, BizorgMain biz ,UnitType unitType,Technic technic "
                + " where ywUnit.orgId = org.orgId and ywUnit.bizId = biz.bizId and ywUnit.unitTypeId=unitType.unitTypeId "
                + "and ywUnit.technicId=technic.technicId ";

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
            countSql.append(" and biz.bizCode like :bizCode");
            dataSql.append(" and biz.bizCode like :bizCode");
            parameterMap.put("bizCode", "%" + YwUnitModel.getBizCode() + "%");
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

        if (null != YwUnitModel.getYwUnitCode() && !StringUtils.isEmpty(YwUnitModel.getYwUnitCode())) {
            countSql.append(" and ywUnit.ywUnitCode like :ywUnitCode");
            dataSql.append(" and ywUnit.ywUnitCode like :ywUnitCode");
            parameterMap.put("ywUnitCode", "%" + YwUnitModel.getYwUnitCode() + "%");
        }

        if (null != YwUnitModel.getYwUnitName() && !StringUtils.isEmpty(YwUnitModel.getYwUnitName())) {
            countSql.append(" and ywUnit.ywUnitName like :ywUnitName");
            dataSql.append(" and ywUnit.ywUnitName like :ywUnitName");
            parameterMap.put("ywUnitName", "%" + YwUnitModel.getYwUnitName() + "%");
        }
        if (null != YwUnitModel.getYwUnitAlias() && !StringUtils.isEmpty(YwUnitModel.getYwUnitAlias())) {
            countSql.append(" and ywUnit.ywUnitAlias like :ywUnitAlias");
            dataSql.append(" and ywUnit.ywUnitAlias like :ywUnitAlias");
            parameterMap.put("ywUnitAlias", "%" + YwUnitModel.getYwUnitAlias() + "%");
        }

        if (null != YwUnitModel.getDataStatus()
                && !StringUtils.isEmpty(String.valueOf(YwUnitModel.getDataStatus()))) {
            countSql.append(" and ywUnit.dataStatus = :dataStatus");
            dataSql.append(" and ywUnit.dataStatus = :dataStatus");
            parameterMap.put("dataStatus", YwUnitModel.getDataStatus());
        }
        if (null != YwUnitModel.getCodeList() && !YwUnitModel.getCodeList().isEmpty()) {
            countSql.append(" and ywUnit.ywUnitCode in :codeList");
            dataSql.append(" and ywUnit.ywUnitCode in :codeList");
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

       /* List<String> orgCodes = new ArrayList<>();
        if (StringUtils.isNotEmpty(YwUnitModel.getRentCode())) {
            orgCodes = CacheRentInfo.getNewOrgCodes(YwUnitModel.getRentCode(), YwUnitModel.getBizCode());
            if (orgCodes != null && !orgCodes.isEmpty()) {
                countSql.append(" and org.orgCode in :rentOrgCodes");
                dataSql.append(" and org.orgCode in :rentOrgCodes");
                parameterMap.put("rentOrgCodes", orgCodes);
            } else {
                return new PageImpl(new ArrayList<YwUnit>(), null, 0L);
            }
        }*/

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
