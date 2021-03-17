package com.pcitc.fms.dal.dao;

import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.TPmAreaNodeType;
import java.util.List;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
* Title: AreaNodeTypeDao
* @author zhenqiang.zhao
* @version 1.0
*/
public interface AreaNodeTypeDao extends JpaSpecificationExecutor<TPmAreaNodeType>,JpaRepository<TPmAreaNodeType, Integer> {

   @Query(AreaNodeBasicSql.areaNodeTypes+" and area.areaCode = :areaCode")
   TPmAreaNodeType getTPmAreaNodeTypeByAreaCode(@Param("areaCode") String areaCode);

   @Transactional
   public Page<TPmAreaNodeType> findAreaNodeTypes(com.pcitc.fms.service.model.TPmAreaNodeType TPmAreaNodeTypeModel, Pageable pageable);




}
