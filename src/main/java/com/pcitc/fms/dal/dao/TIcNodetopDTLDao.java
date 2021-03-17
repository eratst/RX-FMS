package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TIcNodetopDTL;
import java.util.List;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
* Title: NodetopDTLDao
* Description: TODO task mark zhenqiang.zhao
* @author zhenqiang.zhao
* @date 2017年11月21日
* @version 1.0
*/
public interface TIcNodetopDTLDao extends JpaSpecificationExecutor<TIcNodetopDTL>,JpaRepository<TIcNodetopDTL, Integer> {

//   @Query(AreaNodeBasicSql.nodetopDTLs+" order by dtl.sNodeId")
//   List<TIcNodetopDTL> getNodetopDTLs();
//
//   @Query(AreaNodeBasicSql.nodetopDTLs+" and dtl.orgCode = :sNodeId")
//   TIcNodetopDTL getNodetopDTLByOrgCode(@Param("orgCode") String orgCode);
//
//   @Query(AreaNodeBasicSql.nodetopDTLs+" and dtl.parentOrgId = :orgId")
//   List<TIcNodetopDTL> getNodetopDTLByOrgCodeAndChildren(@Param("orgId") Integer orgId);
//
//   @Query(AreaNodeBasicSql.nodetopDTLs+" and dtl.orgId in :orgIds")
//   List<TIcNodetopDTL> getNodetopDTLByOrgCodeAndAncestors(@Param("orgIds") List<Integer> orgIds);
//
//   @Query(AreaNodeBasicSql.nodetopDTLs+" and dtl.orgId in :orgIds")
//   List<TIcNodetopDTL> getNodetopDTLByOrgIds(@Param("orgIds") List<Integer> orgIds);
//
//   @Query(AreaNodeBasicSql.nodetopDTLs+" and dtl.orgId = :orgId")
//   TIcNodetopDTL getNodetopDTLByOrgId(@Param("orgId") Integer orgId);
//
//
//   @Transactional
//   @SQLDelete(sql = "delete from TIcNodetopDTL  dtl where dtl.sNodeCode = :sNodeCode")
//   void deleteByOrgCode(@Param("orgCode") String orgCode);

//	@Modifying
//	@Transactional
//	@Query("update TIcNodetopDTL set code = :code,name = :name,shortName = :shortName,facilityType = :facilityType,businessType = :businessType,technic = :technic,technicRoute=:technicRoute,plant=:plant,enabled = :enabled,editor = :editor where orgId = :orgId")
//	void updateNodetopDTL(@Param("orgId")Integer orgId,
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
   public Page<TIcNodetopDTL> findNodetopDTLs(com.pcitc.fms.service.model.TIcNodetopDTL NodetopDTLModel,
       Pageable pageable);

   public com.pcitc.fms.dal.pojo.TIcNodetopDTL findByDataCode(String tpmNodetopDTLCode);

   public List<TIcNodetopDTL> findByDataCode(String topCode, String dataCode);
   
   @Query("select t from TIcNodetopDTL t where t.topId =:topId and t.dataCode =:dataCode")
   public TIcNodetopDTL chage(@Param("topId")Integer topId, @Param("dataCode")String dataCode);


//   @Query(AreaNodeBasicSql.nodetopDTLs+" and dtl.orgCode = :orgCode ")
//   TIcNodetopDTL findNodetopDTLByorgCode(@Param("orgCode") String orgCode);
//
//   List<TIcNodetopDTL> findNodetopDTLByorgCodeIn(List<String> orgCodes);
//
//
//   void deleteByorgCode(String orgcode);



}
