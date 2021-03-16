package com.pcitc.fms.dal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.PostionMeta;

public interface PostionDao extends JpaRepository<com.pcitc.fms.dal.pojo.PostionMeta, Integer> {
	public List<com.pcitc.fms.dal.pojo.PostionMeta> findPostions(com.pcitc.fms.service.model.PostionMeta postionModel);

	@Query("select table from PostionMeta table where table.postionId = :postionId")
	List<com.pcitc.fms.dal.pojo.PostionMeta> getPostionById(@Param("postionId") Integer postionId);
	
	@Transactional
	@SQLDelete(sql = "delete from PostionMeta where postionId = :postionId")
	void deleteByPostionId(@Param("postionId") Integer postionId);
	
	public List<com.pcitc.fms.dal.pojo.PostionMeta> findByCode(String code);

	@Query("select table from PostionMeta table where table.code = :code")
	public PostionMeta getPostionByCode(@Param("code") String code);
}
