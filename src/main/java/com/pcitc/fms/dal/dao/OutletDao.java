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

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Outlet;


@Service
public interface OutletDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Outlet>, JpaRepository<com.pcitc.fms.dal.pojo.Outlet,Integer> {

//	public List<com.pcitc.fms.dal.pojo.Outlet> findOutlets(com.pcitc.fms.service.model.Outlet findOutletModel);
//
//	@Modifying
//	@Transactional
//	@Query("update Outlet set code = :code,name = :name,shortName = :shortName,emissions = :emissions,plateType = :plateType,location = :location,enabled = :enabled,editor = :editor,creator = :creator,maintainTime=sysdate where outletId = :outletId")
//	public void updateOutlet(
//			@Param("outletId")Integer outletId,
//			@Param("code") String code,
//			@Param("name") String name, 
//			@Param("shortName")String shortName,
//			@Param("emissions") Integer emissions,
//			@Param("plateType")Integer plateType,
//			@Param("location") String location,
//			@Param("enabled") Integer enabled,
//			@Param("editor") String editor
//			);
//
//	
	@Transactional
	@SQLDelete(sql = "delete from Outlet where nodeCode = :code")
	void deleteByNodeCode(@Param("code")String code);
//
//	@Query("select t from Outlet t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'outlets' and t.code=:code")
//	public List<com.pcitc.fms.dal.pojo.Outlet> findLinkOutlets(
//			@Param("parentCode")String parentCode, 
//			@Param("parentType")String parentType, 
//			@Param("code")String code);
//
//	@Query("select t from Outlet t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'outlets'")
//	public List<com.pcitc.fms.dal.pojo.Outlet> findLinkOutlets(
//			@Param("parentCode")String parentCode,
//			@Param("parentType")String parentType);
	//注意类型转换
	public MyPageImpl findPageOutlets(com.pcitc.fms.service.model.Outlet outletModel, Pageable pageable);
//
//	@Query(AreaNodeBasicSql.Outlet+" and a.nodeCode= :code ")
//	public List<com.pcitc.fms.dal.pojo.Outlet> seek(@Param("code")String code);
//
//	public List<com.pcitc.fms.dal.pojo.Outlet> findByParentTypeAndParentCode(String string, String plantCode);
//
//	/**   
//	 * @Title: findByCode   
//	 * @date 2017年10月10日      
//	 * @return: Outlet
//	 */
//	public Outlet findByCode(String code);
}
