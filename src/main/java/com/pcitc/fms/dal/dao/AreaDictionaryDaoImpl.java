/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: AreaDictionaryDaoImpl
 * Date:18-3-9 上午8:33
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.AreaDictionary;
import com.pcitc.fms.dal.pojo.Area_NodeType_Num;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.imp.common.exception.BusiException;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The type Area dictionary dao.
 */
public class AreaDictionaryDaoImpl {

    /**
     * The Em.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Gets area dictionary by model.
     *
     * @param areaDictionaryTableModel the area dictionary table model
     * @param pageable                 the pageable
     * @return the area dictionary by model
     * @throws BusinessException the business exception
     */
    @SuppressWarnings("unchecked")
    public MyPageImpl getAreaDictionaryByModel(com.pcitc.fms.service.model.AreaDictionary areaDictionaryTableModel, Pageable pageable) throws BusinessException {
        Map<String, Object> parameterMap = new HashedMap();
        String factoryCode = areaDictionaryTableModel.getFactoryCode();
        String sql = "select l from AreaDictionary l where factoryCode = '" + factoryCode + "' ";
        if (areaDictionaryTableModel.getEnabled() != null) {
            sql += "and l.enabled=" + "'" + areaDictionaryTableModel.getEnabled() + "'";
        }
        if (areaDictionaryTableModel.getName() != null) {
            sql += "and l.name like" + "'%" + areaDictionaryTableModel.getName() + "%'";
        }
        if (null != areaDictionaryTableModel.getCodeList() && areaDictionaryTableModel.getCodeList().size() > 0) {
            sql += " and l.code in :codes";
            parameterMap.put("codes", areaDictionaryTableModel.getCodeList());
        }
        if (null != areaDictionaryTableModel.getIdList() && areaDictionaryTableModel.getIdList().size() > 0) {
            sql += " and l.areaDictionaryId in :ids";
            parameterMap.put("ids", areaDictionaryTableModel.getIdList());
        }
        sql += " order by areaDictionaryId";
        Query dataQuery = em.createQuery(sql);
        for (String parameter : parameterMap.keySet()) {
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        long count = dataQuery.getResultList().size();
        Integer skip = null;
        if (null != pageable) {
            String skipStr = areaDictionaryTableModel.getSkip();
            skip = Integer.parseInt(skipStr);
            dataQuery = em.createQuery(sql);
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), pageable, count);
            myPageImpl.setCount(count);
            return myPageImpl;

        } else {
            if (null != areaDictionaryTableModel && null != areaDictionaryTableModel.getTop()) {
                Integer top = Integer.parseInt(areaDictionaryTableModel.getTop());
                if (top >= 0) {
                    dataQuery.setMaxResults(top);
                }
            }
            if (null != areaDictionaryTableModel && null != areaDictionaryTableModel.getSkip()) {
                skip = Integer.parseInt(areaDictionaryTableModel.getSkip());
                if (skip >= 0) {
                    dataQuery.setFirstResult(skip);
                }
                dataQuery.setFirstResult(skip);
            }
            MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), null, count);
            myPageImpl.setCount(count);
            return myPageImpl;
        }
    }

    /**
     * Gets area dictionary ids by model list.
     *
     * @param areaDictionaryEntityList the area dictionary entity list
     * @return the area dictionary ids by model list
     */
    @SuppressWarnings("unchecked")
    public List<AreaDictionary> getAreaDictionaryIdsByModelList(List<com.pcitc.fms.bll.entity.AreaDictionary> areaDictionaryEntityList) {
        com.pcitc.fms.bll.entity.AreaDictionary areaDictionaryEntity = areaDictionaryEntityList.get(0);
        Integer factoryId = areaDictionaryEntity.getFactoryId();
        Map<String, Object> parameterMap = new HashedMap();
        String sql = "select l from AreaDictionary l where l.factoryId = " + factoryId + " ";
        if (areaDictionaryEntity.getEnabled() != null) {
            sql += "and l.enabled=" + "'" + areaDictionaryEntity.getEnabled() + "'";
        }
        if (areaDictionaryEntity.getName() != null) {
            sql += "and l.name like" + "'%" + areaDictionaryEntity.getName() + "%'";
        }
        if (null != areaDictionaryEntity.getCodeList() && areaDictionaryEntity.getCodeList().size() > 0) {
            sql += " and l.code in :codes";
            parameterMap.put("codes", areaDictionaryEntity.getCodeList());
        }
        if (null != areaDictionaryEntity.getIdList() && areaDictionaryEntity.getIdList().size() > 0) {
            sql += " and l.areaDictionaryId in :ids";
            parameterMap.put("ids", areaDictionaryEntity.getIdList());
        }
        sql += " order by l.areaDictionaryId";
        Query dataQuery = em.createQuery(sql);
        for (String parameter : parameterMap.keySet()) {
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        List<com.pcitc.fms.dal.pojo.AreaDictionary> pojoList = dataQuery.getResultList();

        return pojoList;
    }

    /**
     * Get area dictionary models page.
     *
     * @param areaDictionaryTableModel the area dictionary table model
     * @param pageable                 the pageable
     * @param orgCode                  the org code
     * @return the page
     * @throws BusiException
     */
    @SuppressWarnings("unchecked")
    public MyPageImpl getAreas(com.pcitc.fms.service.model.Area area, Pageable pageable) throws BusiException {

        String countBase = "select count(1) from AreaDictionary t,TPmOrg o,AreaType at where t.factoryId = o.orgId and t.areaTypeId = at.areaTypeId ";
        StringBuilder countSql = new StringBuilder();
        countSql.append(countBase);

        StringBuilder dataSql = new StringBuilder();
        dataSql.append(AreaNodeBasicSql.areaDictionary + " and 1 = 1");
        Map<String, Object> parameterMap = new HashedMap();

        if (null != area.getAreaCodes() && area.getAreaCodes().size() > 0) {
            dataSql.append(" and t.areaCode in :areaCodes");
            countSql.append(" and t.areaCode in :areaCodes");
            parameterMap.put("areaCodes", area.getAreaCodes());
        }

        if (null != area.getAreaCode() && StringUtils.isNotEmpty(area.getAreaCode())) {
            dataSql.append(" and t.areaCode = :areaCode");
            countSql.append(" and t.areaCode = :areaCode");
            parameterMap.put("areaCode", area.getAreaCode());
        }

        if (null != area.getOrgCodes() && area.getOrgCodes().size() > 0) {
            dataSql.append(" and o.orgCode in :orgCodes");
            countSql.append(" and o.orgCode in :orgCodes");
            parameterMap.put("orgCodes", area.getOrgCodes());
        }

        if (null != area.getOrgCode() && StringUtils.isNotEmpty(area.getOrgCode())) {
            dataSql.append(" and o.orgCode =:orgCode");
            countSql.append(" and o.orgCode = :orgCode");
            parameterMap.put("orgCode", area.getOrgCode());
        }

        if (null != area.getAreaName() && StringUtils.isNotEmpty(area.getAreaName())) {
            dataSql.append(" and t.name like :areaName");
            countSql.append(" and t.name like :areaName");
            parameterMap.put("areaName", "%" + area.getAreaName() + "%");
        }

        if (null != area.getAreaAlias() && StringUtils.isNotEmpty(area.getAreaAlias())) {
            dataSql.append(" and t.shortName like :areaAlias");
            countSql.append(" and t.shortName like :areaAlias");
            parameterMap.put("areaAlias", "%" + area.getAreaAlias() + "%");
        }

        if (null != area.getAreaTypeCode() && StringUtils.isNotEmpty(area.getAreaTypeCode())) {
            dataSql.append(" and at.areaTypeCode = :areaTypeCode");
            countSql.append(" and at.areaTypeCode = :areaTypeCode");
            parameterMap.put("areaTypeCode", area.getAreaTypeCode());
        }

        if (null != area.getInUse()) {
            dataSql.append(" and t.enabled = :enabled");
            countSql.append(" and t.enabled = :enabled");
            parameterMap.put("enabled", area.getInUse());
        }

        //----------处理租户过滤
        if (StringUtils.isNotEmpty(area.getRentCode())) {
            RentCondition<AreaDictionary> rentCondition = new RentCondition<AreaDictionary>();
            String field = " o.orgCode ";
            String rentOrgCodes = rentCondition.getRentOrgCodeCondition(area.getRentCode(), area.getBizCode(), field);
            if (StringUtils.isNotEmpty(rentOrgCodes)) {
                dataSql.append(" and " + rentOrgCodes);
                countSql.append(" and " + rentOrgCodes);
            } else {
                return new MyPageImpl(new ArrayList(), null, 0L);
            }
        }
        //----------处理租户过滤

        if (StringUtils.isNotEmpty(area.getOrderby())) {
            String value = SortParam.getSortParam(AreaDictionary.class, area.getOrderby());
            dataSql.append(value);
        }

        Query dataQuery = em.createQuery(dataSql.toString());
        Query countQuery = em.createQuery(countSql.toString());
        for (String parameter : parameterMap.keySet()) {
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
            countQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        long count = (long) countQuery.getResultList().get(0);
        if (null != pageable) {
            int skip = area.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
        }
        List<com.pcitc.fms.dal.pojo.Material> resultList = dataQuery.getResultList();
        MyPageImpl myPageImpl = new MyPageImpl(resultList, pageable, count);
        myPageImpl.setCount(count);
        return myPageImpl;
    }

    public Page<com.pcitc.fms.dal.pojo.AreaDictionary> getAreaDictionaryNodesModels(com.pcitc.fms.service.model.AreaDictionary areaDictionaryTableModel, Pageable pageable, String orgCode) {
        Map<String, Object> parameterMap = new HashedMap();
        String factoryCode = areaDictionaryTableModel.getFactoryCode();
        String sql = AreaNodeBasicSql.orgFindAllArea + " and b.orgCode = '" + orgCode + "' ";
        List<Area_NodeType_Num> nodeType_numList = null;

        if (null != areaDictionaryTableModel.getAreaCode()) {
            sql += " and a.areaCode = :areaCode";
            parameterMap.put("areaCode", areaDictionaryTableModel.getAreaCode());
            String sqlNodeTypeAndNum = AreaNodeBasicSql.orgFindAllAreaNodeTypeNum + " and b.orgCode = '" + orgCode + "'" + " group by t.nodeTypeId ,nodetype.nodeTypeName";
            Query dataQueryNodeTypeAndNum = em.createQuery(sqlNodeTypeAndNum);
            nodeType_numList = dataQueryNodeTypeAndNum.getResultList();
        }

        Query dataQuery = em.createQuery(sql);
        for (String parameter : parameterMap.keySet()) {
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        Integer skip = null;
        if (null != pageable) {
            String skipStr = areaDictionaryTableModel.getSkip();
            skip = Integer.parseInt(skipStr);
            long count = dataQuery.getResultList().size();
            dataQuery = em.createQuery(sql);
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
            return new PageImpl<com.pcitc.fms.dal.pojo.AreaDictionary>(dataQuery.getResultList(), pageable, count);
        } else {
            if (null != areaDictionaryTableModel && null != areaDictionaryTableModel.getTop()) {
                Integer top = Integer.parseInt(areaDictionaryTableModel.getTop());
                if (top >= 0) {
                    dataQuery.setMaxResults(top);
                }
            }
            if (null != areaDictionaryTableModel && null != areaDictionaryTableModel.getSkip()) {
                skip = Integer.parseInt(areaDictionaryTableModel.getSkip());
                if (skip >= 0) {
                    dataQuery.setFirstResult(skip);
                }
                dataQuery.setFirstResult(skip);
            }
            if (nodeType_numList != null) {
                AreaDictionary areaDictionary = (com.pcitc.fms.dal.pojo.AreaDictionary) dataQuery.getResultList().get(0);
                areaDictionary.setTypeAndNums(nodeType_numList);
                List<com.pcitc.fms.dal.pojo.AreaDictionary> areaDictionaryies = new ArrayList<com.pcitc.fms.dal.pojo.AreaDictionary>();
                areaDictionaryies.add(areaDictionary);
                return new PageImpl<com.pcitc.fms.dal.pojo.AreaDictionary>(areaDictionaryies, null, 0);
            } else {
                return new PageImpl<com.pcitc.fms.dal.pojo.AreaDictionary>(dataQuery.getResultList(), null, 0);
            }


        }
    }
}
