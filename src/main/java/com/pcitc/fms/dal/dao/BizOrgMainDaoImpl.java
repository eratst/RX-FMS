/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: BizOrgMainDaoImpl
 * Date:18-3-9 上午8:33
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Headquarter;
import com.pcitc.fms.dal.pojo.PlantArea;
import com.pcitc.fms.dal.pojo.TPmBizOrgMain;
import com.pcitc.imp.common.exception.BusiException;

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
import java.util.Map;

/**
 * Title: HeadquarterDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class BizOrgMainDaoImpl {

    /**
     * The Em.
     */
    @PersistenceContext
   private EntityManager em;

    /**
     * Find biz org mains page.
     *
     * @param bizOrgMainModel the biz org main model
     * @param pageable the pageable
     * @return page
     * @Title: findHeadquarters
     * @Description: 条件查询, 根据组织机构名称与简称模糊查询
     * @date 2017年11月21日
     * @return: Page<com.pcitc.fms.dal.pojo.TPmBizOrgMain>
     * @author zhenqiang.zhao
     * @throws BusiException 
     */
    @SuppressWarnings("unchecked")
   public MyPageImpl findBizOrgMains(com.pcitc.fms.service.model.TPmBizOrgMain bizOrgMainModel, Pageable pageable) throws BusiException{

       StringBuilder dataSql = new StringBuilder();
       dataSql.append( "select new TPmBizOrgMain(orgMain.bizId,orgMain.bizCode,orgMain.bizName,orgMain.bizAlias,orgMain.inUse,orgMain.crtUserCode,orgMain.crtUserName"
       		+ ",orgMain.crtDate,orgMain.mntUserCode,orgMain.mntUserName,orgMain.mntDate,orgMain.version,orgMain.sortNum,orgMain.des,orgMain.isStandard,orgMain.rentId,r.rentCode) from TPmBizOrgMain orgMain,Rent r where orgMain.rentId=r.rentId and 1 = 1 ");
       Map<String ,Object> parameterMap = new HashedMap();
       String countBase = "select count(1) from TPmBizOrgMain orgMain,Rent r where orgMain.rentId=r.rentId and 1 = 1 ";
       StringBuilder countSql = new StringBuilder();
       countSql.append(countBase);
       if (bizOrgMainModel!=null) {
    	   if (null != bizOrgMainModel.getBizCode()  && !StringUtils.isEmpty(bizOrgMainModel.getBizCode())) {
               dataSql.append( " and orgMain.bizCode = :bizCode");
               countSql.append( " and orgMain.bizCode = :bizCode");
               parameterMap.put("bizCode", bizOrgMainModel.getBizCode());
           }
           if (null != bizOrgMainModel.getBizName() && !StringUtils.isEmpty(bizOrgMainModel.getBizName())) {
               dataSql.append(" and orgMain.bizName like :bizName");
               countSql.append(" and orgMain.bizName like :bizName");
               parameterMap.put("bizName", "%"+bizOrgMainModel.getBizName()+"%");
           }
           if (null != bizOrgMainModel.getBizAlias() && !StringUtils.isEmpty(bizOrgMainModel.getBizAlias())) {
               dataSql.append(" and orgMain.bizAlias like :orgAlias");
               countSql.append(" and orgMain.bizAlias like :orgAlias");
               parameterMap.put("orgAlias", "%"+bizOrgMainModel.getBizAlias()+"%");
           }
           if (null != bizOrgMainModel.getInUse() && !StringUtils.isEmpty(String.valueOf(bizOrgMainModel.getInUse()))) {
               dataSql.append(" and orgMain.inUse = :inUse");
               countSql.append(" and orgMain.inUse = :inUse");
               parameterMap.put("inUse", bizOrgMainModel.getInUse());
           }
           if(bizOrgMainModel.getIsStandard()!=null){
        	   dataSql.append(" and orgMain.isStandard = :isStandard");
               countSql.append(" and orgMain.isStandard = :isStandard");
               parameterMap.put("isStandard", bizOrgMainModel.getIsStandard());
           }
       }
      
       if (null != bizOrgMainModel.getBizCodes()&& bizOrgMainModel.getBizCodes().size() > 0) {
           dataSql.append( " and orgMain.bizCode in :bizCodes");
           countSql.append( " and orgMain.bizCode in :bizCodes");
           parameterMap.put("bizCodes", bizOrgMainModel.getBizCodes());
       }
     //----------处理租户过滤
     		if(StringUtils.isNotEmpty(bizOrgMainModel.getRentCode())){
     			RentCondition<TPmBizOrgMain> rentCondition = new RentCondition<TPmBizOrgMain>();
     			String field=" orgMain.bizCode ";
     			String rentOrgCodes = rentCondition.getRentBizCodeCondition(bizOrgMainModel.getRentCode(),field);
     			if(StringUtils.isNotEmpty(rentOrgCodes)){
     				dataSql.append(" and "+rentOrgCodes);
     				countSql.append(" and "+rentOrgCodes);
     			}else{
     				return new MyPageImpl(new ArrayList(), null, 0L);
     			}
     		}
     //----------处理租户过滤
       if (StringUtils.isNotEmpty(bizOrgMainModel.getOrderby())) {
    	  String value = SortParam.getSortParam(TPmBizOrgMain.class, bizOrgMainModel.getOrderby());
    	  dataSql.append(value);
       }
       
       Query dataQuery = em.createQuery(dataSql.toString());
       Query countQuery = em.createQuery(countSql.toString());
       for (String parameter : parameterMap.keySet()) {
           dataQuery.setParameter(parameter, parameterMap.get(parameter));
           countQuery.setParameter(parameter, parameterMap.get(parameter));
       }
       long count = (long) countQuery.getResultList().get(0);
       if(null != pageable){
           int skip = bizOrgMainModel.getSkip();
           dataQuery.setFirstResult(skip);
           dataQuery.setMaxResults(pageable.getPageSize());
           
           MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), pageable, count);
           myPageImpl.setCount(count);
           return myPageImpl;
       }else{
    	   MyPageImpl myPageImpl = new MyPageImpl(dataQuery.getResultList(), null, count);
           myPageImpl.setCount(count);
           return myPageImpl;
       }

   }

}
