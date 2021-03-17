package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@Service
public interface WarehousTechnicDao extends JpaRepository<com.pcitc.fms.dal.pojo.WarehousTechnic,Integer> {

	public List<com.pcitc.fms.dal.pojo.WarehousTechnic> findByTechnicCode(String code);

}
