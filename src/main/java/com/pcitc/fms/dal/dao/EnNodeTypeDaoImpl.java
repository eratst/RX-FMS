package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.EnNodeType;
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
public class EnNodeTypeDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Page<EnNodeType> findEnNodeTypes(com.pcitc.fms.service.model.EnNodeType EnNodeTypeModel, Pageable pageable) {
        String enNodeTypeCount = "select count(1) "
                + " from EnNodeType enNodeType, BizorgMain biz "
                + " where enNodeType.bizId=biz.bizId ";

        StringBuilder dataSql = new StringBuilder();
        dataSql.append(AreaNodeBasicSql.enNodeType);

        StringBuilder countSql = new StringBuilder();
        countSql.append(enNodeTypeCount);
        Map<String, Object> parameterMap = new HashMap<>();

        if (null != EnNodeTypeModel.getBizCode() && !StringUtils.isEmpty(EnNodeTypeModel.getBizCode())) {
            countSql.append(" and biz.bizCode like :bizCode");
            dataSql.append(" and biz.bizCode like :bizCode");
            parameterMap.put("bizCode", "%" + EnNodeTypeModel.getBizCode() + "%");
        }

        if (null != EnNodeTypeModel.getEnNodeTypeCode() && !StringUtils.isEmpty(EnNodeTypeModel.getEnNodeTypeCode())) {
            countSql.append(" and enNodeType.enNodeTypeCode like :enNodeTypeCode");
            dataSql.append(" and enNodeType.enNodeTypeCode like :enNodeTypeCode");
            parameterMap.put("enNodeTypeCode", "%" + EnNodeTypeModel.getEnNodeTypeCode() + "%");
        }

        if (null != EnNodeTypeModel.getEnNodeTypeName() && !StringUtils.isEmpty(EnNodeTypeModel.getEnNodeTypeName())) {
            countSql.append(" and enNodeType.enNodeTypeName like :enNodeTypeName");
            dataSql.append(" and enNodeType.enNodeTypeName like :enNodeTypeName");
            parameterMap.put("enNodeTypeName", "%" + EnNodeTypeModel.getEnNodeTypeName() + "%");
        }
        if (null != EnNodeTypeModel.getInUse()
                && !StringUtils.isEmpty(String.valueOf(EnNodeTypeModel.getInUse()))) {
            countSql.append(" and enNodeType.inUse = :inUse");
            dataSql.append(" and enNodeType.inUse = :inUse");
            parameterMap.put("inUse", EnNodeTypeModel.getInUse());
        }
        if (null != EnNodeTypeModel.getCodeList() && !EnNodeTypeModel.getCodeList().isEmpty()) {
            countSql.append(" and enNodeType.enNodeTypeCode in :codeList");
            dataSql.append(" and enNodeType.enNodeTypeCode in :codeList");
            parameterMap.put("codeList", EnNodeTypeModel.getCodeList());
        }

        dataSql.append(" order by enNodeType.sortNum asc");
        countSql.append(" order by enNodeType.sortNum asc");

        Query dataQuery = entityManager.createQuery(dataSql.toString());
        Query countQuery = entityManager.createQuery(countSql.toString());
        for (String parameter : parameterMap.keySet()) {
            countQuery.setParameter(parameter, parameterMap.get(parameter));
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        long count = (long) countQuery.getResultList().get(0);
        if (null != pageable) {
            int skip = EnNodeTypeModel.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<EnNodeType>(dataQuery.getResultList(), pageable, count);
        } else {
            return new PageImpl<EnNodeType>(dataQuery.getResultList(), null, count);
        }
    }
}
