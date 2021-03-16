package com.pcitc.fms.dal.dao;


import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.StaalgrConf;
@Service
public interface StaalgrConfDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.StaalgrConf>, JpaRepository<com.pcitc.fms.dal.pojo.StaalgrConf,Integer>  {

	

	StaalgrConf getByCode(String code);
	@Transactional
	@SQLDelete(sql = "delete from StaalgrConf where code = :code")
	void deleteByCode(@Param("code")String code);
	
	@Query("from StaalgrConf where equipId=:equipId and monLevelId=:monLevelId")
	StaalgrConf getByEquipIdAndMonLevelId(@Param("equipId")Integer equipId, @Param("monLevelId")Integer monLevelId);
	@Transactional
	@SQLDelete(sql = "delete from StaalgrConf where equipId=:equipId and monLevelId=:monLevelId")
	void deleteByMonLevelIdAndEquipId(@Param("monLevelId")Integer monLevelId,@Param("equipId")Integer equipId);
	@Transactional
	@SQLDelete(sql = "delete from StaalgrConf where agentCode = :agentCode")
	void deleteByAgentCode(@Param("agentCode")String agentCode);
	@Query("from StaalgrConf where agentCode = :agentCode")
	List<StaalgrConf> getByAgentCode(@Param("agentCode")String agentCode);



}
