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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.UnitAlarm;
import com.pcitc.fms.service.model.Administration;
@Service
public interface UnitAlarmDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.UnitAlarm>, JpaRepository<com.pcitc.fms.dal.pojo.UnitAlarm,Integer>  {

	@Transactional
	UnitAlarm getByUnitAlarmCode(String code);
	
	@Transactional
	@SQLDelete(sql = "delete from UnitAlarm where unitAlarmCode = :unitAlarmCode")
	void deleteByUnitAlarmCode(@Param("unitAlarmCode")String unitAlarmCode);



}
