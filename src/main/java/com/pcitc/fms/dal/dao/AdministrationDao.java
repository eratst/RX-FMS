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

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.AdministrationArea;
import com.pcitc.fms.service.model.Administration;

public interface AdministrationDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.AdministrationArea>, JpaRepository<com.pcitc.fms.dal.pojo.AdministrationArea,Integer>  {

	@Transactional
	public MyPageImpl findPageAdmins(Administration administrationModel,
			Pageable pageable);

}
