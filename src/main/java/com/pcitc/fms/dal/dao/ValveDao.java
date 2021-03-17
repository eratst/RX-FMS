package com.pcitc.fms.dal.dao;

import com.pcitc.fms.exception.BusinessException;
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

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Tee;
import com.pcitc.fms.dal.pojo.Valve;

 /**
 * Title: ValveDao
 * Description:阀门数据操作接口
 * @author zhenqiang.zhao
 * @date 2017年6月14日
 * @version 1.0
 */
public interface ValveDao extends JpaSpecificationExecutor<Valve>, JpaRepository<Valve,Integer>  {

//	/**   
//	 * @Title: getValves   
//	 * @Description: TODO  
//	 * @param valveModel
//	 * @return
//	 * @date 2017年6月15日      
//	 * @return: List<com.pcitc.fms.dal.pojo.Valve>
//	 * @author zhenqiang.zhao      
//	 */
	@Transactional(rollbackFor = BusinessException.class)
	MyPageImpl getValves(com.pcitc.fms.service.model.Valve valveModel,Pageable pageable);
//	
//	/**   
//	 * @Title: findLinkValves   
//	 * @Description: TODO task mark zhenqiang.zhao
//	 * @param parentId
//	 * @param parentType
//	 * @param valveId
//	 * @return
//	 * @date 2017年7月1日      
//	 * @return: List<com.pcitc.fms.dal.pojo.Valve>
//	 * @author zhenqiang.zhao      
//	 */
//	@Query("select t from Valve t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'valves' and t.code=:code")
//	public List<com.pcitc.fms.dal.pojo.Valve> findLinkValves(@Param("parentCode") String parentCode, @Param("parentType")String parentType,@Param("code")String code);
//	
//	/**   
//	 * @Title: findLinkValves   
//	 * @Description: TODO task mark zhenqiang.zhao
//	 * @param parentId
//	 * @param parentType
//	 * @return
//	 * @date 2017年7月1日      
//	 * @return: List<com.pcitc.fms.dal.pojo.Valve>
//	 * @author zhenqiang.zhao      
//	 */
//	@Query("select t from Valve t,Connections c where c.targetCode = t.code and c.sourceCode = :parentCode and c.sourceType =:parentType and c.targetType = 'valves'")
//	public List<com.pcitc.fms.dal.pojo.Valve> findLinkValves(@Param("parentCode")String parentCode, @Param("parentType")String parentType);
//
//
//	@Query("from Valve where parentType = :parentType and parentId = :parentId and code in (:codeList) order by valveId")
//	List<com.pcitc.fms.dal.pojo.Valve> getValveByCodes(@Param("codeList") List<String> codeList, @Param("parentId") Integer parentId, @Param("parentType") String parentType);
//
//	@Query("from Valve where parentType = :parentType and parentId = :parentId and TeeId in (:idList) order by valveId")
//	List<com.pcitc.fms.dal.pojo.Valve> getTeeByIds(@Param("idList") List<Integer> idList, @Param("parentId") Integer parentId, @Param("parentType") String parentType);
//
//	List<com.pcitc.fms.dal.pojo.Valve> findByParentTypeAndParentCode(String string, String plantCode);
//	@Query(AreaNodeBasicSql.Valve+" and a.nodeCode = :code ")
//	List<Valve> getValveByNodeCode(@Param("code")String code);
////
	@Transactional
	@SQLDelete(sql = "delete from Valve v where v.nodeCode = :code ")
	void deleteByNodeCode(@Param("code")String code);
//
//	@Modifying
//	@Transactional
//	@Query("update Valve set name=:name , shortName=:shortName, type=:type,maintainTime= sysdate, editor=:editor,enabled=:enabled,des =:des where code = :code")
//	void updateValve(
//			@Param("name") String name, 
//			@Param("shortName") String shortName,
//			@Param("type") String type, 
//			@Param("editor") String editor,
//			@Param("enabled") Integer enabled,
//			@Param("des") String des,
//			@Param("code") String code
//			);

}
