package com.pcitc.fms.dal.dao;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.SideLine;



public interface SideLineDao extends JpaSpecificationExecutor<SideLine>,JpaRepository<SideLine, Integer> {

	public MyPageImpl getSideLineModels(com.pcitc.fms.service.model.SideLine sideLineModel,Pageable pageable);

}



















