package com.pcitc.fms.dal.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.pcitc.fms.dal.pojo.Connections;
import com.pcitc.fms.dal.pojo.OrgRelation;

 /**
 * Title: ConnectionsDao
 * Description:连接集合
 * @author zhenqiang.zhao
 * @date 2017年6月16日
 * @version 1.0
 */
public interface ConnectionsDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Connections>,
JpaRepository<com.pcitc.fms.dal.pojo.Connections, Integer>{

	/**   
	 * @Title: getConnections   
	 * @Description: TODO  
	 * @param connectionsModel
	 * @return
	 * @date 2017年6月18日      
	 * @return: List<com.pcitc.fms.dal.pojo.Connections>
	 * @author zhenqiang.zhao      
	 */
	List<com.pcitc.fms.dal.pojo.Connections> getConnections(
			com.pcitc.fms.service.model.Connections connectionsModel);

	/**   
	 * @Title: getPageConnections   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param connectionsModel
	 * @param pageable
	 * @return
	 * @date 2017年7月27日      
	 * @return: Page<Connections>
	 * @author zhenqiang.zhao      
	 * @param factoryCode 
	 */
	Page<Connections> getPageConnections(com.pcitc.fms.service.model.Connections connectionsModel, Pageable pageable, String factoryCode);
	/**   
	 * @Title: getUniqueOrgRelations   
	 * @Description:用于验证数据唯一性的查询语句,不允许重复的记录添加
	 * @param ConnectionsModle 包含工厂id,源id,目标id,源类型,目标类型 五个条件确定一条记录
	 * @return
	 * @date 2017年8月10日      
	 * @return: OrgRelation
	 * @author 赵振强      
	 */
	public Connections getUniqueConnections(com.pcitc.fms.service.model.Connections entityConverter);

	/**   
	 * @Title: findByCode   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param connectionsCode
	 * @return
	 * @date 2017年10月13日      
	 * @return: Connections
	 * @author 赵振强      
	 */
	Connections findByCode(String connectionsCode);

	/**   
	 * @Title: deleteByCode   
	 * @Description: TODO task mark zhenqiang.zhao
	 * @param code
	 * @date 2017年10月13日      
	 * @return: void
	 * @author 赵振强      
	 */
	void deleteByCode(String code);
}
