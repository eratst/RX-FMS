/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TIcNodetopMainDaoImpl
 * Date:18-3-9 上午8:35
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

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

import com.pcitc.fms.dal.pojo.TIcNodetopMain;

/**
 * Title: HeadquarterDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class TIcNodetopMainDaoImpl {

    /**
     * The Em.
     */
    @PersistenceContext
   private EntityManager em;

    /**
     * Find nodetop mains page.
     *
     * @param nodetopMainsTableModel the nodetop mains table model
     * @param pageable the pageable
     * @return the page
     */
    public Page<com.pcitc.fms.dal.pojo.TIcNodetopMain> findNodetopMains(
			com.pcitc.fms.service.model.TIcNodetopMain nodetopMainsTableModel, Pageable pageable){
	   
	   StringBuilder dataSql = new StringBuilder();
       dataSql.append( "select topMain from TIcNodetopMain topMain where 1 = 1");
       Map<String ,Object> parameterMap = new HashedMap();
       if (null != nodetopMainsTableModel.getTopCode() && !StringUtils.isEmpty(nodetopMainsTableModel.getTopCode())) {
           dataSql.append( " and topMain.topCode = :topCode");
           parameterMap.put("topCode", nodetopMainsTableModel.getTopCode());
       }
       if (null != nodetopMainsTableModel.getTopName() && !StringUtils.isEmpty(nodetopMainsTableModel.getTopName())) {
           dataSql.append(" and topMain.topName like :topName");
           parameterMap.put("topName", "%"+nodetopMainsTableModel.getTopName()+"%");
       }
       if (null != nodetopMainsTableModel.getTopAlias() && !StringUtils.isEmpty(nodetopMainsTableModel.getTopAlias())) {
           dataSql.append(" and topMain.topAlias like :topAlias");
           parameterMap.put("topAlias", "%"+nodetopMainsTableModel.getTopAlias()+"%");
       }
       if (null != nodetopMainsTableModel.getDataStatus() && !StringUtils.isEmpty(String.valueOf(nodetopMainsTableModel.getDataStatus()))) {
           dataSql.append(" and topMain.dataStatus = :dataStatus");
           parameterMap.put("dataStatus", nodetopMainsTableModel.getDataStatus());
       }
       if (null != nodetopMainsTableModel.getCodeList() && nodetopMainsTableModel.getCodeList().size() > 0) {
           dataSql.append( " and topMain.topCode in :topCodes");
           parameterMap.put("topCodes", nodetopMainsTableModel.getCodeList());
       }
       Query dataQuery = em.createQuery(dataSql.toString());
       for (String parameter : parameterMap.keySet()) {
           dataQuery.setParameter(parameter, parameterMap.get(parameter));
       }
       dataSql.append(" order by topMain.topId");
       List<TIcNodetopMain> resultList = dataQuery.getResultList();
       long count = resultList.size();
       if(null != pageable){
           int skip = nodetopMainsTableModel.getSkip();
           dataQuery.setFirstResult(skip);
           dataQuery.setMaxResults(pageable.getPageSize());
           return new PageImpl<com.pcitc.fms.dal.pojo.TIcNodetopMain>(resultList, pageable, count);
       }else{
           return new PageImpl<com.pcitc.fms.dal.pojo.TIcNodetopMain>(resultList, null, count);

       }
   }
}
