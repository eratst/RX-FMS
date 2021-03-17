/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: BizOrgDTLDaoImpl
 * Date:18-3-9 上午8:33
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TPmBizOrgDTL;
import com.pcitc.imp.common.exception.BusiException;

/**
 * Title: BizOrgDTLDaoImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class BizOrgDTLDaoImpl {
	
	@Autowired
	private DataSource dataSource;
	
	private Integer count;

    public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
     * The Em.
     */
    @PersistenceContext
   private EntityManager em;
    
    /**
     * Find biz org dt ls page.
     *
     * @param tpmBizOrgDTLModel the tpm biz org dtl model
     * @param pageable the pageable
     * @return page
     * @Title: findBizOrgDTLs
     * @Description: 条件查询, 根据组织机构名称与简称模糊查询
     * @date 2017年11月21日
     * @return: Page<com.pcitc.fms.dal.pojo.TPmBizOrgDTL>
     * @author zhenqiang.zhao
     * @throws BusiException 
     */
    public MyPageImpl findBizOrgDTLs(com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel, Pageable pageable) throws BusiException{
    	String countBase = "select count(1) from TPmBizOrgDTL orgDTL ,TPmBizOrgMain  orgMain ,TPmOrg org,TPmOrgType orgType where" +
			" orgDTL.bizId = orgMain.bizId and orgDTL.orgId = org.orgId " +
			" and org.orgTypeId = orgType.orgTypeId ";
    	StringBuilder countSql = new StringBuilder();
		countSql.append(countBase);
       StringBuilder dataSql = new StringBuilder();
       dataSql.append(AreaNodeBasicSql.bizOrgDTLs +" and 1 = 1");
       
       Map<String ,Object> parameterMap = new HashedMap();
       if (null != tpmBizOrgDTLModel.getOrgTypeCode() && "children".equals(tpmBizOrgDTLModel.getOrgTypeCode())) {

       }

       if (null != tpmBizOrgDTLModel.getBizCode() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getBizCode())) {
           dataSql.append( " and orgMain.bizCode = :bizCode");
           countSql.append( " and orgMain.bizCode = :bizCode");
           parameterMap.put("bizCode", tpmBizOrgDTLModel.getBizCode());
       }
       
       if (null != tpmBizOrgDTLModel.getOrgCodes()  && tpmBizOrgDTLModel.getOrgCodes().size()>0) {
           dataSql.append( " and org.orgCode in :orgCodes");
           countSql.append( " and org.orgCode in :orgCodes");
           parameterMap.put("orgCodes", tpmBizOrgDTLModel.getOrgCodes());
       }

       if (null != tpmBizOrgDTLModel.getOrgCode()  && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgCode())) {
           dataSql.append( " and org.orgCode = :orgCode");
           countSql.append( " and org.orgCode = :orgCode");
           parameterMap.put("orgCode", tpmBizOrgDTLModel.getOrgCode());
       }
       if (null != tpmBizOrgDTLModel.getOrgName() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgName())) {
           dataSql.append(" and org.orgName like :orgName");
           countSql.append(" and org.orgName like :orgName");
           parameterMap.put("orgName", "%"+tpmBizOrgDTLModel.getOrgName()+"%");
       }
       if (null != tpmBizOrgDTLModel.getOrgAlias() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgAlias())) {
           dataSql.append(" and org.orgAlias like :orgAlias");
           countSql.append(" and org.orgAlias like :orgAlias");
           parameterMap.put("orgAlias", "%"+tpmBizOrgDTLModel.getOrgAlias()+"%");
       }
       if (null != tpmBizOrgDTLModel.getOrgTypeCode() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgTypeCode())) {
           dataSql.append(" and orgType.orgTypeCode = :orgTypeCode");
           countSql.append(" and orgType.orgTypeCode = :orgTypeCode");
           parameterMap.put("orgTypeCode", tpmBizOrgDTLModel.getOrgTypeCode());
       }

       if (null != tpmBizOrgDTLModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(tpmBizOrgDTLModel.getOrgTypeId()))) {
           dataSql.append(" and org.orgTypeId = :orgTypeId");
           countSql.append(" and org.orgTypeId = :orgTypeId");
           parameterMap.put("orgTypeId", tpmBizOrgDTLModel.getOrgTypeId());
       }

       if (null != tpmBizOrgDTLModel.getInUse() && !StringUtils.isEmpty(String.valueOf(tpmBizOrgDTLModel.getInUse()))) {
           dataSql.append(" and org.inUse = :inUse");
           countSql.append(" and org.inUse = :inUse");
           parameterMap.put("inUse", tpmBizOrgDTLModel.getInUse());
       }
       
       if (null != tpmBizOrgDTLModel.getExpendFlag()) {
           dataSql.append(" and orgDTL.expendFlag = :expendFlag");
           countSql.append(" and orgDTL.expendFlag = :expendFlag");
           parameterMap.put("expendFlag", tpmBizOrgDTLModel.getExpendFlag());
       }
       
       if (null != tpmBizOrgDTLModel.getOrgCodes() && tpmBizOrgDTLModel.getOrgCodes().size() > 0) {
           dataSql.append( " and org.orgCode in :orgCodes");
           countSql.append( " and org.orgCode in :orgCodes");
           parameterMap.put("orgCodes", tpmBizOrgDTLModel.getOrgCodes());
       }
       if(StringUtils.isNotEmpty(tpmBizOrgDTLModel.getParentOrgCode())){
    	   if(tpmBizOrgDTLModel.getParentOrgCode().equals("0")){
    		   dataSql.append( " and orgDTL.parentOrgId =:parentOrgId");
               countSql.append( " and orgDTL.parentOrgId =:parentOrgId");
               parameterMap.put("parentOrgId", 0L);
    	   }else{
    		   dataSql.append( " and orgDTL.parentOrgId =:parentOrgId");
               countSql.append( " and orgDTL.parentOrgId =:parentOrgId");
               parameterMap.put("parentOrgId", tpmBizOrgDTLModel.getParentOrgId());
    	   }
       }
       
       //----------处理租户过滤
       if(StringUtils.isNotEmpty(tpmBizOrgDTLModel.getRentCode())&&StringUtils.isEmpty(tpmBizOrgDTLModel.getBizCode())){
			RentCondition<TPmBizOrgDTL> rentCondition = new RentCondition<TPmBizOrgDTL>();
			String field=" orgMain.bizCode ";
			String rentOrgCodes = rentCondition.getRentBizCodeCondition(tpmBizOrgDTLModel.getRentCode(),field);
			if(StringUtils.isNotEmpty(rentOrgCodes)){
				dataSql.append(" and "+rentOrgCodes);
				countSql.append(" and "+rentOrgCodes);
			}else{
				return new MyPageImpl(new ArrayList(), null, 0L);
			}
		}
       //----------处理租户过滤
       
       if (StringUtils.isNotEmpty(tpmBizOrgDTLModel.getOrderby())) {
    	   String value = SortParam.getSortParam(TPmBizOrgDTL.class, tpmBizOrgDTLModel.getOrderby());
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
           int skip = tpmBizOrgDTLModel.getSkip();
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
    public List<TPmBizOrgDTL> getBizOrgDTLByOrgCodeAndChildren (Long orgId ,String bizCode,Integer inUse,com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel,Pageable pageable) throws BusiException{
    	 StringBuilder dataSql = new StringBuilder();
    	  Map<String ,Object> parameterMap = new HashedMap();
         dataSql.append(AreaNodeBasicSql.bizOrgDTLs +" and 1 = 1");
         if(orgId!=null){
        	 dataSql.append(" and orgDTL.parentOrgId = :orgId");
        	 parameterMap.put("orgId", orgId);
         }
         if(StringUtils.isNoneEmpty(bizCode)){
        	 dataSql.append(" and orgMain.bizCode = :bizCode");
        	 parameterMap.put("bizCode", bizCode);
         }
         if(inUse!=null){
        	 dataSql.append(" and orgDTL.inUse = 1");
         }
         if (null != tpmBizOrgDTLModel.getOrgTypeCode() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgTypeCode())) {
             dataSql.append(" and orgType.orgTypeCode = :orgTypeCode");
             parameterMap.put("orgTypeCode", tpmBizOrgDTLModel.getOrgTypeCode());
         }
         if (null != tpmBizOrgDTLModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(tpmBizOrgDTLModel.getOrgTypeId()))) {
             dataSql.append(" and org.orgTypeId = :orgTypeId");
             parameterMap.put("orgTypeId", tpmBizOrgDTLModel.getOrgTypeId());
         }
         if (null != tpmBizOrgDTLModel.getOrgName() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgName())) {
             dataSql.append(" and org.orgName like :orgName");
             parameterMap.put("orgName", "%"+tpmBizOrgDTLModel.getOrgName()+"%");
         }
         if (null != tpmBizOrgDTLModel.getOrgAlias() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgAlias())) {
             dataSql.append(" and org.orgAlias like :orgAlias");
             parameterMap.put("orgAlias", "%"+tpmBizOrgDTLModel.getOrgAlias()+"%");
         }
         if (null != tpmBizOrgDTLModel.getExpendFlag()) {
             dataSql.append(" and orgDTL.expendFlag = :expendFlag");
             parameterMap.put("expendFlag", tpmBizOrgDTLModel.getExpendFlag());
         }
         if (StringUtils.isNotEmpty(tpmBizOrgDTLModel.getOrderby())) {
      	   String value = SortParam.getSortParam(TPmBizOrgDTL.class, tpmBizOrgDTLModel.getOrderby());
      	   dataSql.append(value);
         } 
         Query dataQuery = em.createQuery(dataSql.toString());
         for (String parameter : parameterMap.keySet()) {
             dataQuery.setParameter(parameter, parameterMap.get(parameter));
         }
         Integer count=dataQuery.getResultList().size();
         this.setCount(count);
         if(null != pageable){
        	 int skip = tpmBizOrgDTLModel.getSkip();
             dataQuery.setFirstResult(skip);
             dataQuery.setMaxResults(pageable.getPageSize());
         }
         return dataQuery.getResultList();
   } 
    public List<TPmBizOrgDTL> getBizOrgDTLByOrgCodeAndAllChildren (List<String> orgCodes ,String bizCode,com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel,Pageable pageable) throws BusiException{
   	 StringBuilder dataSql = new StringBuilder();
   	  Map<String ,Object> parameterMap = new HashedMap();
        dataSql.append(AreaNodeBasicSql.bizOrgDTLs +" and 1 = 1");
        if(orgCodes.size()>0){
        	dataSql.append(" and orgDTL.orgCode in :orgCodes");
        	parameterMap.put("orgCodes", orgCodes);
        }
        if(StringUtils.isNoneEmpty(bizCode)){
       	 dataSql.append(" and orgMain.bizCode = :bizCode");
       	 parameterMap.put("bizCode", bizCode);
        }
        if (null != tpmBizOrgDTLModel.getOrgTypeCode() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgTypeCode())) {
            dataSql.append(" and orgType.orgTypeCode = :orgTypeCode");
            parameterMap.put("orgTypeCode", tpmBizOrgDTLModel.getOrgTypeCode());
        }
        if (null != tpmBizOrgDTLModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(tpmBizOrgDTLModel.getOrgTypeId()))) {
            dataSql.append(" and org.orgTypeId = :orgTypeId");
            parameterMap.put("orgTypeId", tpmBizOrgDTLModel.getOrgTypeId());
        }
        if (null != tpmBizOrgDTLModel.getOrgName() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgName())) {
            dataSql.append(" and org.orgName like :orgName");
            parameterMap.put("orgName", "%"+tpmBizOrgDTLModel.getOrgName()+"%");
        }
        if (null != tpmBizOrgDTLModel.getOrgAlias() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgAlias())) {
            dataSql.append(" and org.orgAlias like :orgAlias");
            parameterMap.put("orgAlias", "%"+tpmBizOrgDTLModel.getOrgAlias()+"%");
        }
        if (null != tpmBizOrgDTLModel.getExpendFlag()) {
            dataSql.append(" and orgDTL.expendFlag = :expendFlag");
            parameterMap.put("expendFlag", tpmBizOrgDTLModel.getExpendFlag());
        }
        dataSql.append(" and orgDTL.inUse = 1");
        if (StringUtils.isNotEmpty(tpmBizOrgDTLModel.getOrderby())) {
     	   String value = SortParam.getSortParam(TPmBizOrgDTL.class, tpmBizOrgDTLModel.getOrderby());
     	   dataSql.append(value);
        } 
        Query dataQuery = em.createQuery(dataSql.toString());
        for (String parameter : parameterMap.keySet()) {
            dataQuery.setParameter(parameter, parameterMap.get(parameter));
        }
        Integer count=dataQuery.getResultList().size();
        this.setCount(count);
        if(null != pageable){
       	 int skip = tpmBizOrgDTLModel.getSkip();
            dataQuery.setFirstResult(skip);
            dataQuery.setMaxResults(pageable.getPageSize());
        }
        return dataQuery.getResultList();
  } 
    
    public List<TPmBizOrgDTL> getBizOrgDTLByOrgCodeAndAllToRoot (List<String> orgCodes ,String bizCode,com.pcitc.fms.service.model.TPmBizOrgDTL tpmBizOrgDTLModel,Pageable pageable) throws BusiException{
      	 StringBuilder dataSql = new StringBuilder();
      	  Map<String ,Object> parameterMap = new HashedMap();
           dataSql.append(AreaNodeBasicSql.bizOrgDTLs +" and 1 = 1");
           
           if(orgCodes.size()>0){
           	dataSql.append(" and orgDTL.orgCode in :orgCodes");
           	parameterMap.put("orgCodes", orgCodes);
           }
           if(StringUtils.isNoneEmpty(bizCode)){
          	 dataSql.append(" and orgMain.bizCode = :bizCode");
          	 parameterMap.put("bizCode", bizCode);
           }
           if (null != tpmBizOrgDTLModel.getOrgTypeCode() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgTypeCode())) {
               dataSql.append(" and orgType.orgTypeCode = :orgTypeCode");
               parameterMap.put("orgTypeCode", tpmBizOrgDTLModel.getOrgTypeCode());
           }
           if (null != tpmBizOrgDTLModel.getOrgTypeId() && !StringUtils.isEmpty(String.valueOf(tpmBizOrgDTLModel.getOrgTypeId()))) {
               dataSql.append(" and org.orgTypeId = :orgTypeId");
               parameterMap.put("orgTypeId", tpmBizOrgDTLModel.getOrgTypeId());
           }
           if (null != tpmBizOrgDTLModel.getOrgName() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgName())) {
               dataSql.append(" and org.orgName like :orgName");
               parameterMap.put("orgName", "%"+tpmBizOrgDTLModel.getOrgName()+"%");
           }
           if (null != tpmBizOrgDTLModel.getOrgAlias() && !StringUtils.isEmpty(tpmBizOrgDTLModel.getOrgAlias())) {
               dataSql.append(" and org.orgAlias like :orgAlias");
               parameterMap.put("orgAlias", "%"+tpmBizOrgDTLModel.getOrgAlias()+"%");
           }
           if (null != tpmBizOrgDTLModel.getExpendFlag()) {
               dataSql.append(" and orgDTL.expendFlag = :expendFlag");
               parameterMap.put("expendFlag", tpmBizOrgDTLModel.getExpendFlag());
           }
           dataSql.append(" and orgDTL.inUse = 1");
           if (StringUtils.isNotEmpty(tpmBizOrgDTLModel.getOrderby())) {
        	   String value = SortParam.getSortParam(TPmBizOrgDTL.class, tpmBizOrgDTLModel.getOrderby());
        	   dataSql.append(value);
           } 
           Query dataQuery = em.createQuery(dataSql.toString());
           for (String parameter : parameterMap.keySet()) {
               dataQuery.setParameter(parameter, parameterMap.get(parameter));
           }
           Integer count=dataQuery.getResultList().size();
           this.setCount(count);
           if(null != pageable){
             	 int skip = tpmBizOrgDTLModel.getSkip();
                  dataQuery.setFirstResult(skip);
                  dataQuery.setMaxResults(pageable.getPageSize());
              }
           return dataQuery.getResultList();
     } 
    
    //mysql中的树形结构包含级别
    public List<String> getAllChildrenOrgCodes(String orgCode,Integer levelValue) {
    	List<String> orgCodeList = new ArrayList<>();
    	Connection conn = null;
    	ResultSet resultSet = null;
    	Statement statement1 = null;
    	Statement statement2 = null;
    	try {
    		String sql1 = "CALL SELECT_TEMPORARY_DTL('"+orgCode+"');";
    		String sql2 = "select t.ORG_CODE from ORG_TREE_LEVEL t where t.LEVEL<="+levelValue+";";
    		conn = dataSource.getConnection();
    		statement1 = conn.createStatement();
    		statement2 = conn.createStatement();
    		statement1.execute(sql1);
    		resultSet = statement2.executeQuery(sql2);
    		while (resultSet.next()) {
    			String orgCodeRes = resultSet.getString("ORG_CODE");
    			orgCodeList.add(orgCodeRes);
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBCBasicInfo(conn,new Statement[]{statement1,statement2},new ResultSet[]{resultSet});
		}
    	return orgCodeList;
    }
    
    public List<String> getLeavesCodes(String orgCode,String bizCode){
    	List<String> orgCodeList = new ArrayList<>();
    	Connection conn = null;
    	ResultSet resultSet = null;
    	Statement statement = null;
    	try {
    		String sql = "";
    		if (StringUtils.isEmpty(bizCode)) {
    			sql = "select * from T_PM_BIZORGDTL t where FIND_IN_SET(t.ORG_ID,getLeafNodeListForDtl('"+orgCode+"',null));";
    		} else {
    			sql = "select * from T_PM_BIZORGDTL t where FIND_IN_SET(t.ORG_ID,getLeafNodeListForDtl('"+orgCode+"','"+bizCode+"'));";
    		}
    		
    		conn = dataSource.getConnection();
    		statement = conn.createStatement();
    		resultSet = statement.executeQuery(sql);
    		while (resultSet.next()) {
    			String orgCodeRes = resultSet.getString("ORG_CODE");
    			orgCodeList.add(orgCodeRes);
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBCBasicInfo(conn,new Statement[]{statement},new ResultSet[]{resultSet});
		}
    	return orgCodeList;
    }
    
    
 private static void closeJDBCBasicInfo(Connection conn,Statement[] statements,ResultSet[] resultSets){
    	
    	for (int i=0;i<resultSets.length;i++) {
    		if (resultSets[i]!=null) {
    			try {
    				resultSets[i].close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
    	for (int i=0;i<statements.length;i++) {
    		if (statements[i]!=null) {
    			try {
    				statements[i].close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	
		if (conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
}
