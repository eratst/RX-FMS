package com.pcitc.fms.dal.dao;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Silo;
import com.pcitc.fms.dal.pojo.Stock;
import com.pcitc.fms.service.model.StockModelStr;

public interface StockDao extends JpaSpecificationExecutor<Stock>, JpaRepository<Stock, Integer> {

	/**
	 * 查询单个库位
	 * 
	 * @param stockCode
	 * @return
	 */
//	@Query(AreaNodeBasicSql.Stock+" and a.nodeCode = :stockCode")
//	List<Stock> getStockByNodeCode(@Param("stockCode") String stockCode);
//
//	@Modifying
//	@Transactional
//	@Query("update Stock set code = :code,name = :name,shortName = :shortName,enabled = :enabled,editor = :editor,maintainTime=sysdate where stockId = :stockId")
//	void updateStock(@Param("stockId") Integer stockId, @Param("code") String code, @Param("name") String name,
//			@Param("shortName") String shortName, @Param("enabled") Integer enabled, @Param("editor") String editor);
//
	@Transactional
	@SQLDelete(sql = "delete from Stock where nodeCode = :stockCode")
	void deleteByNodeCode(@Param("stockCode") String stockCode);
//
//	@Query("select t from Stock t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'stocks'")
//	List<Stock> findLinkStocks(@Param("parentCode") String parentCode, @Param("parentType") String parentType);
//
//	@Query("select t from Stock t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'stocks' and t.code=:tankCode")
//	List<Stock> findLinkStocks(@Param("parentCode") String parentCode, @Param("parentType") String parentType,
//			@Param("tankCode") String tankCode);
//
//	/**
//	 * 用code查询 校验是否存在数据
//	 * 
//	 * @param code
//	 * @return
//	 */
//	public List<com.pcitc.fms.dal.pojo.Stock> findByCode(String code);
//
	/**
	 * 
	 * @param modelStr
	 * @param pageable
	 * @return
	 */
	@Transactional(rollbackFor = BusinessException.class)
	MyPageImpl findPageStocks(com.pcitc.fms.service.model.Stock modelStr, Pageable pageable);
//
//	com.pcitc.fms.dal.pojo.Stock findStockByCode(String stockCode);

}
