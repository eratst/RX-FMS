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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.pojo.NodeDictionary;
import com.pcitc.fms.dal.pojo.Tank;
@Service
public interface NodeDictionaryDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.NodeDictionary>, JpaRepository<com.pcitc.fms.dal.pojo.NodeDictionary,Integer>  {

	@Query("from NodeDictionary where nodeCode = :code")
	NodeDictionary getNodeDictionaryByNodeCode(@Param("code") String code);
	
	@Query("from NodeDictionary where nodeCode = :code")
	List<com.pcitc.fms.dal.pojo.NodeDictionary> getNodeDictionaryByNodeCodes(@Param("code") String code);

	@Query("from NodeDictionary where areaCode = :areaCode and code = :code")
	List<NodeDictionary> getNodeDictionaryByNodeCode(@Param("areaCode") String areaCode,@Param("code") String code);

	@Modifying
	@Transactional
	@Query("update NodeDictionary set name=:name,maintainTime=sysdate, editor=:editor,enabled=:enabled,type=:type,des =:des where code = :code and areaCode = :areaCode")
	void updateNodeDictionaryTable(@Param("name") String name,
			@Param("code") String code,
			@Param("areaCode") String areaCode,
			@Param("editor") String editor,
			@Param("enabled") Integer enabled,
			@Param("type") String type,
			@Param("des") String des);

	@Transactional
	@SQLDelete(sql = "delete from NodeDictionary where nodeCode  = :code")
	void deleteNodeDictionaryByNodeCode(@Param("code") String code);
	
	@Transactional
	MyPageImpl getNodeDictionaryTablesByModel(
			com.pcitc.fms.service.model.NodeDictionary nodeDictionaryTableModel, Pageable pageable);

	@Query("from NodeDictionary where  nodeCode in :code")
	List<NodeDictionary> getNodeDictionaryTablesByNodeCodes(@Param("code")List<String> relSourceCodeList);

	@Query("from NodeDictionary where  nodeName = :nodeName and nodeAlias = :nodeAlias")
	NodeDictionary getNodeDictionaryByNodeName(@Param("nodeName")String nodeName,@Param("nodeAlias") String nodeAlias);
	@Query("from NodeDictionary where  areaId = :id ")
	List<NodeDictionary> getNodeDictionaryByAreaId(@Param("id")Integer areaDictionaryId);
	
	

}
