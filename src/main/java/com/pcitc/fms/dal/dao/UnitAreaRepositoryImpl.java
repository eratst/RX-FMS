package com.pcitc.fms.dal.dao;

import com.pcitc.fms.common.CacheRentInfo;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.UnitArea;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author yalin.zhao
 * @date 2021/4/21 9:32
 */
@Service
public class UnitAreaRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Page<UnitArea> findUnitAreas(com.pcitc.fms.service.model.UnitArea UnitAreaModel, Pageable pageable) {
        String unitareaCount = "select count(1) "
                + " from UnitArea unitArea,Org org, BizorgMain biz "
                + " where unitArea.orgId = org.orgId and unitArea.bizId=biz.bizId ";

        StringBuilder dataSql = new StringBuilder();
        dataSql.append(AreaNodeBasicSql.unitarea);

        StringBuilder countSql = new StringBuilder();
        countSql.append(unitareaCount);
        Map<String, Object> parameterMap = new HashMap<>();
        if (null != UnitAreaModel.getOrgAlias() && !StringUtils.isEmpty(UnitAreaModel.getOrgAlias())) {
            countSql.append(" and org.orgAlias like :orgAlias");
            dataSql.append(" and org.orgAlias like :orgAlias");
            parameterMap.put("orgAlias", "%" + UnitAreaModel.getOrgAlias() + "%");
        }

        if (null != UnitAreaModel.getBizCode() && !StringUtils.isEmpty(UnitAreaModel.getBizCode())) {
            countSql.append(" and biz.bizCode like :bizCode");
            dataSql.append(" and biz.bizCode like :bizCode");
            parameterMap.put("bizCode", "%" + UnitAreaModel.getBizCode() + "%");
        }

        if (null != UnitAreaModel.getOrgCode() && !StringUtils.isEmpty(UnitAreaModel.getOrgCode())) {
            countSql.append(" and org.orgCode in :orgCode");
            dataSql.append(" and org.orgCode in :orgCode");
            parameterMap.put("orgCode", UnitAreaModel.getOrgCode());
        }
        if (null != UnitAreaModel.getUnitAreaCode() && !StringUtils.isEmpty(UnitAreaModel.getUnitAreaCode())) {
            countSql.append(" and unitArea.unitAreaCode like :unitAreaCode");
            dataSql.append(" and unitArea.unitAreaCode like :unitAreaCode");
            parameterMap.put("unitAreaCode", "%" + UnitAreaModel.getUnitAreaCode() + "%");
        }
        if (null != UnitAreaModel.getUnitAreaName() && !StringUtils.isEmpty(UnitAreaModel.getUnitAreaName())) {
            countSql.append(" and unitArea.unitAreaName like :unitAreaName");
            dataSql.append(" and unitArea.unitAreaName like :unitAreaName");
            parameterMap.put("unitAreaName", "%" + UnitAreaModel.getUnitAreaName() + "%");
        }
        /*if (null != UnitAreaModel.getUnitAreaAlias() && !StringUtils.isEmpty(UnitAreaModel.getUnitAreaAlias())) {
            countSql.append(" and unitArea.unitAreaAlias like :unitAreaAlias");
            dataSql.append(" and unitArea.unitAreaAlias like :unitAreaAlias");
            parameterMap.put("unitAreaAlias", "%" + UnitAreaModel.getUnitAreaAlias() + "%");
        }*/

        if (null != UnitAreaModel.getDataStatus()
                && !StringUtils.isEmpty(String.valueOf(UnitAreaModel.getDataStatus()))) {
            countSql.append(" and unitArea.dataStatus = :dataStatus");
            dataSql.append(" and unitArea.dataStatus = :dataStatus");
            parameterMap.put("dataStatus", UnitAreaModel.getDataStatus());
        }
        if (null != UnitAreaModel.getCodeList() && !UnitAreaModel.getCodeList().isEmpty()) {
            countSql.append(" and unitArea.unitAreaCode in :codeList");
            dataSql.append(" and unitArea.unitAreaCode in :codeList");
            parameterMap.put("codeList", UnitAreaModel.getCodeList());
        }

        /*List<String> orgCodes = new ArrayList<>();
        if (StringUtils.isNotEmpty(UnitAreaModel.getRentCode())) {
            orgCodes = CacheRentInfo.getNewOrgCodes(UnitAreaModel.getRentCode(), UnitAreaModel.getBizCode());
            if (orgCodes != null && !orgCodes.isEmpty()) {
                countSql.append(" and org.orgCode in :rentOrgCodes");
                dataSql.append(" and org.orgCode in :rentOrgCodes");
                parameterMap.put("rentOrgCodes", orgCodes);
            } else {
                return new PageImpl(new ArrayList<UnitArea>(), null, 0L);
            }
        }*/

        dataSql.append(" order by unitArea.sortNum asc");
        countSql.append(" order by unitArea.sortNum asc");

        Query dataQuery = entityManager.createQuery(dataSql.toString());
        Query countQuery = entityManager.createQuery(countSql.toString());
        for (String parameter : parameterMap.keySet()) {
            countQuery.setParameter(parameter, parameterMap.get(parameter));
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        long count = (long) countQuery.getResultList().get(0);
        if (null != pageable) {
            int skip = UnitAreaModel.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<UnitArea>(dataQuery.getResultList(), pageable, count);
        } else {
            return new PageImpl<UnitArea>(dataQuery.getResultList(), null, count);
        }
    }
}
