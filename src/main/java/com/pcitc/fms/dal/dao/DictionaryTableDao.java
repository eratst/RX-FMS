package com.pcitc.fms.dal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.DictionaryTable;

public interface DictionaryTableDao extends	JpaRepository<DictionaryTable, Integer> {
	@Transactional
	public Page<DictionaryTable> findDictionaryTables(com.pcitc.fms.service.model.DictionaryTable findDictionaryTableModel, Pageable pageable);

//	@Modifying
//	@Transactional
//	@Query("update DictionaryTable " 
//			+ "set " 
//			+ "entityTableName = :entityTableName,"
//			+ "name = :name," 
//			+ "entityId = :entityId," 
//			+ "editor = :editor ,"
//			+ "maintainTime=sysdate " 
//			+ "where " 
//			+ "dictionaryTableId = :dictionaryTableId")
//	public void updateDictionaryTable(
//			@Param("dictionaryTableId") Integer dictionaryTableId,
//			@Param("entityId") Integer entityId, 
//			@Param("entityTableName") String entityTableName,
//			@Param("name") String name, 
//			@Param("editor") String editor);

	@Transactional
	@SQLDelete(sql = "delete from DictionaryTable where dictionaryTableId = :dictionaryTableId")
	void deleteByDictionaryTableId(@Param("dictionaryTableId") Integer dictionaryTableId);

	@Query(" select table from DictionaryTable table where table.dictionaryTableId = :dictionaryTableId")
	public List<DictionaryTable> getDictionaryTableById(@Param("dictionaryTableId") Integer dictionaryTableId);
}
