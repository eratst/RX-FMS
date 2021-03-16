package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.ManagementMtrl;

public interface ManagementMtrlDao extends JpaRepository<ManagementMtrl, Integer>, JpaSpecificationExecutor<ManagementMtrl> {
	@Transactional
	public Page<ManagementMtrl> findManagementMtrls(com.pcitc.fms.service.model.ManagementMtrl managementMtrl, Pageable pageable);

//	@Query("select new ManagementMtrl(mm.mtrlId, mm.mtrlCode, mm.mtrlName, mm.mtrlAlias, mm.upperMtrlCode, mm.mtrlTypeId, mt.mtrlTypeCode, mt.mtrlTypeName, mm.vcfTypeId"
//			+ ", vt.vcfTypeCode, vt.vcfTypeName, mm.dimensionId, ds.dimensionCode, ds.dimensionName, ds.dimensionAlias, mm.dec, mm.sortNum) from ManagementMtrl mm, MtrlType mt, VcfType vt, Dimension ds where mm.mtrlTypeId = mt.mtrlTypeId and mm.vcfTypeId = vt.vcfTypeId and mm.dimensionId = ds.dimensionId start with mm.mtrlCode=:mtrlCode connect by prior mm.upperMtrlCode=mm.mtrlCode ")
//	
//	@Query(SELECT )
//	public List<ManagementMtrl> getManagementMtrls(@Param("mtrlCode") String mtrlCode);

	@Query(value = "select management0_.MTRL_ID,management0_.MTRL_CODE,management0_.MTRL_NAME,management0_.MTRL_ALIAS,"
			+ "management0_.UPPER_MTRL_CODE,management0_.MTRL_TYPE_ID,mtrltype1_.MTRL_TYPE_CODE,mtrltype1_.MTRL_TYPE_NAME,"
			+ "management0_.VCF_TYPE,vcftype2_.VCF_TYPE_CODE,vcftype2_.VCF_TYPE_NAME,management0_.DIMENSION_ID,  "
			+ "dimension3_.DIMENSION_CODE,  dimension3_.DIMENSION_NAME,  dimension3_.DIMENSION_ALIAS, management0_.DEC,"
			+ "management0_.SORT_NUM from T_PM_MTRL management0_ cross join T_PM_MTRL_TYPE mtrltype1_ cross join "
			+ "T_PM_VCFTYPE vcftype2_ cross join T_PM_DIMENSION dimension3_ where management0_.MTRL_TYPE_ID = mtrltype1_.MTRL_TYPE_ID"
			+ " and management0_.VCF_TYPE = vcftype2_.VCF_TYPE_ID and management0_.DIMENSION_ID = dimension3_.DIMENSION_ID "
			+ " and 1 = 1 start with management0_.MTRL_CODE=:mtrlCode CONNECT BY PRIOR management0_.UPPER_MTRL_CODE=management0_.MTRL_CODE ",
			nativeQuery=true)
	public List<Object> getManagementMtrls(@Param("mtrlCode") String mtrlCode);
}
