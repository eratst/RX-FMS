package com.pcitc.fms.dal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TPmOrg;

public interface TPmOrgDao extends JpaSpecificationExecutor<TPmOrg>, JpaRepository<TPmOrg, Integer> {

	// 标准组织机构树（向下查询）
	@Query(value = "select t.org_id, t.upper_org_id,"
		   +" (select innert.org_code from T_PM_ORG innert where innert.org_id=t.upper_org_id) as upper_org_code, "
		   +" (select innert.org_name from T_PM_ORG innert where innert.org_id=t.upper_org_id) as upper_org_name, "
		   +" (select innert.org_alias from T_PM_ORG innert where innert.org_id=t.upper_org_id) as upper_org_alias, "
		   +" t.org_code,  t.org_name, t.org_alias, t.orgtype_id,ty.orgtype_code,ty.orgtype_name "
		   +" from T_PM_ORG t, T_PM_ORGTYPE ty  "
		   +" where t.orgtype_id = ty.orgtype_id and FIND_IN_SET(t.ORG_ID,getOrgChildById(:orgId))", nativeQuery = true)
	public List<Object> getAllTrees(@Param("orgId") Long orgId);
	
	// 标准组织机构树（向下查询）
	@Query(value = "  select t.org_id, t.upper_org_id,"
			+" (select innert.org_code from T_PM_ORG innert where innert.org_id=t.upper_org_id) as upper_org_code, "
			+" (select innert.org_name from T_PM_ORG innert where innert.org_id=t.upper_org_id) as upper_org_name, "
			+" (select innert.org_alias from T_PM_ORG innert where innert.org_id=t.upper_org_id) as upper_org_alias, "
			+" t.org_code, t.org_name, t.org_alias, t.orgtype_id, ty.orgtype_code,"
			+" ty.orgtype_name from T_PM_ORG t, T_PM_ORGTYPE ty where t.orgtype_id = ty.orgtype_id "
			+" and FIND_IN_SET(t.ORG_ID,getOrgParentById(:orgId))", nativeQuery = true)
	public List<Object> getBranchTrees(@Param("orgId") Long orgId);

	@Transactional
	@SQLDelete(sql = "delete from TPmOrg where orgCode = :orgCode")
	void deleteByOrgCode(@Param("orgCode") String orgCode);

	@Modifying
	@Transactional
	@Query("update TPmOrg set code = :code,name = :name,shortName = :shortName,facilityType = :facilityType,businessType = :businessType,technic = :technic,technicRoute=:technicRoute,plant=:plant,enabled = :enabled,editor = :editor where orgId = :orgId")
	void updateTPmOrg(@Param("orgId") Integer orgId, @Param("code") String code, @Param("name") String name,
			@Param("shortName") String shortName, @Param("facilityType") String facilityType,
			@Param("businessType") String businessType, @Param("technic") String technic,
			@Param("technicRoute") String technicRoute, @Param("plant") String plant, @Param("enabled") Integer enabled,
			@Param("editor") String editor);

	TPmOrg findByorgCode(String code);

	@Query("from TPmOrg where orgCode = :orgCode")
	TPmOrg getTPmOrg(@Param("orgCode") String orgCode);

	@Query("from TPmOrg where orgId = :orgId")
	TPmOrg getTPmOrgByOrgId(@Param("orgId") Long orgId);

	@Query("from TPmOrg where orgCode in :orgCodes")
	List<TPmOrg> getTPmOrgTablesByorgCodes(@Param("orgCodes") List<String> relSourceCodeList);

}
