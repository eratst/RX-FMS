package com.pcitc.fms.dal.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface DbPrimaryIdDao extends JpaSpecificationExecutor<BigDecimal>,JpaRepository<com.pcitc.fms.dal.pojo.AreaDictionary, BigDecimal>{
	
	@Transactional
	public List<BigDecimal> getSeqId(int count,String Tablename);

}
