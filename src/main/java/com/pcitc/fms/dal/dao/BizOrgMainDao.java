package com.pcitc.fms.dal.dao;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TPmBizOrgMain;
import com.pcitc.fms.dal.pojo.TPmOrg;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* Title: BizOrgMainDao
* Description: TODO task mark zhenqiang.zhao
* @author zhenqiang.zhao
* @date 2017年11月21日
* @version 1.0
*/
public interface BizOrgMainDao extends JpaSpecificationExecutor<TPmBizOrgMain>,JpaRepository<TPmBizOrgMain, Integer> {

   @Query("select orgMain from TPmBizOrgMain orgMain where 1 = 1")
   List<TPmBizOrgMain> getBizOrgMains();

   @Query("select orgMain from TPmBizOrgMain orgMain  where 1 = 1  and  orgMain.bizCode = :bizCode")
   TPmBizOrgMain getBizOrgMainByBizCode(@Param("bizCode") String bizCode);


   @Transactional
   @SQLDelete(sql = "delete from TPmBizOrgMain where bizCode = :bizCode")
   void deleteByBizCode(@Param("bizCode") String bizCode);

//	@Modifying
//	@Transactional
//	@Query("update TPmBizOrgMain set code = :code,name = :name,shortName = :shortName,facilityType = :facilityType,businessType = :businessType,technic = :technic,technicRoute=:technicRoute,plant=:plant,enabled = :enabled,editor = :editor where orgId = :orgId")
//	void updateBizOrgMain(@Param("orgId")Integer orgId,
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
   public MyPageImpl findBizOrgMains(com.pcitc.fms.service.model.TPmBizOrgMain TPmBizOrgMainModel, Pageable pageable);


   @Query("select orgMain from TPmBizOrgMain orgMain where 1 = 1 and orgMain.bizCode = :bizCode ")
   TPmBizOrgMain findTPmBizOrgMainBybizCode(@Param("bizCode") String bizCode);
   
   List<TPmBizOrgMain> findTPmBizOrgMainBybizCodeIn(List<String> bizCodes);

   void deleteBybizCode(String orgcode);


   TPmBizOrgMain findBybizCode(String bizCode);
}
