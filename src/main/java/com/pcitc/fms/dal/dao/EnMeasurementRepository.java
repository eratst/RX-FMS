package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.EnMeasurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EnMeasurementRepository extends JpaRepository<EnMeasurement, Integer>, JpaSpecificationExecutor<EnMeasurement> {

    @Transactional
    public Page<EnMeasurement> findEnMeasurement(com.pcitc.fms.service.model.EnMeasurement enMeasurement, Pageable pageable);

    @Query(AreaNodeBasicSql.enMeasurements + " and m.idxCode = :idxCode and biz.bizCode=:bizCode")
    public List<EnMeasurement> findMeasureMentByIdxCode(@Param("idxCode") String idxCode, @Param("bizCode") String bizCode);

    @Query(AreaNodeBasicSql.enMeasurements + " and m.idxCode = :idxCode and m.ofMeasindexType=:ofMeasindexType and biz.bizCode=:bizCode")
    public List<EnMeasurement> findMeasureMentByIdxCode(@Param("idxCode") String idxCode,
                                                        @Param("ofMeasindexType") Integer ofMeasindexType,
                                                        @Param("bizCode") String bizCode);

    @Query(AreaNodeBasicSql.enMeasurements + " and m.idxCode = :idxCode and m.ofMeasindexType=:ofMeasindexType")
    public List<EnMeasurement> findMeasureMentByIdxCode(@Param("idxCode") String idxCode, @Param("ofMeasindexType") Integer ofMeasindexType);

    @Query("from EnMeasurement where idxName = :idxName")
    public List<EnMeasurement> findMeasureMentByIdxName(@Param("idxName") String idxName);


    /*删除*/
    /*@Modifying
    @Transactional
    @Query("delete from EnMeasurement enm ,Measurement m where enm.idxId  from Measurementand enm.ofMeasindexType=:ofMeasindexType and enm.idxId=m.idxId")
    public Integer deleteMeasureMentByIdxCode(@Param("idxCode") String idxCode, @Param("ofMeasindexType") Integer ofMeasindexType);*/

}
