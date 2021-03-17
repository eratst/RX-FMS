package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.pojo.SignboardType;
@Service
public interface SignboardTypeDao extends JpaRepository<com.pcitc.fms.dal.pojo.SignboardType,Integer> {

	public List<com.pcitc.fms.dal.pojo.SignboardType> findBySignboardTypeCode(String code);

	public SignboardType findBySignboardTypeId(Integer signboardTypeId);

}
