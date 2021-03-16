package com.pcitc.fms.dal.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pcitc.fms.dal.pojo.Temppreden;
/**
 * 
 * @author xin.kou
 *
 */
public interface TemppredenDao extends JpaSpecificationExecutor<Temppreden>, JpaRepository<Temppreden, Integer> {

}
