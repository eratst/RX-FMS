package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.Material;
@Service
public interface MaterialDao extends JpaSpecificationExecutor<Material>, JpaRepository<Material,Integer> {
	
	String sql = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
			+ "mu.mtrlName,mu.mtrlAlias,"
			+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
			+" a.vcfTypeId,vcf.vcfTypeCode, vcf.vcfTypeName,"
			+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
			+" a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.inUse,a.tankIdt)"
			+" from Material a,Material mu,MtrlType m,VcfType vcf, Dimension d where mu.mtrlCode = a.upperMtrlCode and "
			+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
			+" and a.dimensionId = d.dimensionId";
	@Transactional
	public List<Material> getMtrlByCodeWithChildsTest(List<String> mtrlCodes,com.pcitc.fms.service.model.Material material,Pageable pageable);

	
	
	String sqltest = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
			+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
			+" a.vcfTypeId,vcf.vcfTypeCode, vcf.vcfTypeName,"
			+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
			+" a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.inUse,a.tankIdt)"
			+" from Material a,MtrlType m,VcfType vcf, Dimension d where"
			+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
			+" and a.dimensionId = d.dimensionId";
	
//	public List<Material> getMtrlByCode(@Param("mtrlCode") String mtrlCode);
	public List<Material> getMtrlByCodestest(@Param("mtrlCodes") List<String> mtrlCodes,com.pcitc.fms.service.model.Material findMaterialModel,Pageable pageable);
	
	String sqlMtrlType = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
			+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
			+" a.vcfTypeId,vcf.vcfTypeCode, vcf.vcfTypeName,"
			+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
			+" a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.inUse,a.tankIdt)"
			+" from Material a,MtrlType m,VcfType vcf, Dimension d where"
			+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
			+" and a.dimensionId = d.dimensionId ";
	@Query(sqlMtrlType + " and m.mtrlTypeCode = :mtrlTypeCode")
	public List<Material> getMtrlByMtrlTypeCode(@Param("mtrlTypeCode") String mtrlTypeCode);
	
	String sqlMtrlCode = "select new Material(a.mtrlId,a.mtrlCode,a.mtrlName,a.mtrlAlias,a.upperMtrlCode,"
			+" a.mtrlTypeId,m.mtrlTypeCode, m.mtrlTypeName, "
			+" a.vcfTypeId,vcf.vcfTypeCode, vcf.vcfTypeName,"
			+" a.dimensionId, d.dimensionCode, d.dimensionName,d.dimensionAlias,"
			+" a.crtUserCode,a.crtUserName,a.crtDate,a.mntUserCode,a.mntUserName,a.mntDate,a.dec,a.sortNum,a.inUse,a.tankIdt)"
			+" from Material a,MtrlType m,VcfType vcf, Dimension d where"
			+" a.mtrlTypeId = m.mtrlTypeId and a.vcfTypeId = vcf.vcfTypeId"
			+" and a.dimensionId = d.dimensionId ";
	@Query(sqlMtrlCode + " and a.mtrlCode in :mtrlCodes")
	public List<Material> getMtrlByMtrlCodes(@Param("mtrlCodes") List<String> mtrlCodes);
	
	@Query(sqlMtrlCode + " and a.mtrlCode = :mtrlCode")
	public Material getMtrlByMtrlCode(@Param("mtrlCode") String mtrlCode);
	
	@Query(sqltest + " and a.mtrlCode = :mtrlCode")
	public List<Material> getMtrlByCodetest(@Param("mtrlCode") String mtrlCode);
	
	@Query( sqltest + " and a.mtrlCode in :mtrlCodes")
	public List<Material> getMtrlByCodes(@Param("mtrlCodes") List<String> mtrlCodes);
	
	@Query(sql + " and a.upperMtrlCode in :mtrlCodes")
	public List<Material> getMtrlByCodeWithChilds(@Param("mtrlCodes") List<String> mtrlCodes);
	
	@Transactional
	public List<Material> getMtrlByCodeWithAncestors(String upperMtrlCode,com.pcitc.fms.service.model.Material material);
	
	//暂时没用
//	@Query(value="select t.MTRL_CODE from T_PM_MTRL t where FIND_IN_SET(t.MTRL_CODE,getMtrlRootCodeByCode(:mtrlCode))",nativeQuery=true)
//	public List<String> getAllCodeToRoot(@Param("mtrlCode") String mtrlCode);
	

	public List<String> getAllMtrlCodeToRoot(@Param("mtrlCode") String mtrlCode);
	
	@Transactional
	public List<Material> getAllToRoot(List<String> mtrlCodes,com.pcitc.fms.service.model.Material material,Pageable pageable);
	
	
	
	//过滤非叶子节点
	@Query(value="select MTRL_CODE from T_PM_MTRL where FIND_IN_SET(MTRL_CODE,getLeafNodeListForMtrl(:mtrlCode));",nativeQuery=true)
	public List<String> getMtrlCodesLeaves(@Param("mtrlCode") String mtrlCode);
	
	
	public List<String> getAllCodeToChild(String mtrlCode,Integer levelValue);
	
	public MyPageImpl queryMaterials(com.pcitc.fms.service.model.Material findMaterialModel, Pageable pageable);
	
	public List<com.pcitc.fms.dal.pojo.Material> findByMtrlCode(String code);
//
//
	public List<com.pcitc.fms.dal.pojo.Material> findByUpperMtrlCode(String parentCode);
//
	public com.pcitc.fms.dal.pojo.Material getByMtrlCode(String code);

//	@Query(AreaNodeBasicSql.materials+" and a.nodeId in :nodeIds")
//	public List<com.pcitc.fms.dal.pojo.Material> findForRel(@Param("nodeIds")List<Integer> nodeId);


//	@Query(AreaNodeBasicSql.materials_sidelines+" and t.nodeCode in :nodeCodes")
//	public List<com.pcitc.fms.dal.pojo.Material> findForRelsidelines(@Param("nodeCodes")List<String> nodeCodes);
//
//	@Query(AreaNodeBasicSql.materials_sidelines)
//	public List<com.pcitc.fms.dal.pojo.Material> findForRelsidelines();

}
