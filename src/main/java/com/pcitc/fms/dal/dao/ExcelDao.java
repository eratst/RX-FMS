package com.pcitc.fms.dal.dao;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "FACTORY")
@SequenceGenerator(name = "mseq", sequenceName = "FACTORY_SEQ")
public interface ExcelDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Factory>, JpaRepository<com.pcitc.fms.dal.pojo.Factory,Long> {

}
