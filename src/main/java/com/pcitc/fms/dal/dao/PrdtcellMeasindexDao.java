package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.PrdtcellMeasindex;

/**
 *
 * zhao.li
 */
public interface PrdtcellMeasindexDao
		extends JpaRepository<PrdtcellMeasindex, Integer>, JpaSpecificationExecutor<PrdtcellMeasindex> {

	@Query(AreaNodeBasicSql.prdtcellMeasindexs + " and prdtcellMeasindex.idxCode =:idxCode")
	public List<PrdtcellMeasindex> getPrdtcellMeasindexByIdxCode(@Param("idxCode") String idxCode);

	@Transactional
	public MyPageImpl getPrdtcellMeasindexs(
			com.pcitc.fms.service.model.PrdtcellMeasindex prdtcellMeasindex, Pageable pageable);

	// 删除
	@Modifying
	@Transactional
	@Query("delete from PrdtcellMeasindex where idxCode =:idxCode ")
	public void deletePrdtcellMeasindexByIdxCode(@Param("idxCode") String idxCode);

}
