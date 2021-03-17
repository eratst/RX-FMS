package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pcitc.fms.dal.pojo.Relations;

 /**
 * Title: RelationsDao
 * Description:关联关系
 * @author zhenqiang.zhao
 * @date 2017年6月16日
 * @version 1.0
 */
public interface RelationsDao extends JpaRepository<com.pcitc.fms.dal.pojo.Relations, Integer>,
		JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Relations> {

	/**   
	 * @Title: getConnections   
	 * @Description: TODO  
	 * @param relationsModel
	 * @return
	 * @date 2017年6月18日      
	 * @return: List<com.pcitc.fms.dal.pojo.Relations>
	 * @author zhenqiang.zhao      
	 */
	List<com.pcitc.fms.dal.pojo.Relations> getRelations(
			com.pcitc.fms.service.model.Relations relationsModel);

	/**   
	 * @Title: getPageRelations   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param relationsModel
	 * @param pageable
	 * @return
	 * @date 2017年7月27日      
	 * @return: Page<Relations>
	 * @author zhenqiang.zhao      
	 */
	Page<Relations> getPageRelations(com.pcitc.fms.service.model.Relations relationsModel, Pageable pageable);

	/**   
	 * @Title: getUniqueRelations   
	 * @Description: 添加数据时唯一性校验
	 * @param entityConverter
	 * @return
	 * @date 2017年8月10日      
	 * @return: Relations
	 * @author 赵振强      
	 */
	Relations getUniqueRelations(com.pcitc.fms.service.model.Relations entityConverter);

	@Query("select t from Relations t where t.sourceType = :sourceType and t.sourceCode in :codeList and t.targetType = :targetType")
	List<com.pcitc.fms.dal.pojo.Relations> getRelationsCodesIn(@Param("sourceType") String sourceType, @Param("codeList") List<String> codeList,@Param("targetType") String targetType);
	
	@Query("select t from Relations t where t.sourceType = :sourceType and t.sourceCode in :codeList")
	List<com.pcitc.fms.dal.pojo.Relations> getRelationsCodesIn(@Param("sourceType") String sourceType, @Param("codeList") List<String> codeList);

	/**   
	 * @Title: findByCode   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param relationsCode
	 * @return
	 * @date 2017年10月13日      
	 * @return: Relations
	 * @author 赵振强      
	 */
	Relations findByCode(String relationsCode);

	/**   
	 * @Title: deleteByCode   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param relationsCode
	 * @date 2017年10月13日      
	 * @return: void
	 * @author 赵振强      
	 */
	void deleteByCode(String relationsCode);

}
