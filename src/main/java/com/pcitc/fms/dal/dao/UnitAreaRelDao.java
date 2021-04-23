package com.pcitc.fms.dal.dao;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.UnitAreaRel;

/**
 * 
 * @author zhao.li
 *
 */
public interface UnitAreaRelDao extends JpaRepository<UnitAreaRel, Long>, JpaSpecificationExecutor<UnitAreaRel> {

	@Query(AreaNodeBasicSql.unitAreaRel + " and plant.plantCode= :plantCode and unitArea.unitAreaCode = :unitAreaCode and biz.bizCode = :bizCode ")
	public UnitAreaRel getUnitAreaRelByCodes(
			@Param("plantCode") String unitCode, @Param("unitAreaCode") String unitAreaCode, @Param("bizCode") String bizCode);

	@Transactional
	public Page<UnitAreaRel> findUnitAreaRels(com.pcitc.fms.service.model.UnitAreaRel unitAreaRelModel, Pageable pageable);

	@Transactional
    @SQLDelete(sql = " delete from UnitAreaRel t where t.unitAreaId = :unitAreaId ")
    public void deleteByUnitAreaId(@Param("unitAreaId") Long unitAreaId);

}
