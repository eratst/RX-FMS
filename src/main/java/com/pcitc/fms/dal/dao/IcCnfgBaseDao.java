package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.IcCnfgBase;

public interface IcCnfgBaseDao extends JpaRepository<IcCnfgBase, Integer>, JpaSpecificationExecutor<IcCnfgBase>{
	@Transactional
	public List<IcCnfgBase> findIcCnfgBases(String nodeCode, List<String> codeList);
	
	
	@Query("select new CnfgTank(cnfgBase.bCnfgClassId,cnfgClass.cnfgClassCode,cnfgClass.sortNum"
				+ ",cnfgClass.tankType,cnfgBase.bClassParaId,cnfgClassPara.cnfgParaValue,cnfgClassPara.isUseFormula,"
				+ "cnfgBase.bFormula) "
				+ "from IcCnfgBase cnfgBase,CnfgClass cnfgClass,CnfgClassPara cnfgClassPara where"
				+ " cnfgBase.bCnfgClassId=cnfgClass.cnfgClassId and cnfgBase.bClassParaId=cnfgClassPara.classParaId "
				+ "and  cnfgBase.bAreaId = :tankAreaId and cnfgClass.tankType in :tankTypeIds and cnfgBase.bCnfgClassId not in :cnfgClassIds")
	public List<com.pcitc.fms.dal.pojo.CnfgTank> findCnfgBase(
			@Param("tankAreaId") Long tankAreaId, @Param("tankTypeIds") List<String> tankTypeIdList, @Param("cnfgClassIds") List<Long> cnfgClassIdList);

	@Query("select new CnfgTank(cnfgBase.bCnfgClassId,cnfgClass.cnfgClassCode,cnfgClass.sortNum"
			+ ",cnfgClass.tankType,cnfgBase.bClassParaId,cnfgClassPara.cnfgParaValue,cnfgClassPara.isUseFormula,"
			+ "cnfgBase.bFormula) "
			+ "from IcCnfgBase cnfgBase,CnfgClass cnfgClass,CnfgClassPara cnfgClassPara where"
			+ " cnfgBase.bCnfgClassId=cnfgClass.cnfgClassId and cnfgBase.bClassParaId=cnfgClassPara.classParaId "
			+ "and  cnfgBase.bAreaId = :tankAreaId and cnfgClass.tankType in :tankTypeIds")
	public List<com.pcitc.fms.dal.pojo.CnfgTank> findCnfgBase1(
			@Param("tankAreaId") Long tankAreaId, @Param("tankTypeIds") List<String> tankTypeIdList);


	
	
	@Query("select new CnfgTank(cnfgBase.bCnfgClassId,cnfgClass.cnfgClassCode,cnfgClass.sortNum"
			+ ",cnfgClass.tankType,cnfgBase.bClassParaId,cnfgClassPara.cnfgParaValue,cnfgClassPara.isUseFormula,"
			+ "cnfgBase.bFormula) "
			+ "from IcCnfgBase cnfgBase,CnfgClass cnfgClass,CnfgClassPara cnfgClassPara,AreaDictionary a ,TPmOrg org where "
			+ " cnfgBase.bCnfgClassId=cnfgClass.cnfgClassId and cnfgBase.bClassParaId=cnfgClassPara.classParaId and cnfgBase.bAreaId=a.areaDictionaryId and a.factoryId=org.orgId "
			+ "and  cnfgBase.bAreaId = :tankAreaId and cnfgClass.tankType in :tankTypeIds and cnfgBase.bCnfgClassId not in :cnfgClassIds and org.orgCode in :orgCodes ")
	public List<com.pcitc.fms.dal.pojo.CnfgTank> findCnfgBaseForRent(@Param("tankAreaId") Long tankAreaId, @Param("tankTypeIds") List<String> tankTypeIdList,
			@Param("cnfgClassIds") List<Long> cnfgClassIdList, @Param("orgCodes") List<String> orgCodes);

	
	
	@Query("select new CnfgTank(cnfgBase.bCnfgClassId,cnfgClass.cnfgClassCode,cnfgClass.sortNum"
			+ ",cnfgClass.tankType,cnfgBase.bClassParaId,cnfgClassPara.cnfgParaValue,cnfgClassPara.isUseFormula,"
			+ "cnfgBase.bFormula) "
			+ "from IcCnfgBase cnfgBase,CnfgClass cnfgClass,CnfgClassPara cnfgClassPara,AreaDictionary a ,TPmOrg org where"
			+ " cnfgBase.bCnfgClassId=cnfgClass.cnfgClassId and cnfgBase.bClassParaId=cnfgClassPara.classParaId and cnfgBase.bAreaId=a.areaDictionaryId and a.factoryId=org.orgId "
			+ "and  cnfgBase.bAreaId = :tankAreaId and cnfgClass.tankType in :tankTypeIds and org.orgCode in :orgCodes ")
	public List<com.pcitc.fms.dal.pojo.CnfgTank> findCnfgBaseForRent1(@Param("tankAreaId") Long tankAreaId, @Param("tankTypeIds") List<String> tankTypeIdList,
			 @Param("orgCodes") List<String> orgCodes);

     
	


}
