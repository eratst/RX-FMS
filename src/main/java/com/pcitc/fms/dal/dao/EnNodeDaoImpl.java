package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.service.model.EnNode;
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
public class EnNodeDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Page<EnNode> findEnNodes(com.pcitc.fms.service.model.EnNode EnNodeModel, Pageable pageable) {
        String enNodes = "select count(1) "
                + "from EnNode enNode, EnNodeType enNodeType,  BizorgMain biz ,Rent rent "
                + "where enNode.enNodeTypeId = enNodeType.enNodeTypeId and enNode.bizId = biz.bizId and enNodeType.rentId=rent.rentId ";

        StringBuilder dataSql = new StringBuilder();
        dataSql.append(AreaNodeBasicSql.enNode);

        StringBuilder countSql = new StringBuilder();
        countSql.append(enNodes);

        Map<String, Object> parameterMap = new HashMap<>();
        if (null != EnNodeModel.getEnNodeCode() && !StringUtils.isEmpty(EnNodeModel.getEnNodeCode())) {
            countSql.append(" and enNode.enNodeCode like :enNodeCode");
            dataSql.append(" and enNode.enNodeCode like :enNodeCode");
            parameterMap.put("enNodeCode", "%" + EnNodeModel.getEnNodeCode() + "%");
        }
        if (null != EnNodeModel.getEnNodeName() && !StringUtils.isEmpty(EnNodeModel.getEnNodeName())) {
            countSql.append(" and enNode.enNodeName like :enNodeName");
            dataSql.append(" and enNode.enNodeName like :enNodeName");
            parameterMap.put("enNodeName", "%" + EnNodeModel.getEnNodeName() + "%");
        }
        if (null != EnNodeModel.getEnNodeAlias() && !StringUtils.isEmpty(EnNodeModel.getEnNodeAlias())) {
            countSql.append(" and enNode.enNodeAlias like :enNodeAlias");
            dataSql.append(" and enNode.enNodeAlias like :enNodeAlias");
            parameterMap.put("enNodeAlias", "%" + EnNodeModel.getEnNodeAlias() + "%");
        }
        if (null != EnNodeModel.getEnNodeTypeCode() && !StringUtils.isEmpty(EnNodeModel.getEnNodeTypeCode())) {
            countSql.append(" and enNodeType.enNodeTypeCode like :enNodeTypeCode");
            dataSql.append(" and enNodeType.enNodeTypeCode like :enNodeTypeCode");
            parameterMap.put("enNodeTypeCode", "%" + EnNodeModel.getEnNodeTypeCode() + "%");
        }

        if (null != EnNodeModel.getEnNodeTypeName() && !StringUtils.isEmpty(EnNodeModel.getEnNodeTypeName())) {
            countSql.append(" and enNodeType.enNodeTypeName like :enNodeTypeName");
            dataSql.append(" and enNodeType.enNodeTypeName like :enNodeTypeName");
            parameterMap.put("enNodeTypeName", "%" + EnNodeModel.getEnNodeTypeName() + "%");
        }

        if (null != EnNodeModel.getAreaCode() && !StringUtils.isEmpty(EnNodeModel.getAreaCode())) {
            countSql.append(" and (enNode.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaCode like :areaCode) " +
                    "or enNode.areaId in (select plantId from Plant unit where unit.plantCode like :areaCode))");
            dataSql.append(" and (enNode.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaCode like :areaCode) " +
                    "or enNode.areaId in (select plantId from Plant unit where unit.plantCode like :areaCode))");
            parameterMap.put("areaCode", "%" + EnNodeModel.getAreaCode() + "%");
        }
        if (null != EnNodeModel.getAreaName() && !StringUtils.isEmpty(EnNodeModel.getAreaName())) {
            countSql.append(" and (enNode.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaName like :areaName) " +
                    "or enNode.areaId in (select plantId from Plant unit where unit.plantCode in " +
                    "(select area.areaCode from Area area where area.areaName like :areaName)))");
            dataSql.append("  and (enNode.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaName like :areaName) " +
                    "or enNode.areaId in (select plantId from Plant unit where unit.plantCode in " +
                    "(select area.areaCode from Area area where area.areaName like :areaName)))");
            parameterMap.put("areaName", "%" + EnNodeModel.getAreaName() + "%");
        }

        if (null != EnNodeModel.getAreaAlias() && !StringUtils.isEmpty(EnNodeModel.getAreaAlias())) {
            countSql.append(" and (enNode.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaAlias like :areaAlias) " +
                    "or enNode.areaId in (select plantId from Plant unit where unit.plantCode in " +
                    "(select area.areaCode from Area area where area.areaAlias like :areaAlias)))");
            dataSql.append("  and (enNode.areaId in (select areaId from YwUnit ywUnit where ywUnit.areaAlias like :areaAlias) " +
                    "or enNode.areaId in (select plantId from Plant unit where unit.plantCode in " +
                    "(select area.areaCode from Area area where area.areaAlias like :areaAlias)))");
            parameterMap.put("areaAlias", "%" + EnNodeModel.getAreaAlias() + "%");
        }

        if (null != EnNodeModel.getNetCode() && !StringUtils.isEmpty(EnNodeModel.getNetCode())) {
            countSql.append(" and enpipenet.netCode like :netCode");
            dataSql.append(" and enpipenet.netCode like :netCode");
            parameterMap.put("netCode", "%" + EnNodeModel.getNetCode() + "%");
        }
        if (null != EnNodeModel.getNetName() && !StringUtils.isEmpty(EnNodeModel.getNetName())) {
            countSql.append(" and enpipenet.netName like :netName");
            dataSql.append(" and enpipenet.netName like :netName");
            parameterMap.put("netName", "%" + EnNodeModel.getNetName() + "%");
        }
        if (null != EnNodeModel.getBizCode() && !StringUtils.isEmpty(EnNodeModel.getBizCode())) {
            countSql.append(" and biz.bizCode = :bizCode");
            dataSql.append(" and biz.bizCode = :bizCode");
            parameterMap.put("bizCode", EnNodeModel.getBizCode());
        }
        if (EnNodeModel.getDataStatus() != null) {
            countSql.append(" and enNode.dataStatus = :dataStatus");
            dataSql.append(" and enNode.dataStatus = :dataStatus");
            parameterMap.put("dataStatus", +EnNodeModel.getDataStatus());
        }

        if (null != EnNodeModel.getRentCode() && !StringUtils.isEmpty(EnNodeModel.getRentCode())) {
            dataSql.append(" and rent.rentCode = :rentCode");
            countSql.append(" and rent.rentCode = :rentCode");
            parameterMap.put("rentCode", EnNodeModel.getRentCode());
        }

        dataSql.append(" order by enNode.sortNum asc");
        countSql.append(" order by enNode.sortNum asc");

        Query dataQuery = entityManager.createQuery(dataSql.toString());
        Query countQuery = entityManager.createQuery(countSql.toString());
        for (String parameter : parameterMap.keySet()) {
            countQuery.setParameter(parameter, parameterMap.get(parameter));
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }

        long count = (long) countQuery.getResultList().get(0);
        if (null != pageable) {
            Integer skip = EnNodeModel.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<EnNode>(dataQuery.getResultList(), pageable, count);
        } else {
            return new PageImpl<EnNode>(dataQuery.getResultList(), pageable, count);
        }
    }
}
