package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@Service
public interface PipenetTechnicDao extends JpaRepository<com.pcitc.fms.dal.pojo.PipenetTechnic,Integer> {

	public List<com.pcitc.fms.dal.pojo.PipenetTechnic> findByTechnicCode(String code);

}
