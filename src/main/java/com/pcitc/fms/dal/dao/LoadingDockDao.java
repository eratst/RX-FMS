package com.pcitc.fms.dal.dao;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.LoadingDock;
import com.pcitc.fms.dal.pojo.LoadingDockArea;



public interface LoadingDockDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.LoadingDock>,JpaRepository<com.pcitc.fms.dal.pojo.LoadingDock, Integer>{

	@Transactional
	MyPageImpl getLoadingDocksByModel(
                com.pcitc.fms.service.model.LoadingDock loadingDockModel, Pageable pageable);

}
