package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MeasurementDao extends JpaRepository<Measurement, Integer>, JpaSpecificationExecutor<Measurement> {

    @Transactional
    public Page<Measurement> findMeasurement(com.pcitc.fms.service.model.Measurement measurement, Pageable pageable);

    @Transactional
    public Page<Measurement> findMeasurementByNodes(com.pcitc.fms.service.model.Measurement measurement, Pageable pageable);

    @Transactional
    public Page<Measurement> findMeasurementByAreas(com.pcitc.fms.service.model.Measurement measurement, Pageable pageable);

    /*@Query(AreaNodeBasicSql.measureMentsOfNode + " and m.idxCode = :idxCode ")
    public List<Measurement> findMeasureMentByIdxCodeOfNode(@Param("idxCode") String idxCode);

    @Query(AreaNodeBasicSql.measureMentsOfArea + " and m.idxCode = :idxCode ")
    public List<Measurement> findMeasureMentByIdxCodeOfArea(@Param("idxCode") String idxCode);

    @Query(AreaNodeBasicSql.measureMentsOfNode + " and m.idxCode = :idxCode and m.ofMeasindexType=:ofMeasindexType ")
    public List<Measurement> findMeasureMentByIdxCodeOfNode(@Param("idxCode") String idxCode, @Param("ofMeasindexType") Integer ofMeasindexType);

    @Query(AreaNodeBasicSql.measureMentsOfArea + " and m.idxCode = :idxCode and m.ofMeasindexType=:ofMeasindexType ")
    public List<Measurement> findMeasureMentByIdxCodeOfArea(@Param("idxCode") String idxCode, @Param("ofMeasindexType") Integer ofMeasindexType);

    @Query("from Measurement where idxName = :idxName")
    public List<Measurement> findMeasureMentByIdxName(@Param("idxName") String idxName);*/

}
