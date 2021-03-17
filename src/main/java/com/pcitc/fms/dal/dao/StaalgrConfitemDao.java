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

import com.pcitc.fms.dal.pojo.StaalgrConfitem;
import com.pcitc.fms.dal.pojo.UnitAlarm;
import com.pcitc.fms.service.model.Administration;
@Service
public interface StaalgrConfitemDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.StaalgrConfitem>, JpaRepository<com.pcitc.fms.dal.pojo.StaalgrConfitem,Integer>  {

	
	@Query("from StaalgrConfitem where code = :code")
	StaalgrConfitem getByCodes(@Param("code")String code);
//	@Transactional
//	@SQLDelete(sql = "delete from StaalgrConfitem where code = :code")
//	void deleteByCode(@Param("code")String code);
	@Query("from StaalgrConfitem where agentCode = :code")
	List<StaalgrConfitem> getByAgentCode(@Param("code")String code);
	@Transactional
	@SQLDelete(sql = "delete from StaalgrConfitem where opeindexId = :opeindexId")
	void deleteByOpeindexId(@Param("opeindexId")Integer opeindexId);
	@Query("from StaalgrConfitem where opeindexId = :opeindexId")
	List<StaalgrConfitem> getOpeindexId(@Param("opeindexId")Integer opeindexId);
	@Query("from StaalgrConfitem where opeindexId=:opeindexId and staalgrConfId=:staalgrConfId")
	StaalgrConfitem getByOpeindexIdAndStaalgrConfId(@Param("opeindexId")Integer opeindexId,@Param("staalgrConfId")Integer staalgrConfId);
	@Transactional
	@SQLDelete(sql = "delete from StaalgrConfitem where opeindexId=:opeindexId and staalgrConfId=:staalgrConfId")
	void deleteByOpeindexIdAndStaalgrConfId(@Param("opeindexId")Integer opeindexId,@Param("staalgrConfId")Integer staalgrConfId);
	@Query("from StaalgrConfitem where staalgrConfId=:staalgrConfId")
	List<StaalgrConfitem> getByStaalgrConfId(@Param("staalgrConfId")Integer staalgrConfId);
	@Transactional
	@SQLDelete(sql = "delete from StaalgrConfitem where staalgrConfId=:staalgrConfId")
	void deleteByStaalgrConfId(@Param("staalgrConfId")Integer staalgrConfId);
	@Transactional
	@SQLDelete(sql = "delete from StaalgrConfitem where agentCode = :agentCode")
	void deleteByAgentCode(@Param("agentCode")String agentCode);



}
