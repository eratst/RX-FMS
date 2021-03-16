package com.pcitc.fms.dal.dao;

import java.util.Date;
import java.util.List;

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
import com.pcitc.fms.dal.pojo.CommunityArea;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

public interface CommunityDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Community>,JpaRepository<com.pcitc.fms.dal.pojo.Community, Integer> {

	@Transactional
	public MyPageImpl findPages(com.pcitc.fms.service.model.Community model, Pageable pageable) throws BusinessException;

}
