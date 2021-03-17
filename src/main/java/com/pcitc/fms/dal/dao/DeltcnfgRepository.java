package com.pcitc.fms.dal.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.Deltcnfg;


public interface DeltcnfgRepository extends JpaRepository<Deltcnfg, Long>, JpaSpecificationExecutor<Deltcnfg>{

	public MyPageImpl findAllDeltCnfgs(com.pcitc.fms.service.model.Deltcnfg deltcnfgModel,
			Pageable pageable);


}
