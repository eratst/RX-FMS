package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.pojo.Fms_Type;

@Service
public interface TypeDao extends JpaRepository<Fms_Type,Long> {

	@Query(" select t from Fms_Type t where t.code = :code")
	public Fms_Type getType(@Param("code")String code);

	/**   
	 * @Title: getEntity   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param entityType
	 * @param entityId
	 * @date 2017年7月4日      
	 * @return: void
	 * @author zhenqiang.zhao      
	 */
	public List getEntity(String entityType,Integer entityId);
	/**
	 * 
	 * @param entityType
	 * @param entityCode
	 * @return
	 */
	public List getEntity(String entityType,String entityCode);
	
	/**   
	 * @Title: getEntity   
	 * @Description: where 条件 加 parentId
	 * @param entityType
	 * @param entityId
	 * @param parentId
	 * @return
	 * @date 2017年7月21日      
	 * @return: List
	 * @author zhenqiang.zhao      
	 */
	public List getEntity(String entityType,Integer entityId,Integer parentId);

	/**   
	 * @Title: getNodeEntity   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param nodeName
	 * @param nodeId
	 * @date 2017年8月5日      
	 * @return: void
	 * @author 赵振强      
	 */
	public Object getNodeEntity(String nodeName, Integer nodeId);
	
	public Object getNodeEntity(String nodeName, String nodeCode);
	
	/**   
	 * @Description: getNodeEntity   该方法只用于 checkURLForThree
	 * @param nodeName
	 * @param nodeId
	 * @param pojoName
	 * @return
	 * @date 2017年8月8日      
	 * @return: Object
	 * @author 赵振强      
	 */
	public Object getNodeEntity(String nodeName, Integer nodeId,String pojoName);
	
	public Object getNodeEntity(String nodeName, String nodeCode,String pojoName);
	
//	public List  getA(String factoryType, Integer factoryId, String areaType, Integer areaId,String nodeType, Integer nodeId);
}
