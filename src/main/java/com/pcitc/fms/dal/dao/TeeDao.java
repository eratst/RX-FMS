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
import com.pcitc.fms.dal.pojo.Tee;


public interface TeeDao extends JpaSpecificationExecutor<Tee>,JpaRepository<Tee, Integer>{
	
//    /**
//     * 通过单个三通
//     * @param parentCode 父级id
//     * @param teeCode  三通id
//     * @return 返回三通
//     */
	@Query(AreaNodeBasicSql.Tee+" and a.nodeCode = :teeCode")
	List<Tee> getTeeByNodeCode(@Param("teeCode") String teeCode);
//
	@Transactional
	@SQLDelete(sql = "delete from Tee where nodeCode = :teeCode")
	void deleteByNodeCode(@Param("teeCode") String teeCode);
//
//	
	@Transactional
	MyPageImpl getTeesByModel(com.pcitc.fms.service.model.Tee teeModel, Pageable pageable);
//
//	List<com.pcitc.fms.dal.pojo.Tee> findByParentTypeAndParentCode(String string, String plantCode);
	com.pcitc.fms.dal.pojo.Tee findByNodeCode(String nodeCode);
}


