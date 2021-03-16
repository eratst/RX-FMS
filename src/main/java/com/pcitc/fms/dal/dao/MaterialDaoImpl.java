/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: MaterialDaoImpl
 * Date:18-3-9 上午8:34
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.dal.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.SortParam;
import com.pcitc.fms.dal.pojo.Material;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.imp.common.exception.BusiException;


/**
 * The type Material dao.
 */
public class MaterialDaoImpl {
	
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
	 * Query materials page.
	 *
	 * @param model the model
	 * @param pageable the pageable
	 * @return the page
	 * @throws BusinessException 
	 * @throws BusiException 
	 * @throws Exception 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public MyPageImpl queryMaterials(com.pcitc.fms.service.model.Material model, Pageable pageable) throws BusinessException, BusiException{
		
		
//		
//		final String materials_news = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
//				+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
//				+" a.vcfType,vcf.vcfTypeCode, vcf.vcfTypeName,"
//				+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
//				+" a.crtUserId,a.crtUserName,a.crtDate,a.mntUserId,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.dataStatus,a.tankIdt)"
//				+" from Material a,MtrlType m,VcfType vcf, Dimension d where "
//				+" a.mtrlTypeId = m.mtrlTypeId and a.vcfType = vcf.vcfTypeId"
//				+" and a.dimensionId = d.dimensionId";
		
		String sql = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
				+" case  when  (select material.mtrlName from Material  material where material.mtrlCode = a.upperMtrlCode )  is NULL then '0' else (select material.mtrlName from Material  material where material.mtrlCode = a.upperMtrlCode)  end," 
				+" case  when  (select material.mtrlAlias from Material  material where material.mtrlCode = a.upperMtrlCode )  is NULL then '0' else (select material.mtrlAlias from Material  material where material.mtrlCode = a.upperMtrlCode)  end," 
				+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
				+" a.vcfTypeId,vcf.vcfTypeCode, vcf.vcfTypeName,"
				+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
				+" a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.inUse,a.tankIdt)"
				+" from Material a,MtrlType m,VcfType vcf, Dimension d where"
				+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
				+" and a.dimensionId = d.dimensionId";
		
		Map<String ,Object> parameterMap = new HashedMap();
		StringBuilder sqlSB = new StringBuilder();
		sqlSB.append(sql);
		
		String materials_count = "select count(1) "
				+" from Material a,Material mmm,MtrlType m,VcfType vcf, Dimension d where"
				+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
				+" and a.dimensionId = d.dimensionId and a.mtrlCode = mmm.mtrlCode ";
		StringBuilder countSql = new StringBuilder();
		countSql.append(materials_count);
		
		if(null != model.getMtrlCode() && !StringUtils.isEmpty(model.getMtrlCode())){
			sqlSB.append(" and a.mtrlCode = :mtrlCode");
			countSql.append(" and a.mtrlCode = :mtrlCode");
			parameterMap.put("mtrlCode", model.getMtrlCode());
		}
		if(null != model.getMtrlName() && !StringUtils.isEmpty(model.getMtrlName())){
			sqlSB.append(" and a.mtrlName like :mtrlName");
			countSql.append(" and a.mtrlName like :mtrlName");
			parameterMap.put("mtrlName", "%"+model.getMtrlName()+"%");
		}
		if(null != model.getMtrlAlias() && !StringUtils.isEmpty(model.getMtrlAlias())){
			sqlSB.append(" and a.mtrlAlias like :mtrlAlias");
			countSql.append(" and a.mtrlAlias like :mtrlAlias");
			parameterMap.put("mtrlAlias", "%"+model.getMtrlAlias()+"%");
		}

		if(null != model.getUpperMtrlCode() && !StringUtils.isEmpty(String.valueOf(model.getUpperMtrlCode()))){
			sqlSB.append(" and a.upperMtrlCode = :upperMtrlCode");
			countSql.append(" and a.upperMtrlCode = :upperMtrlCode");
			parameterMap.put("upperMtrlCode", model.getUpperMtrlCode());
		}
		if(null != model.getMtrlTypeCode() && !StringUtils.isEmpty(model.getMtrlTypeCode())){
			sqlSB.append( " and m.mtrlTypeCode = :mtrlTypeCode");
			countSql.append( " and m.mtrlTypeCode = :mtrlTypeCode");
			parameterMap.put("mtrlTypeCode", model.getMtrlTypeCode());
		}
		
		if(null != model.getInUse() && !StringUtils.isEmpty(String.valueOf(model.getInUse()))){
			sqlSB.append( " and a.inUse = :inUse");
			countSql.append( " and a.inUse = :inUse");
			parameterMap.put("inUse", model.getInUse());
		}
		if (null != model && null != model.getMtrlCodes() && model.getMtrlCodes().size() > 0) {
			sqlSB.append(" and a.mtrlCode in :mtrlCodes");
			countSql.append(" and a.mtrlCode in :mtrlCodes");
			parameterMap.put("mtrlCodes", model.getMtrlCodes());
		}
		if(null != model && model.getFilterData()!=null && model.getFilterData().size()>0){
			sqlSB.append(" and a.mtrlCode not in :mtrlCodes");
			countSql.append(" and a.mtrlCode not in :mtrlCodes");
			parameterMap.put("mtrlCodes", model.getFilterData());
		}
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(Material.class, model.getOrderby());
			sqlSB.append(value);
		}
		
		Query dataQuery = em.createQuery(sqlSB.toString());
		Query countQuery = em.createQuery(countSql.toString());
		
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			dataQuery.setFirstResult( model.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());

		} 
		
		List<com.pcitc.fms.dal.pojo.Material> resultList = dataQuery.getResultList();
		MyPageImpl myPageImpl=new MyPageImpl(resultList, pageable, count);
		myPageImpl.setCount(count);
		return myPageImpl;
	}
	
	public List<Material> getMtrlByCodestest(@Param("mtrlCodes") List<String> mtrlCodes,com.pcitc.fms.service.model.Material model,Pageable pageable) throws BusiException{
		
		String sqltest = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
				+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
				+" a.vcfTypeId,vcf.vcfTypeCode, vcf.vcfTypeName,"
				+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
				+" a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.inUse,a.tankIdt)"
				+" from Material a,MtrlType m,VcfType vcf, Dimension d where"
				+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
				+" and a.dimensionId = d.dimensionId";
		
		
		
		Map<String ,Object> parameterMap = new HashedMap();
		StringBuilder sqlSB = new StringBuilder();
		sqlSB.append(sqltest);
		
		String materials_count = "select count(1) "
				+" from Material a,MtrlType m,VcfType vcf, Dimension d where"
				+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
				+" and a.dimensionId = d.dimensionId";
		StringBuilder countSql = new StringBuilder();
		countSql.append(materials_count);
		
		if(null != model.getMtrlCode() && !StringUtils.isEmpty(model.getMtrlCode())){
			sqlSB.append(" and a.mtrlCode = :code");
			countSql.append(" and a.mtrlCode = :code");
			parameterMap.put("code", model.getMtrlCode());
		}
		if(null != model.getMtrlName() && !StringUtils.isEmpty(model.getMtrlName())){
			sqlSB.append(" and a.mtrlName like :name");
			countSql.append(" and a.mtrlName like :name");
			parameterMap.put("name", "%"+model.getMtrlName()+"%");
		}
		if(null != model.getMtrlAlias() && !StringUtils.isEmpty(model.getMtrlAlias())){
			sqlSB.append(" and a.mtrlAlias like :shortName");
			countSql.append(" and a.mtrlAlias like :shortName");
			parameterMap.put("shortName", "%"+model.getMtrlAlias()+"%");
		}

		if(null != model.getUpperMtrlCode() && !StringUtils.isEmpty(String.valueOf(model.getUpperMtrlCode()))){
			sqlSB.append(" and a.upperMtrlCode = :parentCode");
			countSql.append(" and a.upperMtrlCode = :parentCode");
			parameterMap.put("parentCode", model.getUpperMtrlCode());
		}
		if(null != model.getMtrlTypeCode() && !StringUtils.isEmpty(model.getMtrlTypeCode())){
			sqlSB.append( " and m.mtrlTypeCode = :mtrlTypeCode");
			countSql.append( " and m.mtrlTypeCode = :mtrlTypeCode");
			parameterMap.put("mtrlTypeCode", model.getMtrlTypeCode());
		}
		
		if(null != model.getInUse() && !StringUtils.isEmpty(String.valueOf(model.getInUse()))){
			sqlSB.append( " and a.inUse = :enabled");
			countSql.append( " and a.inUse = :enabled");
			parameterMap.put("enabled", model.getInUse());
		}
		if (null != model && null != model.getMtrlCodes() && model.getMtrlCodes().size() > 0) {
			sqlSB.append(" and a.mtrlCode in :codes");
			countSql.append(" and a.mtrlCode in :codes");
			parameterMap.put("codes", model.getMtrlCodes());
		}
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(Material.class, model.getOrderby());
			sqlSB.append(value);
		}
		
		Query dataQuery = em.createQuery(sqlSB.toString());
		Query countQuery = em.createQuery(countSql.toString());
		
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
			countQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		long count = (long) countQuery.getResultList().get(0);
		if (null != pageable) {
			dataQuery.setFirstResult( model.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());

		} 
		return dataQuery.getResultList();
		
	}
	
	
	
	public List<Material> getMtrlByCodeWithChildsTest(List<String> mtrlCodes,com.pcitc.fms.service.model.Material model,Pageable pageable) throws BusiException{
		String sql = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
				+ "mu.mtrlName,mu.mtrlAlias,"
				+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
				+" a.vcfTypeId,vcf.vcfTypeCode, vcf.vcfTypeName,"
				+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
				+" a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.inUse,a.tankIdt)"
				+" from Material a,Material mu,MtrlType m,VcfType vcf, Dimension d where mu.mtrlCode = a.upperMtrlCode and "
				+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
				+" and a.dimensionId = d.dimensionId";
		
		Map<String ,Object> parameterMap = new HashedMap();
		StringBuilder sqlSB = new StringBuilder();
		sqlSB.append(sql);
		if(mtrlCodes.size()>0){
			sqlSB.append(" and a.upperMtrlCode in :mtrlCodes");
			parameterMap.put("mtrlCodes", mtrlCodes);
		}
		if(null != model.getMtrlTypeCode() && !StringUtils.isEmpty(model.getMtrlTypeCode())){
			sqlSB.append( " and m.mtrlTypeCode = :mtrlTypeCode");
			parameterMap.put("mtrlTypeCode", model.getMtrlTypeCode());
		}
		if(null != model.getMtrlName() && !StringUtils.isEmpty(model.getMtrlName())){
			sqlSB.append(" and a.mtrlName like :name");
			parameterMap.put("name", "%"+model.getMtrlName()+"%");
		}
		if(null != model.getMtrlAlias() && !StringUtils.isEmpty(model.getMtrlAlias())){
			sqlSB.append(" and a.mtrlAlias like :shortName");
			parameterMap.put("shortName", "%"+model.getMtrlAlias()+"%");
		}
		if(null != model && model.getFilterData()!=null && model.getFilterData().size()>0){
			sqlSB.append(" and a.mtrlCode not in :FmtrlCodes");
			parameterMap.put("FmtrlCodes", model.getFilterData());
		}
		sqlSB.append(" and a.inUse=1");
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(Material.class, model.getOrderby());
			sqlSB.append(value);
		}
		
		Query dataQuery = em.createQuery(sqlSB.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer count=dataQuery.getResultList().size();
		this.setCount(count);
		if (null != pageable) {
			dataQuery.setFirstResult( model.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());

		}
		return dataQuery.getResultList();
	}
	
	public List<Material> getMtrlByCodeWithAncestors(String upperMtrlCode , com.pcitc.fms.service.model.Material model) throws BusiException{
		String sql = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
				+ "mu.mtrlName,mu.mtrlAlias,"
				+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
				+" a.vcfTypeId,vcf.vcfTypeCode, vcf.vcfTypeName,"
				+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
				+" a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.inUse,a.tankIdt)"
				+" from Material a,Material mu,MtrlType m,VcfType vcf, Dimension d where mu.mtrlCode = a.upperMtrlCode and "
				+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
				+" and a.dimensionId = d.dimensionId";
		
		Map<String ,Object> parameterMap = new HashedMap();
		StringBuilder sqlSB = new StringBuilder();
		sqlSB.append(sql);
		if(StringUtils.isNotEmpty(upperMtrlCode)){
			sqlSB.append(" and a.mtrlCode = :upperMtrlCode");
			parameterMap.put("upperMtrlCode", upperMtrlCode);
		}
		if(null != model.getMtrlTypeCode() && !StringUtils.isEmpty(model.getMtrlTypeCode())){
			sqlSB.append( " and m.mtrlTypeCode = :mtrlTypeCode");
			parameterMap.put("mtrlTypeCode", model.getMtrlTypeCode());
		}
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(Material.class, model.getOrderby());
			sqlSB.append(value);
		}
		if(null != model.getMtrlName() && !StringUtils.isEmpty(model.getMtrlName())){
			sqlSB.append(" and a.mtrlName like :name");
			parameterMap.put("name", "%"+model.getMtrlName()+"%");
		}
		if(null != model.getMtrlAlias() && !StringUtils.isEmpty(model.getMtrlAlias())){
			sqlSB.append(" and a.mtrlAlias like :shortName");
			parameterMap.put("shortName", "%"+model.getMtrlAlias()+"%");
		}
		if(null != model && model.getFilterData()!=null && model.getFilterData().size()>0){
			sqlSB.append(" and a.mtrlCode not in :FmtrlCodes");
			parameterMap.put("FmtrlCodes", model.getFilterData());
		}
		sqlSB.append(" and a.inUse=1");
		Query dataQuery = em.createQuery(sqlSB.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		
		return dataQuery.getResultList();
	}
	
	public List<Material> getAllToRoot(List<String> Codes , com.pcitc.fms.service.model.Material model,Pageable pageable) throws BusiException{
		String sql = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
				+ "mu.mtrlName,mu.mtrlAlias,"
				+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
				+" a.vcfTypeId,vcf.vcfTypeCode, vcf.vcfTypeName,"
				+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
				+" a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.inUse,a.tankIdt)"
				+" from Material a,Material mu,MtrlType m,VcfType vcf, Dimension d where mu.mtrlCode = a.upperMtrlCode and "
				+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
				+" and a.dimensionId = d.dimensionId";
		
		Map<String ,Object> parameterMap = new HashedMap();
		StringBuilder sqlSB = new StringBuilder();
		sqlSB.append(sql);
		if(Codes.size()>0){
			sqlSB.append(" and a.mtrlCode in :mtrlCodes");
			parameterMap.put("mtrlCodes", Codes);
		}
		if(null != model.getMtrlTypeCode() && !StringUtils.isEmpty(model.getMtrlTypeCode())){
			sqlSB.append( " and m.mtrlTypeCode = :mtrlTypeCode");
			parameterMap.put("mtrlTypeCode", model.getMtrlTypeCode());
		}
		if(null != model.getMtrlName() && !StringUtils.isEmpty(model.getMtrlName())){
			sqlSB.append(" and a.mtrlName like :name");
			parameterMap.put("name", "%"+model.getMtrlName()+"%");
		}
		if(null != model.getMtrlAlias() && !StringUtils.isEmpty(model.getMtrlAlias())){
			sqlSB.append(" and a.mtrlAlias like :shortName");
			parameterMap.put("shortName", "%"+model.getMtrlAlias()+"%");
		}
		if(null != model && model.getFilterData()!=null && model.getFilterData().size()>0){
			sqlSB.append(" and a.mtrlCode not in :FmtrlCodes");
			parameterMap.put("FmtrlCodes", model.getFilterData());
		}
		if (StringUtils.isNotEmpty(model.getOrderby())) {
			String value = SortParam.getSortParam(Material.class, model.getOrderby());
			sqlSB.append(value);
		}
		Query dataQuery = em.createQuery(sqlSB.toString());
		for (String parameter : parameterMap.keySet()) {
			dataQuery.setParameter(parameter, parameterMap.get(parameter));
		}
		Integer count=dataQuery.getResultList().size();
		this.setCount(count);
		if (null != pageable) {
			dataQuery.setFirstResult( model.getSkip());
			dataQuery.setMaxResults(pageable.getPageSize());

		}
		return dataQuery.getResultList();
	}
	public List<String> getAllMtrlCodeToRoot(String mtrlCodes){
		List<String> codeList = new ArrayList<>();
		Connection conn = null;
    	ResultSet resultSet = null;
    	Statement statement1 = null;
    	Statement statement2 = null;
    	try {
    		String sql = "select getMtrlRootCodeByCode('"+mtrlCodes+"');";
    		conn = dataSource.getConnection();
    		statement1 = conn.createStatement();
    		resultSet = statement1.executeQuery(sql);
    		while(resultSet.next()){
    			String resStr = resultSet.getString(1);
        		if (StringUtils.isNotEmpty(resStr)) {
        			codeList = Arrays.asList(resStr.split(","));
        		}
    		}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBCBasicInfo(conn,new Statement[]{statement1,statement2},new ResultSet[]{resultSet});
		}
    	return codeList;
	}
	
	
	//mysql中的树形结构包含级别
	public List<String> getAllCodeToChild(String mtrlCode,Integer levelValue){
		List<String> codeList = new ArrayList<>();
    	Connection conn = null;
    	ResultSet resultSet = null;
    	Statement statement1 = null;
//    	Statement statement2 = null;
    	try {
//    		String sql1 = "call SELECT_TEMPORARY_MTRL('"+mtrlCode+"');";
//    		String sql2 = "select t.MTRL_CODE from MTRL_TREE_LEVEL t where t.LEVEL<="+levelValue+"";
    		String sql = "select getMtrlChildByCode('"+mtrlCode+"')";
    		conn = dataSource.getConnection();
    		statement1 = conn.createStatement();
//    		statement2 = conn.createStatement();
//    		statement1.execute(sql1);
    		resultSet = statement1.executeQuery(sql);
    		while (resultSet.next()) {
    			String codeRes = resultSet.getString("getMtrlChildByCode('"+mtrlCode+"')");
    			if (StringUtils.isNotEmpty(codeRes)) {
    				String[] strs = codeRes.split(",");
    				codeList.addAll(Arrays.asList(strs));
    			}
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeJDBCBasicInfo(conn,new Statement[]{statement1},new ResultSet[]{resultSet});
		}
    	return codeList;
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

