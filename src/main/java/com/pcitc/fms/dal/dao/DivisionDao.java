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
import com.pcitc.fms.dal.pojo.Division;

 /**
 * Title: DivisionDao
* Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
public interface DivisionDao extends JpaSpecificationExecutor<Division>,JpaRepository<Division, Integer> {
	
	@Query(AreaNodeBasicSql.divisions+" order by orgId")
	List<Division> getDivisions();
	
	@Query(AreaNodeBasicSql.divisions+" and t.orgCode = :orgCode")
	Division getDivisionByOrgCode(@Param("orgCode")String orgCode);
	
	
	@Transactional 
	@SQLDelete(sql = "delete from Division where orgCode = :orgCode")
	void deleteByOrgCode(@Param("orgCode")String  orgCode);
	
//	@Modifying
//	@Transactional
//	@Query("update Division set code = :code,name = :name,shortName = :shortName,facilityType = :facilityType,businessType = :businessType,technic = :technic,technicRoute=:technicRoute,plant=:plant,enabled = :enabled,editor = :editor where orgId = :orgId")
//	void updateDivision(@Param("orgId")Integer orgId, 
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
	public MyPageImpl findDivisions(com.pcitc.fms.service.model.Division DivisionModel, Pageable pageable);
	
	
	@Query(AreaNodeBasicSql.divisions+" and t.orgCode = :orgCode ")
	Division findDivisionByorgCode(@Param("orgCode")String orgCode);
	
	List<Division> findDivisionByorgCodeIn(List<String> orgCodes);
	
	void deleteByorgCode(String orgcode);
	
	
	


}
