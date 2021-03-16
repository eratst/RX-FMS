package com.pcitc.fms.dal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pcitc.fms.dal.pojo.OrgUnitContrast;

public interface OrgUnitContrastDao extends JpaSpecificationExecutor<OrgUnitContrast>,JpaRepository<OrgUnitContrast, Long>{
	
	@Query("select new OrgUnitContrast(Ouc.orgUnitContrastId,Ouc.oumOrgUnitId,Ouc.fmOrgId,Ouc.crtUserCode,Ouc.crtUserName,Ouc.crtDate,Ouc.mntUserCode,Ouc.mntUserName,Ouc.mntDate,Ouc.des,Ouc.sortNum,Ouc.version,Ouc.inUse) "
			+ "from OrgUnitContrast Ouc where Ouc.oumOrgUnitId =:oumOrgUnitId ")
	public OrgUnitContrast findByOrgUnitContrastId(@Param ("oumOrgUnitId") Long oumOrgUnitId);

}
