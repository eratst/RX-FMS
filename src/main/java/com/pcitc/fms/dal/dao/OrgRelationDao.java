package com.pcitc.fms.dal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.dal.pojo.OrgRelation;

 /**
 * Title: OrgRelationDao
 * Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年7月1日
 * @version 1.0
 */
public interface OrgRelationDao extends JpaRepository<OrgRelation, Integer>,
		JpaSpecificationExecutor<OrgRelation> {

	/**   
	 * @Title: getOrgRelation   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param orgRelationModel
	 * @return
	 * @date 2017年7月1日      
	 * @return: List<com.pcitc.fms.dal.pojo.OrgRelation>
	 * @author zhenqiang.zhao      
	 */
	public Page<com.pcitc.fms.dal.pojo.OrgRelation> getOrgRelations(
			com.pcitc.fms.service.model.OrgRelation orgRelationModel,Pageable pageable);

	/**   
	 * @Title: deleteByOrgRelationIdIn   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param integerList
	 * @date 2017年7月1日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void deleteByCodeIn(List<String> integerList);

	/**   
	 * @Title: findByFactoryIdAndOrgRelationId   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param factoryCode
	 * @param orgRelationCode
	 * @return
	 * @date 2017年7月23日      
	 * @return: OrgRelation
	 * @author zhenqiang.zhao      
	 */
	public OrgRelation findByFactoryCodeAndCode(String factoryCode, String orgRelationCode);

	/**   
	 * @Title: deleteOrgRelationByFactoryIdAndOrgRelationId   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param factoryCode
	 * @param orgRelationCode
	 * @date 2017年7月23日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void deleteOrgRelationByFactoryCodeAndCode(String factoryCode, String orgRelationCode);

	/**   
	 * @Title: deleteByOrgRelationIdIn   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param factoryCode
	 * @param integerList
	 * @date 2017年7月23日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public void deleteOrgRelationByFactoryCodeAndCodeIn(String factoryCode, List<String> codeList);

	/**   
	 * @Title: getUniqueOrgRelations   
	 * @Description:用于验证数据唯一性的查询语句,不允许重复的记录添加
	 * @param orgRelationModle 包含工厂id,源id,目标id,源类型,目标类型 五个条件确定一条记录
	 * @param entityConverter
	 * @return
	 * @date 2017年7月25日      
	 * @return: Page<OrgRelation>
	 * @author zhenqiang.zhao      
	 */
	public OrgRelation getUniqueOrgRelations(com.pcitc.fms.service.model.OrgRelation entityConverter);

	@Query("from OrgRelation where sourceCode = :sourceCode and sourceType = :sourceType and targetType='factories'")
	public List<OrgRelation> findAllBySourceCodeAndSourceTypeAndTargetTypes(@Param("sourceCode")String sourceCode, @Param("sourceType")String sourceType);
	
	@Query("from OrgRelation where sourceCode = :sourceCode and sourceType = :sourceType and targetType='factories'")
	public OrgRelation findAllBySourceCodeAndSourceTypeAndTargetType(@Param("sourceCode")String sourceCode, @Param("sourceType")String sourceType);

	/**   
	 * @Title: findByCode   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param code
	 * @return
	 * @date 2017年10月13日      
	 * @return: OrgRelation
	 * @author 赵振强      
	 */
	public OrgRelation findByCode(String code);

	public void deleteByCode(String code);

	@Query("from OrgRelation where sourceCode = :sourceCode and sourceType = :sourceType and targetType in :types")
	public com.pcitc.fms.dal.pojo.OrgRelation findAllBySourceIdAndTargetType(@Param("sourceCode")String parseInt, @Param("sourceType")String string,@Param("types")List<String> types);


	/**   
	 * @Title: deleteByTargetTypeAndTargetCodeAndFactoryCode   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param urlType
	 * @param warehouseCode
	 * @date 2017年11月10日      
	 * @return: void
	 * @author 赵振强      
	 */
	@Transactional
	@SQLDelete(sql = "delete from OrgRelation where targetType = :urlType and targetCode = :warehouseCode and factoryCode = :factoryCode")
	public void deleteByTargetTypeAndTargetCodeAndFactoryCode(@Param("urlType")String urlType, @Param("warehouseCode")String warehouseCode,@Param("factoryCode")String factoryCode);

	/**   
	 * @Title: deleteByFactoryCode   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param urlType
	 * @param factoryCode
	 * @date 2017年11月10日      
	 * @return: void
	 * @author 赵振强      
	 */
	public void deleteByFactoryCode(String urlType, String factoryCode);


}
