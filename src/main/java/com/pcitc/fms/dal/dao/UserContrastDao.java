package com.pcitc.fms.dal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pcitc.fms.dal.pojo.UserContrast;

public interface UserContrastDao extends JpaSpecificationExecutor<UserContrast>, JpaRepository<UserContrast,Integer>{

}
