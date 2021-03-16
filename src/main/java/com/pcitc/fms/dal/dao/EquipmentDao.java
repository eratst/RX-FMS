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
import com.pcitc.fms.dal.pojo.Equipment;


@Service
public interface EquipmentDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Equipment>, JpaRepository<com.pcitc.fms.dal.pojo.Equipment,Integer> {
	/**
	 * 查询单个设备
	 * @param factoryId
	 * @return
	 */
	@Query(AreaNodeBasicSql.Equipment+" and a.nodeId = :equipmentId")
	List<Equipment> getEquipmentByNodeID(@Param("equipmentId")Integer equipmentId);

	@Query(AreaNodeBasicSql.Equipment+" and a.nodeCode = :code")
	List<Equipment> getEquipmentByNodeCode(@Param("code")String code);
	
	public MyPageImpl getAll(com.pcitc.fms.service.model.Equipment equipmentModel, Pageable pageable);
	
	@Transactional
	@SQLDelete(sql = "delete from Equipment where nodeCode = :code")
	public void deleteByNodeCode(@Param("code")String code);
	
//	@Modifying
//	@Transactional
//	@Query("update Equipment set name = :name,shortName = :shortName,deviceType = :deviceType,parentType = :parentType,"
//			+ "technic = :technic,enabled = :enabled,editor = :editor, maintainTime=sysdate,des = :des where code = :code")
//	public void updateEquipment(
//			@Param("code") String code, 
//			@Param("name")String name,
//			@Param("shortName")String shortName,
//			@Param("deviceType")String deviceType,
//			@Param("parentType")String parentType,
//			@Param("technic")Integer technic,
//			@Param("enabled")Integer enabled, 
//			@Param("editor")String editor,
//			@Param("des")String des
//			);
//	
	
//	@Query("select t from Equipment t where t.nodeId = :equipmentId and t.parentId = :parentId")
//	public List<com.pcitc.fms.dal.pojo.Equipment> seek(
//			@Param("equipmentId")Integer equipmentId,
//			@Param("parentId")Integer parentId);
	
	
//	@Query("select t from Equipment t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'equipments' and t.code=:code")
//	public List<com.pcitc.fms.dal.pojo.Equipment> findLinkEquipments(@Param("parentCode")String parentCode,@Param("parentType") String parentType,@Param("code")
//			String code);
	
//	@Query("select t from Equipment t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'equipments'")
//	public List<com.pcitc.fms.dal.pojo.Equipment> findLinkEquipments(@Param("parentCode")String parentCode, @Param("parentType")String parentType);

	

//	public List<com.pcitc.fms.dal.pojo.Equipment> findByParentTypeAndParentCode(String string, String plantCode);
	
//	@Query("from Equipment where parentType = :parentType and parentCode = :parentCode and code = :code")
//	public List<com.pcitc.fms.dal.pojo.Equipment> getEquipmentByNodeCode(@Param("parentCode")String parentCode,@Param("code") String code,@Param("parentType") String parentType);
	

	
}
