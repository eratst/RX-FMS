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
import com.pcitc.fms.dal.pojo.Office;

 /**
 * Title: OfficeDao
* Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
public interface OfficeDao extends JpaSpecificationExecutor<Office>,JpaRepository<Office, Integer> {
	
	@Query(AreaNodeBasicSql.offices+" order by orgId")
	List<Office> getOffices();
	
	@Query(AreaNodeBasicSql.offices+" and t.orgCode = :orgCode")
	Office getOfficeByOrgCode(@Param("orgCode")String orgCode);
	
	
	@Transactional 
	@SQLDelete(sql = "delete from Office where orgCode = :orgCode")
	void deleteByOrgCode(@Param("orgCode")String  orgCode);
	
//	@Modifying
//	@Transactional
//	@Query("update Office set code = :code,name = :name,shortName = :shortName,facilityType = :facilityType,businessType = :businessType,technic = :technic,technicRoute=:technicRoute,plant=:plant,enabled = :enabled,editor = :editor where orgId = :orgId")
//	void updateOffice(@Param("orgId")Integer orgId, 
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
	public MyPageImpl findOffices(com.pcitc.fms.service.model.Office OfficeModel, Pageable pageable);
	
	
	@Query(AreaNodeBasicSql.offices+" and t.orgCode = :orgCode ")
	Office findOfficeByorgCode(@Param("orgCode")String orgCode);
	
	List<Office> findOfficeByorgCodeIn(List<String> orgCodes);
	
	void deleteByorgCode(String orgcode);
	
	
	


}
