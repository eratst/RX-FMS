package com.pcitc.fms.dal.dao;

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
import com.pcitc.fms.dal.pojo.Measurement;

public interface MeasurementDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Measurement>,
		JpaRepository<com.pcitc.fms.dal.pojo.Measurement, Integer> {

	@Query(AreaNodeBasicSql.measurements+" and a.idxCode = :measurementCode")
	List<com.pcitc.fms.dal.pojo.Measurement> getMeasurementIdxCode(@Param("measurementCode") String measurementCode);
//
//
//
	@Transactional
	@SQLDelete(sql = "delete from com.pcitc.fms.dal.pojo.Measurement where idxCode = :measurementCode")
	void deleteByIdxCode(@Param("measurementCode") String measurementCode);
//
	@Transactional
	public MyPageImpl findPageMeasurements(com.pcitc.fms.service.model.Measurement measurementModel,
			Pageable pageable);
//	
	//@Query("from Measurement m where m.code in (select r.targetCode from Measurement t ,Relations r where t.code = r.targetCode and r.sourceCode in :relSourceCodeList and r.sourceType =  :sourceType and r.targetType = 'measurements')")
	@Query(AreaNodeBasicSql.measurements+" and a.nodeId in :relSourceCodeList")
	List<com.pcitc.fms.dal.pojo.Measurement> findForRel(@Param("relSourceCodeList")List<Long> relSourceCodeList);
//
	com.pcitc.fms.dal.pojo.Measurement getByIdxCode(String code);
}
