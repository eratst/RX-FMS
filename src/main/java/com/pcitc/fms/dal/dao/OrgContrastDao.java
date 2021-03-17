package com.pcitc.fms.dal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pcitc.fms.dal.pojo.OrgContrast;

public interface OrgContrastDao extends JpaSpecificationExecutor<OrgContrast>,JpaRepository<OrgContrast, Long>{
	
}
