/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: AreaNodeTypeDaoImpl
 * Date:18-3-9 上午8:33
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Description: zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class AreaNodeTypeDaoImpl {

    /**
     * The Em.
     */
    @PersistenceContext
   private EntityManager em;

    /**
     * Find area node types page.
     *
     * @param tpmAreaNodeType the tpm area node type
     * @param pageable the pageable
     * @return page
     * @Title: findAreaNodeTypes
     * @date 2017年11月21日
     * @return: Page<com.pcitc.fms.dal.pojo.TPmAreaNodeType>
     * @author zhenqiang.zhao
     */
    public Page<com.pcitc.fms.dal.pojo.TPmAreaNodeType> findAreaNodeTypes(com.pcitc.fms.service.model.TPmAreaNodeType tpmAreaNodeType, Pageable pageable){
       
       StringBuilder dataSql = new StringBuilder();
       dataSql.append(AreaNodeBasicSql.areaNodeTypes +" and 1 = 1");
       Map<String ,Object> parameterMap = new HashedMap();

       if (null != tpmAreaNodeType.getAreaCode()  && !StringUtils.isEmpty(tpmAreaNodeType.getAreaCode())) {
           dataSql.append( " and area.areaCode = :areaCode");
           parameterMap.put("areaCode", tpmAreaNodeType.getAreaCode());
       }

       if (null != tpmAreaNodeType.getNodeTypeCode()  && !StringUtils.isEmpty(tpmAreaNodeType.getNodeTypeCode())) {
           dataSql.append( " and nodetype.nodeTypeCode = :nodeTypeCode");
           parameterMap.put("nodeTypeCode", tpmAreaNodeType.getNodeTypeCode());
       }

       if (null != tpmAreaNodeType.getCodeList() && tpmAreaNodeType.getCodeList().size() > 0) {
           dataSql.append( " and area.areaCode in :orgCodes");
           parameterMap.put("orgCodes", tpmAreaNodeType.getCodeList());
       }
       Query dataQuery = em.createQuery(dataSql.toString());
       for (String parameter : parameterMap.keySet()) {
           dataQuery.setParameter(parameter, parameterMap.get(parameter));
       }
       dataSql.append(" order by org.areaCode");
       long count = dataQuery.getResultList().size();
       if(null != pageable){
           int skip = tpmAreaNodeType.getSkip();
           dataQuery.setFirstResult(skip);
           dataQuery.setMaxResults(pageable.getPageSize());
           return new PageImpl<com.pcitc.fms.dal.pojo.TPmAreaNodeType>(dataQuery.getResultList(), pageable, count);
       }else{
           return new PageImpl<com.pcitc.fms.dal.pojo.TPmAreaNodeType>(dataQuery.getResultList(), null, count);
           
       }
    
   }
   
}
