package com.pcitc.fms.dal.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.OpenindexVO;
@Service
public interface OpenindexVODao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.OpenindexVO>, JpaRepository<com.pcitc.fms.dal.pojo.OpenindexVO,Integer>  {

	@Query(AreaNodeBasicSql.openindexVO+" and p.plantCode = :plantCode")
	public List<OpenindexVO> findOpenindexs(@Param("plantCode") String unitCode);

}
