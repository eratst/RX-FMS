package com.pcitc.fms.dal.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewTankDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.NewTank>, JpaRepository<com.pcitc.fms.dal.pojo.NewTank,Integer>  {

}
