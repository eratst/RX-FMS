package com.pcitc.fms.dal.dao;

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
import com.pcitc.fms.dal.pojo.Team;

 /**
 * Title: TeamDao
* Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
public interface TeamDao extends JpaSpecificationExecutor<Team>,JpaRepository<Team, Integer> {
	
	@Query(AreaNodeBasicSql.teams+" order by orgId")
	List<Team> getTeams();
	
	@Query(AreaNodeBasicSql.teams+" and t.orgCode = :orgCode")
	Team getTeamByOrgCode(@Param("orgCode")String orgCode);
	
	
	@Transactional 
	@SQLDelete(sql = "delete from Team where orgCode = :orgCode")
	void deleteByOrgCode(@Param("orgCode")String  orgCode);
	
//	@Modifying
//	@Transactional
//	@Query("update Team set code = :code,name = :name,shortName = :shortName,facilityType = :facilityType,businessType = :businessType,technic = :technic,technicRoute=:technicRoute,plant=:plant,enabled = :enabled,editor = :editor where orgId = :orgId")
//	void updateTeam(@Param("orgId")Integer orgId, 
//		@Param("code") String code, 
//		@Param("name") String name, 
//		@Param("shortName") String shortName, 
//		@Param("facilityType") String facilityType,
//		@Param("businessType") String businessType, 
//		@Param("technic") String technic, 
//		@Param("technicRoute") String technicRoute, 
//		@Param("plant") String plant, 
//		@Param("enabled") Integer enabled,
//		@Param("editor") String editor);
	
	@Transactional
	public MyPageImpl findTeams(com.pcitc.fms.service.model.Team TeamModel, Pageable pageable);
	
	
	@Query(AreaNodeBasicSql.teams+" and t.orgCode = :orgCode ")
	Team findTeamByorgCode(@Param("orgCode")String orgCode);
	
	List<Team> findTeamByorgCodeIn(List<String> orgCodes);
	
	void deleteByorgCode(String orgcode);
	
	
	


}
