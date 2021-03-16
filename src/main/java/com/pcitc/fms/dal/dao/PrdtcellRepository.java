package com.pcitc.fms.dal.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Prdtcell;

public interface PrdtcellRepository extends JpaRepository<Prdtcell, Integer>, JpaSpecificationExecutor<Prdtcell> {

	@Transactional
	public MyPageImpl getPrdtcells(com.pcitc.fms.service.model.Prdtcell prdtcell, Pageable pageable);
	
	@Query(AreaNodeBasicSql.prdtcells + " and prdtcell.cellCode = :cellCode")
	public List<Prdtcell> getPrdtcellByCellCode(@Param("cellCode") String cellCode);

	@Query(value = "select t.cell_id from T_PM_PRDTCELL t where t.cell_abbr_name = :cellAbbrName", nativeQuery = true)
	public List<BigDecimal> getCellId(@Param("cellAbbrName") String cellAbbrName);

}
