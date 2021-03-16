package com.pcitc.fms.dal.dao;

import com.pcitc.fms.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pcitc.fms.dal.pojo.TIcNodetopMain;

/**
* Title: TIcNodetopMainDao
* Description: TODO task mark zhenqiang.zhao
* @author he.yang
* @date 2017年1月18日
* @version 1.0
*/
public interface TIcNodetopMainDao extends JpaSpecificationExecutor<TIcNodetopMain>,JpaRepository<TIcNodetopMain, Integer> {

	TIcNodetopMain findByTopCode(String topCode) throws BusinessException;

	Page<com.pcitc.fms.dal.pojo.TIcNodetopMain> findNodetopMains(
			com.pcitc.fms.service.model.TIcNodetopMain nodetopMainsTableModel, Pageable pageable);

}
