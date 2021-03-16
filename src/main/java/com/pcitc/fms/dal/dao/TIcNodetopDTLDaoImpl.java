/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TIcNodetopDTLDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;

import java.util.List;
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
 * Title: BizOrgDTLDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class TIcNodetopDTLDaoImpl {

  /**
   * The Em.
   */
  @PersistenceContext
   private EntityManager em;

  /**
   * Find nodetop dt ls page.
   *
   * @param tpmNodetopDTLModel the tpm nodetop dtl model
   * @param pageable the pageable
   * @return page
   * @Title: findBizOrgDTLs
   * @Description: 条件查询, 根据拓扑结构名称与简称模糊查询
   * @return: Page<com.pcitc.fms.dal.pojo.TIcNodetopDTL>
   * @author zhenqiang.zhao
   */
  public Page<com.pcitc.fms.dal.pojo.TIcNodetopDTL> findNodetopDTLs(com.pcitc.fms.service.model.TIcNodetopDTL tpmNodetopDTLModel, Pageable pageable){
       
       StringBuilder dataSql = new StringBuilder();
       Map<String ,Object> parameterMap = new HashedMap();
       if (null != tpmNodetopDTLModel.getScodeList() && tpmNodetopDTLModel.getScodeList().size() > 0) {
           dataSql.append(AreaNodeBasicSql.snodetopDTLs +" and 1 = 1");
           dataSql.append( " and node.nodeCode in :sCodeList");
           parameterMap.put("sCodeList", tpmNodetopDTLModel.getScodeList());
       }else if (null != tpmNodetopDTLModel.getTcodeList() && tpmNodetopDTLModel.getTcodeList().size() > 0) {
           dataSql.append(AreaNodeBasicSql.tnodetopDTLs +" and 1 = 1");
           dataSql.append( " and node.nodeCode in :tCodeList");
           parameterMap.put("tCodeList", tpmNodetopDTLModel.getTcodeList());
       }else{
           dataSql.append(AreaNodeBasicSql.snodetopDTLs +" and 1 = 1");
       }


       if (null != tpmNodetopDTLModel.getTopCode()  && !StringUtils.isEmpty(tpmNodetopDTLModel.getTopCode())) {
           dataSql.append( " and main.topCode = :topCode");
           parameterMap.put("topCode", tpmNodetopDTLModel.getTopCode());
       }
       if (null != tpmNodetopDTLModel.getTopName() && !StringUtils.isEmpty(tpmNodetopDTLModel.getTopName())) {
           dataSql.append(" and main.topName like :topName");
           parameterMap.put("topName", "%"+tpmNodetopDTLModel.getTopName()+"%");
       }
       if (null != tpmNodetopDTLModel.getTopAlias() && !StringUtils.isEmpty(tpmNodetopDTLModel.getTopAlias())) {
           dataSql.append(" and main.topAlias like :topAlias");
           parameterMap.put("getTopAlias", "%"+tpmNodetopDTLModel.getTopAlias()+"%");
       }
       if (null != tpmNodetopDTLModel.getSnodeCode()&& !StringUtils.isEmpty(tpmNodetopDTLModel.getSnodeCode())) {
           dataSql.append(" and dtl.sNodeCode = :sNodeCode");
           parameterMap.put("sNodeCode", tpmNodetopDTLModel.getSnodeCode());
       }

       if (null != tpmNodetopDTLModel.getTnodeCode() && !StringUtils.isEmpty(tpmNodetopDTLModel.getTnodeCode())) {
           dataSql.append(" and dtl.tNodeCode = :tNodeCode");
           parameterMap.put("tNodeCode", tpmNodetopDTLModel.getTnodeCode());
       }


       Query dataQuery = em.createQuery(dataSql.toString());
       for (String parameter : parameterMap.keySet()) {
           dataQuery.setParameter(parameter, parameterMap.get(parameter));
       }
       dataSql.append(" order by org.dataId");
       long count = dataQuery.getResultList().size();
       if(null != pageable){
           int skip = tpmNodetopDTLModel.getSkip();
           dataQuery.setFirstResult(skip);
           dataQuery.setMaxResults(pageable.getPageSize());
           return new PageImpl<com.pcitc.fms.dal.pojo.TIcNodetopDTL>(dataQuery.getResultList(), pageable, count);
       }else{
           return new PageImpl<com.pcitc.fms.dal.pojo.TIcNodetopDTL>(dataQuery.getResultList(), null, count);
           
       }
    
   }

  /**
   * Find by data code list.
   *
   * @param topCode the top code
   * @param dataCode the data code
   * @return the list
   */
  @SuppressWarnings("unchecked")
public List<com.pcitc.fms.dal.pojo.TIcNodetopDTL> findByDataCode(String topCode ,String dataCode){
       
       StringBuilder dataSql = new StringBuilder();
       Map<String ,Object> parameterMap = new HashedMap();
       dataSql.append(AreaNodeBasicSql.snodetopDTLs +" and 1 = 1");
       dataSql.append( " and main.topCode = '" + topCode);
       dataSql.append( "' and dtl.dataCode = '" + dataCode + "'");
      


       Query dataQuery = em.createQuery(dataSql.toString());
       for (String parameter : parameterMap.keySet()) {
           dataQuery.setParameter(parameter, parameterMap.get(parameter));
       }
       dataSql.append(" order by org.dataId");
       return dataQuery.getResultList();
   }
}
