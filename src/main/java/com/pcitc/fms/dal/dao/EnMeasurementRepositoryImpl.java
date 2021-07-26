package com.pcitc.fms.dal.dao;

import com.pcitc.fms.common.CacheRentInfo;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.EnMeasurement;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EnMeasurementRepositoryImpl {
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public Page<EnMeasurement> findEnMeasurement(com.pcitc.fms.service.model.EnMeasurement enMeasurement, Pageable pageable) {
        String measureMents = "select count(1) "
                + "from EnMeasurement enm, Measurement m,IdxType i,Dimension d, BizorgMain biz"
                + " where enm.idxId=m.idxId and m.idxTypeId = i.idxTypeId and m.dimensionId = d.dimensionId and enm.bizId=biz.bizId";

        StringBuilder dataSql = new StringBuilder();
        dataSql.append(AreaNodeBasicSql.enMeasurements);

        StringBuilder countSql = new StringBuilder();
        countSql.append(measureMents);

        Map<String, Object> parameterMap = new HashedMap();

        if (null != enMeasurement.getIdxCode()) {
            countSql.append(" and m.idxCode = :idxCode");
            dataSql.append(" and m.idxCode =  :idxCode");
            parameterMap.put("idxCode", enMeasurement.getIdxCode());
        }
        if (null != enMeasurement.getIdxName() && !StringUtils.isEmpty(enMeasurement.getIdxName())) {
            countSql.append(" and m.idxName like :idxName");
            dataSql.append(" and m.idxName like :idxName");
            parameterMap.put("idxName", "%" + enMeasurement.getIdxName() + "%");
        }

        if (null != enMeasurement.getIdxFormula() && !StringUtils.isEmpty(enMeasurement.getIdxFormula())) {
            countSql.append(" and m.idxFormula = :idxFormula");
            dataSql.append(" and m.idxFormula = :idxFormula");
            parameterMap.put("idxFormula", enMeasurement.getIdxFormula());
        }
        if (null != enMeasurement.getIdxAlias() && !StringUtils.isEmpty(enMeasurement.getIdxAlias())) {
            countSql.append(" and m.idxAlias like :idxAlias");
            dataSql.append(" and m.idxAlias like :idxAlias");
            parameterMap.put("idxAlias", "%" + enMeasurement.getIdxAlias() + "%");
        }

        if (null != enMeasurement.getInUse() && !StringUtils.isEmpty(enMeasurement.getInUse().toString())) {
            countSql.append(" and m.inUse = :inUse");
            dataSql.append(" and m.inUse = :inUse");
            parameterMap.put("inUse", enMeasurement.getInUse());
        }
        if (null != enMeasurement.getSourceDataType() && !StringUtils.isEmpty(enMeasurement.getSourceDataType())) {
            countSql.append(" and m.sourceDataType = :sourceDataType");
            dataSql.append(" and m.sourceDataType = :sourceDataType");
            parameterMap.put("sourceDataType", enMeasurement.getSourceDataType());
        }

        if (null != enMeasurement.getIdxTypeCode() && !StringUtils.isEmpty(enMeasurement.getIdxTypeCode())) {
            countSql.append(" and i.idxTypeCode like :idxTypeCode");
            dataSql.append(" and i.idxTypeCode like :idxTypeCode");
            parameterMap.put("idxTypeCode", "%" + enMeasurement.getIdxTypeCode() + "%");
        }

        if (null != enMeasurement.getIdxTypeName() && !StringUtils.isEmpty(enMeasurement.getIdxTypeName())) {
            countSql.append(" and i.idxTypeName like :idxTypeName");
            dataSql.append(" and i.idxTypeName like :idxTypeName");
            parameterMap.put("idxTypeName", "%" + enMeasurement.getIdxTypeName() + "%");
        }

        if (null != enMeasurement.getDimensionAlias() && !StringUtils.isEmpty(enMeasurement.getDimensionAlias())) {
            countSql.append(" and d.dimensionAlias like :dimensionAlias");
            dataSql.append(" and d.dimensionAlias like :dimensionAlias");
            parameterMap.put("dimensionAlias", "%" + enMeasurement.getDimensionAlias() + "%");
        }

        if (null != enMeasurement.getOfMeasindexType() && !StringUtils.isEmpty(enMeasurement.getOfMeasindexType().toString())) {
            countSql.append(" and m.ofMeasindexType = :ofMeasindexType");
            dataSql.append(" and m.ofMeasindexType = :ofMeasindexType");
            parameterMap.put("ofMeasindexType", enMeasurement.getOfMeasindexType());
        }

        if (null != enMeasurement.getNodeCode()) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where nodeCode like :nodeCode)");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where nodeCode like :nodeCode)");
            parameterMap.put("nodeCode", "%" + enMeasurement.getNodeCode() + "%");
        }
        if (null != enMeasurement.getNodeName() && !StringUtils.isEmpty(enMeasurement.getNodeName())) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where nodeName like :nodeName)");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where nodeName like :nodeName)");
            parameterMap.put("nodeName", "%" + enMeasurement.getNodeName() + "%");
        }
        if (null != enMeasurement.getNodeAlias() && !StringUtils.isEmpty(enMeasurement.getNodeAlias())) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where nodeAlias like :nodeAlias)");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where nodeAlias like :nodeAlias)");
            parameterMap.put("nodeAlias", "%" + enMeasurement.getNodeAlias() + "%");
        }
        if (null != enMeasurement.getNodeTypeCode()) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where n.nodeTypeId in " +
                    "(select nodeTypeId from NodeType nt where nt.nodeTypeCode like :nodeTypeCode))");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where n.nodeTypeId in " +
                    "(select nodeTypeId from NodeType nt where nt.nodeTypeCode like :nodeTypeCode))");
            parameterMap.put("nodeTypeCode", enMeasurement.getNodeTypeCode());
        }
        if (null != enMeasurement.getNodeTypeName() && !StringUtils.isEmpty(enMeasurement.getNodeTypeName())) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where n.nodeTypeId in " +
                    "(select nodeTypeId from NodeType nt where nt.nodeTypeName like :nodeTypeName))");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where n.nodeTypeId in " +
                    "(select nodeTypeId from NodeType nt where nt.nodeTypeName like :nodeTypeName))");
            parameterMap.put("nodeTypeName", "%" + enMeasurement.getNodeTypeName() + "%");
        }

        if (null != enMeasurement.getAreaCode()) {
            countSql.append(" and m.areaId in (select areaId from Area n where areaCode like :areaCode)");
            dataSql.append(" and m.areaId in (select areaId from Area n where areaCode like :areaCode)");
            parameterMap.put("areaCode", "%" + enMeasurement.getAreaCode() + "%");
        }
        if (null != enMeasurement.getAreaName() && !StringUtils.isEmpty(enMeasurement.getAreaName())) {
            countSql.append(" and m.areaId in (select areaId from Area n where areaName like :areaName)");
            dataSql.append(" and m.areaId in (select areaId from Area n where areaName like :areaName)");
            parameterMap.put("areaName", "%" + enMeasurement.getAreaName() + "%");
        }
        if (null != enMeasurement.getAreaAlias() && !StringUtils.isEmpty(enMeasurement.getAreaAlias())) {
            countSql.append(" and m.areaId in (select areaId from Area n where areaAlias like :areaAlias)");
            dataSql.append(" and m.areaId in (select areaId from Area n where areaAlias like :areaAlias)");
            parameterMap.put("areaAlias", "%" + enMeasurement.getAreaAlias() + "%");
        }

        if (null != enMeasurement.getAreaTypeCode()) {
            countSql.append(" and m.areaId in (select areaId from Area area where area.areaTypeId in " +
                    "(select areaTypeId from AreaType areat where areat.areaTypeCode like :areaTypeCode))");
            dataSql.append(" and m.areaId in (select areaId from Area area where area.areaTypeId in " +
                    "(select areaTypeId from AreaType areat where areat.areaTypeCode like :areaTypeCode))");
            parameterMap.put("areaTypeCode", enMeasurement.getAreaTypeCode());
        }
        if (null != enMeasurement.getAreaTypeName() && !StringUtils.isEmpty(enMeasurement.getAreaTypeName())) {
            countSql.append(" and m.areaId in (select areaId from Area area where area.areaTypeId in " +
                    "(select areaTypeId from AreaType areat where areat.areaTypeName like :areaTypeName))");
            dataSql.append(" and m.areaId in (select areaId from Area area where area.areaTypeId in " +
                    "(select areaTypeId from AreaType areat where areat.areaTypeName like :areaTypeName))");
            parameterMap.put("areaTypeName", "%" + enMeasurement.getAreaTypeName() + "%");
        }

        if (null != enMeasurement.getOrgCode()) {
            countSql.append(" and m.orgId in (select orgId from Org org where orgCode like :orgCode)");
            dataSql.append(" and m.orgId in (select orgId from Org org where orgCode like :orgCode)");
            parameterMap.put("orgCode", "%" + enMeasurement.getOrgCode() + "%");
        }
        if (null != enMeasurement.getOrgName() && !StringUtils.isEmpty(enMeasurement.getOrgName())) {
            countSql.append(" and m.orgId in (select orgId from Org org where orgName like :orgName)");
            dataSql.append(" and m.orgId in (select orgId from Org org where orgName like :orgName)");
            parameterMap.put("orgName", "%" + enMeasurement.getOrgName() + "%");
        }
        if (null != enMeasurement.getOrgAlias() && !StringUtils.isEmpty(enMeasurement.getOrgAlias())) {
            countSql.append(" and m.orgId in (select orgId from Org org where orgAlias like :orgAlias)");
            dataSql.append(" and m.orgId in (select orgId from Org org where orgAlias like :orgAlias)");
            parameterMap.put("orgAlias", "%" + enMeasurement.getOrgAlias() + "%");
        }
        if (null != enMeasurement.getOrgTypeCode()) {
            countSql.append(" and m.orgId in (select orgId from Org org where org.orgTypeId in " +
                    "(select orgTypeId from OrgType orgType where orgType.orgTypeCode like :orgTypeCode))");
            dataSql.append(" and m.orgId in (select orgId from Org org where org.orgTypeId in " +
                    "(select orgTypeId from OrgType orgType where orgType.orgTypeCode like :orgTypeCode))");
            parameterMap.put("orgTypeCode", enMeasurement.getOrgTypeCode());
        }
        if (null != enMeasurement.getOrgTypeName() && !StringUtils.isEmpty(enMeasurement.getOrgTypeName())) {
            countSql.append(" and m.orgId in (select orgId from Org org where org.orgTypeId in " +
                    "(select orgTypeId from OrgType orgType where orgType.orgTypeName like :orgTypeName))");
            dataSql.append(" and m.orgId in (select orgId from Org n where n.orgTypeId in " +
                    "(select orgTypeId from OrgType orgType where orgType.orgTypeName like :orgTypeName))");
            parameterMap.put("orgTypeName", "%" + enMeasurement.getOrgTypeName() + "%");
        }

        if (null != enMeasurement.getCodeList() && enMeasurement.getCodeList().size() > 0) {
            countSql.append(" and m.idxCode in :codeList");
            dataSql.append(" and m.idxCode in :codeList");
            parameterMap.put("codeList", enMeasurement.getCodeList());
        }

        dataSql.append(" order by m.sortNum asc");
        countSql.append(" order by m.sortNum asc");

        Query dataQuery = em.createQuery(dataSql.toString());
        Query countQuery = em.createQuery(countSql.toString());
        for (String parameter : parameterMap.keySet()) {
            countQuery.setParameter(parameter, parameterMap.get(parameter));
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        long count = (long) countQuery.getResultList().get(0);
        if (null != pageable) {
            int skip = enMeasurement.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<EnMeasurement>(dataQuery.getResultList(), pageable, count);
        } else {
            return new PageImpl<EnMeasurement>(dataQuery.getResultList(), null, count);
        }
    }
}
