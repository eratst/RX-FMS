package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.EnPipeNet;
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
public class EnPipeNetRepositoryImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public Page<EnPipeNet> findEnPipeNets(com.pcitc.fms.service.model.EnPipeNet EnPipeNetModel, Pageable pageable) {
        String enPipeNetCount = "select count(1) "
                + " from EnPipeNet enpipenet,Material material,Org org, BizorgMain biz ,Rent rent "
                + " where enpipenet.mtrlId = material.mtrlId and enpipenet.orgId = org.orgId and enpipenet.bizId=biz.bizId and enpipenet.rentId=rent.rentId ";

        StringBuilder dataSql = new StringBuilder();
        dataSql.append(AreaNodeBasicSql.enPipeNet);

        StringBuilder countSql = new StringBuilder();
        countSql.append(enPipeNetCount);
        Map<String, Object> parameterMap = new HashMap<>();
        if (null != EnPipeNetModel.getOrgAlias() && !StringUtils.isEmpty(EnPipeNetModel.getOrgAlias())) {
            countSql.append(" and org.orgAlias like :orgAlias");
            dataSql.append(" and org.orgAlias like :orgAlias");
            parameterMap.put("orgAlias", "%" + EnPipeNetModel.getOrgAlias() + "%");
        }

        if (null != EnPipeNetModel.getBizCode() && !StringUtils.isEmpty(EnPipeNetModel.getBizCode())) {
            countSql.append(" and biz.bizCode = :bizCode");
            dataSql.append(" and biz.bizCode = :bizCode");
            parameterMap.put("bizCode", EnPipeNetModel.getBizCode());
        }

        if (null != EnPipeNetModel.getMtrlCode() && !StringUtils.isEmpty(EnPipeNetModel.getMtrlCode())) {
            countSql.append(" and material.mtrlCode in :mtrlCode");
            dataSql.append(" and material.mtrlCode in :mtrlCode");
            parameterMap.put("mtrlCode", EnPipeNetModel.getMtrlCode());
        }

        if (null != EnPipeNetModel.getMtrlName() && !StringUtils.isEmpty(EnPipeNetModel.getMtrlName())) {
            countSql.append(" and material.mtrlName in :mtrlName");
            dataSql.append(" and material.mtrlName in :mtrlName");
            parameterMap.put("mtrlName", EnPipeNetModel.getMtrlName());
        }

        if (null != EnPipeNetModel.getOrgName() && !StringUtils.isEmpty(EnPipeNetModel.getOrgName())) {
            countSql.append(" and org.orgName in :orgName");
            dataSql.append(" and org.orgName in :orgName");
            parameterMap.put("orgName", EnPipeNetModel.getOrgName());
        }

        if (null != EnPipeNetModel.getOrgCode() && !StringUtils.isEmpty(EnPipeNetModel.getOrgCode())) {
            countSql.append(" and org.orgCode in :orgCode");
            dataSql.append(" and org.orgCode in :orgCode");
            parameterMap.put("orgCode", EnPipeNetModel.getOrgCode());
        }

        if (null != EnPipeNetModel.getNetCode() && !StringUtils.isEmpty(EnPipeNetModel.getNetCode())) {
            countSql.append(" and enpipenet.netCode like :netCode");
            dataSql.append(" and enpipenet.netCode like :netCode");
            parameterMap.put("netCode", "%" + EnPipeNetModel.getNetCode() + "%");
        }

        if (null != EnPipeNetModel.getNetName() && !StringUtils.isEmpty(EnPipeNetModel.getNetName())) {
            countSql.append(" and enpipenet.netName like :netName");
            dataSql.append(" and enpipenet.netName like :netName");
            parameterMap.put("netName", "%" + EnPipeNetModel.getNetName() + "%");
        }
        if (null != EnPipeNetModel.getNetAlias() && !StringUtils.isEmpty(EnPipeNetModel.getNetAlias())) {
            countSql.append(" and enpipenet.netAlias like :netAlias");
            dataSql.append(" and enpipenet.netAlias like :netAlias");
            parameterMap.put("netAlias", "%" + EnPipeNetModel.getNetAlias() + "%");
        }

        if (null != EnPipeNetModel.getDataStatus()
                && !StringUtils.isEmpty(String.valueOf(EnPipeNetModel.getDataStatus()))) {
            countSql.append(" and enpipenet.dataStatus = :dataStatus");
            dataSql.append(" and enpipenet.dataStatus = :dataStatus");
            parameterMap.put("dataStatus", EnPipeNetModel.getDataStatus());
        }
        if (null != EnPipeNetModel.getCodeList() && !EnPipeNetModel.getCodeList().isEmpty()) {
            countSql.append(" and enpipenet.enPipeNetCode in :codeList");
            dataSql.append(" and enpipenet.enPipeNetCode in :codeList");
            parameterMap.put("codeList", EnPipeNetModel.getCodeList());
        }

        if (null != EnPipeNetModel.getRentCode() && !StringUtils.isEmpty(EnPipeNetModel.getRentCode())) {
            dataSql.append(" and rent.rentCode = :rentCode");
            countSql.append(" and rent.rentCode = :rentCode");
            parameterMap.put("rentCode", EnPipeNetModel.getRentCode());
        }

        dataSql.append(" order by enpipenet.sortNum asc");
        countSql.append(" order by enpipenet.sortNum asc");

        Query dataQuery = entityManager.createQuery(dataSql.toString());
        Query countQuery = entityManager.createQuery(countSql.toString());
        for (String parameter : parameterMap.keySet()) {
            countQuery.setParameter(parameter, parameterMap.get(parameter));
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        long count = (long) countQuery.getResultList().get(0);
        if (null != pageable) {
            int skip = EnPipeNetModel.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<EnPipeNet>(dataQuery.getResultList(), pageable, count);
        } else {
            return new PageImpl<EnPipeNet>(dataQuery.getResultList(), null, count);
        }
    }
}
