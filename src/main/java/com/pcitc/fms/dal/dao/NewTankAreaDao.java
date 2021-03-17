package com.pcitc.fms.dal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewTankAreaDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.NewTankArea>, JpaRepository<com.pcitc.fms.dal.pojo.NewTankArea,Integer>{

}
