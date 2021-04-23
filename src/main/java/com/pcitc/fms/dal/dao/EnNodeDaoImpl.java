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
import com.pcitc.fms.service.model.EnNode;

@Service
public class EnNodeDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public Page<EnNode> findEnNodes(com.pcitc.fms.service.model.EnNode EnNodeModel, Pageable pageable) {
		String enNodes = "select count(1) "
				+ "from EnNode enNode, EnNodeType enNodeType, YwUnit ywUnit, BizorgMain biz "
				+ "where enNode.enNodeTypeId = enNodeType.enNodeTypeId and ywUnit.ywUnitId = enNode.ywUnitId and "
				+ "enNode.bizId = biz.bizId";

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
		if (null != EnNodeModel.getYwUnitCode() && !StringUtils.isEmpty(EnNodeModel.getYwUnitCode())) {
			countSql.append(" and ywUnit.ywUnitCode like :ywUnitCode");
			dataSql.append(" and ywUnit.ywUnitCode like :ywUnitCode");
			parameterMap.put("ywUnitCode", "%" + EnNodeModel.getYwUnitCode() + "%");
		}
		if (null != EnNodeModel.getYwUnitName() && !StringUtils.isEmpty(EnNodeModel.getYwUnitName())) {
			countSql.append(" and ywUnit.ywUnitName like :ywUnitName");
			dataSql.append(" and ywUnit.ywUnitName like :ywUnitName");
			parameterMap.put("ywUnitName", "%" + EnNodeModel.getYwUnitName() + "%");
		}
		if (null != EnNodeModel.getYwUnitAlias() && !StringUtils.isEmpty(EnNodeModel.getYwUnitAlias())) {
			countSql.append(" and ywUnit.ywUnitAlias like :ywUnitAlias");
			dataSql.append(" and ywUnit.ywUnitAlias like :ywUnitAlias");
			parameterMap.put("ywUnitAlias", "%" + EnNodeModel.getYwUnitAlias() + "%");
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
			countSql.append(" and biz.bizCode like :bizCode");
			dataSql.append(" and biz.bizCode like :bizCode");
			parameterMap.put("bizCode", "%" + EnNodeModel.getBizCode() + "%");
		}
        if (EnNodeModel.getDataStatus() != null) {
            countSql.append(" and enNode.dataStatus = :dataStatus");
            dataSql.append(" and enNode.dataStatus = :dataStatus");
            parameterMap.put("dataStatus", + EnNodeModel.getDataStatus());
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
            return new PageImpl<EnNode>(dataQuery.getResultList(),pageable,count);
        }
	}
}
