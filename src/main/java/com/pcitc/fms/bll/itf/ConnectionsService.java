package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.service.model.Connections;
import com.pcitc.fms.service.model.Pager;

/**
 * Title: ConnectionsService Description:TODO
 *
 * @author zhenqiang.zhao
 * @version 1.0
 */
@Service
public interface ConnectionsService {

	/**
	 * Gets connections.
	 *
	 * @param connectionsModel the connections model
	 * @return connections
	 * @throws Exception the exception
	 * @Title: getConnections
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: List<com.pcitc.fms.bll.entity.Connections>
	 * @author zhenqiang.zhao
	 */
	public List<com.pcitc.fms.bll.entity.Connections> getConnections(
			Connections connectionsModel) throws BusinessException;

	/**
	 * Gets connections by code.
	 *
	 * @param code the code
	 * @return connections by code
	 * @throws Exception the exception
	 * @Title: getConnectionsById
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: com.pcitc.fms.bll.entity.Connections
	 * @author zhenqiang.zhao
	 */
	public com.pcitc.fms.bll.entity.Connections getConnectionsByCode(
			String code) throws BusinessException;

	/**
	 * Add connections list.
	 *
	 * @param connectionsModelEntityList the connections model entity list
	 * @return list
	 * @throws Exception the exception
	 * @Title: addConnections
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: Integer
	 * @author zhenqiang.zhao
	 */
	public List<com.pcitc.fms.bll.entity.Connections> addConnections(
			List<com.pcitc.fms.bll.entity.Connections> connectionsModelEntityList) throws BusinessException;

	/**
	 * Update connections.
	 *
	 * @param connections the connections
	 * @throws Exception the exception
	 * @Title: updateConnections
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void updateConnections(com.pcitc.fms.bll.entity.Connections connections) throws BusinessException;

	/**
	 * Delete connections by codes.
	 *
	 * @param codeList the code list
	 * @Title: deleteConnectionsByIds
	 * @Description: TODO
	 * @date 2017年6月18日
	 * @return: void
	 * @author zhenqiang.zhao
	 */
	public void deleteConnectionsByCodes(List<String> codeList);

	/**
	 * Gets page connections.
	 *
	 * @param connectionsModel the connections model
	 * @param pageable the pageable
	 * @param factoryCode the factory code
	 * @return page connections
	 * @throws Exception the exception
	 * @Title: getPageConnections
	 * @Description: 分页条件查询
	 * @date 2017年7月27日
	 * @return: List<com.pcitc.fms.bll.entity.Connections>
	 * @author zhenqiang.zhao
	 */
	public Pager<com.pcitc.fms.bll.entity.Connections> getPageConnections(Connections connectionsModel, Pageable pageable, String factoryCode) throws BusinessException;


	/**
	 * Delete connections by code.
	 *
	 * @param code the code
	 * @throws Exception the exception
	 */
	public void deleteConnectionsByCode(String code) throws BusinessException;}
