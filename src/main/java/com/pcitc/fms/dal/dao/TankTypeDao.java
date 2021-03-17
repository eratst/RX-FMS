package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@Service
public interface TankTypeDao extends JpaRepository<com.pcitc.fms.dal.pojo.TankType,Integer> {

	public List<com.pcitc.fms.dal.pojo.TankType> findByTankTypeCode(String code);

}
