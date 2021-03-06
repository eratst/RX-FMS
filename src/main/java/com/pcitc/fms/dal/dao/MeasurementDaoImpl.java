package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Measurement;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Map;

/**
 * Title: EquipmentRepositoryImpl Description: 设备对象接口实现类
 *
 * @author lei.y
 * @version 1.0
 * @date 2018年1月15日
 */
@Service
public class MeasurementDaoImpl {
    @PersistenceContext
    private EntityManager em;

    /**
     * @Title: getEquipments
     * @Description: TODO 动态查询实体
     * @date 2018年1月15日
     * @return: dataQuery.getResultList()
     * @author lei.y
     */

    @SuppressWarnings("unchecked")
    public Page<Measurement> findMeasurement(com.pcitc.fms.service.model.Measurement measurement, Pageable pageable) {
        String measureMents = "select count(1) "
                + "from Measurement m,IdxType i "
                + " where m.idxTypeId = i.idxTypeId ";
        StringBuilder dataSql = new StringBuilder();
        dataSql.append(AreaNodeBasicSql.measureMents);

        StringBuilder countSql = new StringBuilder();
        countSql.append(measureMents);

        Map<String, Object> parameterMap = new HashedMap();

        if (null != measurement.getIdxCode()) {
            countSql.append(" and m.idxCode like :idxCode");
            dataSql.append(" and m.idxCode like :idxCode");
            parameterMap.put("idxCode", "%" + measurement.getIdxCode() + "%");
        }
        if (null != measurement.getIdxName() && !StringUtils.isEmpty(measurement.getIdxName())) {
            countSql.append(" and m.idxName like :idxName");
            dataSql.append(" and m.idxName like :idxName");
            parameterMap.put("idxName", "%" + measurement.getIdxName() + "%");
        }
        if (null != measurement.getIdxAlias() && !StringUtils.isEmpty(measurement.getIdxAlias())) {
            countSql.append(" and m.idxAlias like :idxAlias");
            dataSql.append(" and m.idxAlias like :idxAlias");
            parameterMap.put("idxAlias", "%" + measurement.getIdxAlias() + "%");
        }

        if (null != measurement.getInUse() && !StringUtils.isEmpty(measurement.getInUse().toString())) {
            countSql.append(" and m.inUse = :inUse");
            dataSql.append(" and m.inUse = :inUse");
            parameterMap.put("inUse", measurement.getInUse());
        }
        if (null != measurement.getSourceDataType() && !StringUtils.isEmpty(measurement.getSourceDataType())) {
            countSql.append(" and m.sourceDataType = :sourceDataType");
            dataSql.append(" and m.sourceDataType = :sourceDataType");
            parameterMap.put("sourceDataType", measurement.getSourceDataType());
        }

        if (null != measurement.getIdxTypeCode() && !StringUtils.isEmpty(measurement.getIdxTypeCode())) {
            countSql.append(" and i.idxTypeCode like :idxTypeCode");
            dataSql.append(" and i.idxTypeCode like :idxTypeCode");
            parameterMap.put("idxTypeCode", "%" + measurement.getIdxTypeCode() + "%");
        }

        if (null != measurement.getIdxTypeName() && !StringUtils.isEmpty(measurement.getIdxTypeName())) {
            countSql.append(" and i.idxTypeName like :idxTypeName");
            dataSql.append(" and i.idxTypeName like :idxTypeName");
            parameterMap.put("idxTypeName", "%" + measurement.getIdxTypeName() + "%");
        }

        if (null != measurement.getDimensionAlias() && !StringUtils.isEmpty(measurement.getDimensionAlias())) {
            countSql.append(" and m.dimensionId in (select dimensionId from Dimension d where dimensionAlias like :dimensionAlias)");
            dataSql.append(" and m.dimensionId in (select dimensionId from Dimension d where dimensionAlias like :dimensionAlias)");
            parameterMap.put("dimensionAlias", "%" + measurement.getDimensionAlias() + "%");
        }

        if (null != measurement.getDimensionCode() && !StringUtils.isEmpty(measurement.getDimensionCode())) {
            countSql.append(" and m.dimensionId in (select dimensionId from Dimension d where dimensionCode like :dimensionCode)");
            dataSql.append(" and m.dimensionId in (select dimensionId from Dimension d where dimensionCode like :dimensionCode)");
            parameterMap.put("dimensionCode", "%" + measurement.getDimensionCode() + "%");
        }

        if (null != measurement.getOfMeasindexType() && !StringUtils.isEmpty(measurement.getOfMeasindexType().toString())) {
            countSql.append(" and m.ofMeasindexType = :ofMeasindexType");
            dataSql.append(" and m.ofMeasindexType = :ofMeasindexType");
            parameterMap.put("ofMeasindexType", measurement.getOfMeasindexType());
        }

        if (null != measurement.getNodeCode()) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where nodeCode like :nodeCode)");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where nodeCode like :nodeCode)");
            parameterMap.put("nodeCode", "%" + measurement.getNodeCode() + "%");
        }
        if (null != measurement.getNodeName() && !StringUtils.isEmpty(measurement.getNodeName())) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where nodeName like :nodeName)");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where nodeName like :nodeName)");
            parameterMap.put("nodeName", "%" + measurement.getNodeName() + "%");
        }
        if (null != measurement.getNodeAlias() && !StringUtils.isEmpty(measurement.getNodeAlias())) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where nodeAlias like :nodeAlias)");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where nodeAlias like :nodeAlias)");
            parameterMap.put("nodeAlias", "%" + measurement.getNodeAlias() + "%");
        }
        if (null != measurement.getNodeTypeCode()) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where n.nodeTypeId in " +
                    "(select nodeTypeId from NodeType nt where nt.nodeTypeCode like :nodeTypeCode))");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where n.nodeTypeId in " +
                    "(select nodeTypeId from NodeType nt where nt.nodeTypeCode like :nodeTypeCode))");
            parameterMap.put("nodeTypeCode", measurement.getNodeTypeCode());
        }
        if (null != measurement.getNodeTypeName() && !StringUtils.isEmpty(measurement.getNodeTypeName())) {
            countSql.append(" and m.nodeId in (select nodeId from Node n where n.nodeTypeId in " +
                    "(select nodeTypeId from NodeType nt where nt.nodeTypeName like :nodeTypeName))");
            dataSql.append(" and m.nodeId in (select nodeId from Node n where n.nodeTypeId in " +
                    "(select nodeTypeId from NodeType nt where nt.nodeTypeName like :nodeTypeName))");
            parameterMap.put("nodeTypeName", "%" + measurement.getNodeTypeName() + "%");
        }

        if (null != measurement.getAreaCode()) {
            countSql.append(" and m.areaId in (select areaId from Area n where areaCode like :areaCode)");
            dataSql.append(" and m.areaId in (select areaId from Area n where areaCode like :areaCode)");
            parameterMap.put("areaCode", "%" + measurement.getAreaCode() + "%");
        }
        if (null != measurement.getAreaName() && !StringUtils.isEmpty(measurement.getAreaName())) {
            countSql.append(" and m.areaId in (select areaId from Area n where areaName like :areaName)");
            dataSql.append(" and m.areaId in (select areaId from Area n where areaName like :areaName)");
            parameterMap.put("areaName", "%" + measurement.getAreaName() + "%");
        }
        if (null != measurement.getAreaAlias() && !StringUtils.isEmpty(measurement.getAreaAlias())) {
            countSql.append(" and m.areaId in (select areaId from Area n where areaAlias like :areaAlias)");
            dataSql.append(" and m.areaId in (select areaId from Area n where areaAlias like :areaAlias)");
            parameterMap.put("areaAlias", "%" + measurement.getAreaAlias() + "%");
        }

        if (null != measurement.getAreaTypeCode()) {
            countSql.append(" and m.areaId in (select areaId from Area area where area.areaTypeId in " +
                    "(select areaTypeId from AreaType areat where areat.areaTypeCode like :areaTypeCode))");
            dataSql.append(" and m.areaId in (select areaId from Area area where area.areaTypeId in " +
                    "(select areaTypeId from AreaType areat where areat.areaTypeCode like :areaTypeCode))");
            parameterMap.put("areaTypeCode", measurement.getAreaTypeCode());
        }
        if (null != measurement.getAreaTypeName() && !StringUtils.isEmpty(measurement.getAreaTypeName())) {
            countSql.append(" and m.areaId in (select areaId from Area area where area.areaTypeId in " +
                    "(select areaTypeId from AreaType areat where areat.areaTypeName like :areaTypeName))");
            dataSql.append(" and m.areaId in (select areaId from Area area where area.areaTypeId in " +
                    "(select areaTypeId from AreaType areat where areat.areaTypeName like :areaTypeName))");
            parameterMap.put("areaTypeName", "%" + measurement.getAreaTypeName() + "%");
        }

        if (null != measurement.getOrgCode()) {
            countSql.append(" and m.orgId in (select orgId from Org org where orgCode like :orgCode)");
            dataSql.append(" and m.orgId in (select orgId from Org org where orgCode like :orgCode)");
            parameterMap.put("orgCode", "%" + measurement.getOrgCode() + "%");
        }
        if (null != measurement.getOrgName() && !StringUtils.isEmpty(measurement.getOrgName())) {
            countSql.append(" and m.orgId in (select orgId from Org org where orgName like :orgName)");
            dataSql.append(" and m.orgId in (select orgId from Org org where orgName like :orgName)");
            parameterMap.put("orgName", "%" + measurement.getOrgName() + "%");
        }
        if (null != measurement.getOrgAlias() && !StringUtils.isEmpty(measurement.getOrgAlias())) {
            countSql.append(" and m.orgId in (select orgId from Org org where orgAlias like :orgAlias)");
            dataSql.append(" and m.orgId in (select orgId from Org org where orgAlias like :orgAlias)");
            parameterMap.put("orgAlias", "%" + measurement.getOrgAlias() + "%");
        }
        if (null != measurement.getOrgTypeCode()) {
            countSql.append(" and m.orgId in (select orgId from Org org where org.orgTypeId in " +
                    "(select orgTypeId from OrgType orgType where orgType.orgTypeCode like :orgTypeCode))");
            dataSql.append(" and m.orgId in (select orgId from Org org where org.orgTypeId in " +
                    "(select orgTypeId from OrgType orgType where orgType.orgTypeCode like :orgTypeCode))");
            parameterMap.put("orgTypeCode", measurement.getOrgTypeCode());
        }
        if (null != measurement.getOrgTypeName() && !StringUtils.isEmpty(measurement.getOrgTypeName())) {
            countSql.append(" and m.orgId in (select orgId from Org org where org.orgTypeId in " +
                    "(select orgTypeId from OrgType orgType where orgType.orgTypeName like :orgTypeName))");
            dataSql.append(" and m.orgId in (select orgId from Org n where n.orgTypeId in " +
                    "(select orgTypeId from OrgType orgType where orgType.orgTypeName like :orgTypeName))");
            parameterMap.put("orgTypeName", "%" + measurement.getOrgTypeName() + "%");
        }

        if (null != measurement.getCodeList() && measurement.getCodeList().size() > 0) {
            countSql.append(" and m.idxCode in :codeList");
            dataSql.append(" and m.idxCode in :codeList");
            parameterMap.put("codeList", measurement.getCodeList());
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
            int skip = measurement.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<Measurement>(dataQuery.getResultList(), pageable, count);
        } else {
            return new PageImpl<Measurement>(dataQuery.getResultList(), null, count);
        }
    }
}
