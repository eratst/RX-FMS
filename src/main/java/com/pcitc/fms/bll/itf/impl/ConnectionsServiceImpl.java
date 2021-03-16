/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: ConnectionsServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.Connections;
import com.pcitc.fms.bll.itf.ConnectionsService;
import com.pcitc.fms.common.CheckUtil;
import com.pcitc.fms.dal.dao.ConnectionsDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.exception.BusinessExceptionMessage;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: ConnectionsServiceImpl Description:TODO
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月16日
 */
@Service
public class ConnectionsServiceImpl implements ConnectionsService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(ConnectionsServiceImpl.class);

	/**
	 * The Connections dao.
	 */
	@Autowired
	private  ConnectionsDao connectionsDao;

	/**
	 * Title: getConnections Description: 无分页,暂时无调用
	 *
	 * @param connectionsModel the connections model
	 * @return connections connections
	 * @throws BusinessException the business exception
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao
	 * @see com.pcitc.fms.bll.itf.ConnectionsService#getConnections(com.pcitc.fms.service.model.Connections)
	 * com.pcitc.fms.bll.itf.ConnectionsService#getConnections(com.pcitc.fms.service.model.Connections)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List<Connections> getConnections(
			com.pcitc.fms.service.model.Connections connectionsModel) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.Connections> connectionsList = connectionsDao.getConnections(connectionsModel);
		try {
			return null == connectionsList ? null :ObjectConverter.listConverter(connectionsList, Connections.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Title: getConnectionsById Description: TODO
	 *
	 * @param connectionsCode the connections code
	 * @return connections by code
	 * @throws BusinessException the business exception
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao  com.pcitc.fms.bll.itf.ConnectionsService#getConnectionsById(java.lang.Integer)
	 * com.pcitc.fms.bll.itf.ConnectionsService#getConnectionsById(java.lang.Integer)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public Connections getConnectionsByCode(String connectionsCode) throws BusinessException {
		com.pcitc.fms.dal.pojo.Connections connections= connectionsDao.findByCode(connectionsCode);
		try {
			return null==connections ?null: ObjectConverter.entityConverter(connections, Connections.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Title: addConnections Description: TODO
	 *
	 * @param connectionsModelEntityList the connections model entity list
	 * @return list list
	 * @throws BusinessException the business exception
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao
	 * @see com.pcitc.fms.bll.itf.ConnectionsService#addConnections(java.util.List)
	 * com.pcitc.fms.bll.itf.ConnectionsService#addConnections(java.util.List)
	 * com.pcitc.fms.bll.itf.ConnectionsService#addConnections(java.util.List)
	 * com.pcitc.fms.bll.itf.ConnectionsService#addConnections(java.util.List)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public List<Connections> addConnections(List<Connections> connectionsModelEntityList) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.Connections> connListPojo = new ArrayList<com.pcitc.fms.dal.pojo.Connections>();
		List<com.pcitc.fms.dal.pojo.Connections> connectionsListPojo = null;
		try {
			connectionsListPojo = ObjectConverter.listConverter(connectionsModelEntityList, com.pcitc.fms.dal.pojo.Connections.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		for (com.pcitc.fms.dal.pojo.Connections connections : connectionsListPojo) {
			BusinessException exception = CheckUtil.getUniqueConnectionsException(getUniqueConnections(connections));//唯一性校验
			if (exception != null) {
				throw new BusinessException("", "", exception.getMessage());
			}
		}
		try {
			connListPojo = connectionsDao.save(connectionsListPojo);
			connectionsDao.flush();
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException("", "", BusinessExceptionMessage.getPrimarykeyUnique(e));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		try {
			return ObjectConverter.listConverter(connListPojo, Connections.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}


	/**
	 * Gets unique connections.
	 *
	 * @param connections the connections
	 * @return unique connections
	 * @throws BusinessException the business exception
	 * @Title: getUniqueConnections
	 * @Description:相同的数据不能重复添加 , 数据唯一性校验
	 * @date 2017年8月10日
	 * @return: Connections
	 * @author 赵振强
	 */
	@Transactional(rollbackFor=BusinessException.class)
	public Connections getUniqueConnections(com.pcitc.fms.dal.pojo.Connections connections) throws BusinessException {
		com.pcitc.fms.dal.pojo.Connections  connectionsPojo = null;
		try {
			connectionsPojo = connectionsDao.getUniqueConnections(
					ObjectConverter.entityConverter(connections, com.pcitc.fms.service.model.Connections.class));
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		try {
			return  (null != connectionsPojo)?ObjectConverter.entityConverter(connectionsPojo, Connections.class):null;
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

	/**
	 * Title: updateConnections Description: TODO
	 *
	 * @param connections the connections
	 * @throws BusinessException the business exception
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao
	 * @see com.pcitc.fms.bll.itf.ConnectionsService#updateConnections(com.pcitc.fms.bll.entity.Connections)
	 * com.pcitc.fms.bll.itf.ConnectionsService#updateConnections(com.pcitc.fms.bll.entity.Connections)
	 * com.pcitc.fms.bll.itf.ConnectionsService#updateConnections(com.pcitc.fms.bll.entity.Connections)
	 * com.pcitc.fms.bll.itf.ConnectionsService#updateConnections(com.pcitc.fms.bll.entity.Connections)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void updateConnections(Connections connections) throws BusinessException {
		com.pcitc.fms.dal.pojo.Connections connectionsPojo = null;
		try {
			connectionsPojo = ObjectConverter.entityConverter(connections, com.pcitc.fms.dal.pojo.Connections.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		com.pcitc.fms.dal.pojo.Connections findByCode = connectionsDao.findByCode(connectionsPojo.getCode());
		if(null ==findByCode) {
			throw new BusinessException("", "", "连接关系编码不存在:"+connectionsPojo.getCode());
		}
		connectionsPojo.setConnectionId(findByCode.getConnectionId());
		
		connectionsDao.saveAndFlush(connectionsPojo);
	}

	/**
	 * Title: deleteConnectionsByIds Description: TODO
	 *
	 * @param codeList the code list
	 * @date 2017年6月18日
	 * @author zhenqiang.zhao com.pcitc.fms.bll.itf.ConnectionsService#deleteConnectionsByIds(java.util.List)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void deleteConnectionsByCodes(List<String> codeList) {
		for (String connectionsCode : codeList) {
			connectionsDao.deleteByCode(connectionsCode);
		}
		
	}

	/**
	 * Delete connections by code.
	 *
	 * @param code the code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public void deleteConnectionsByCode(String code) throws BusinessException {
			try {
				connectionsDao.deleteByCode(code);
			} catch (EmptyResultDataAccessException e) {
				throw new BusinessException("", "", BusinessExceptionMessage.getDataNotExist("ID", code));
			} catch (Exception e) {
				log.error(e.getMessage());
				throw e;
			}
	}

	/**
	 * Title: getPageConnections Description: TODO task mark zhenqiang.zhao
	 *
	 * @param connectionsModel the connections model
	 * @param pageable the pageable
	 * @param factoryCode the factory code
	 * @return page connections
	 * @throws BusinessException the business exception
	 * @date 2017年7月27日
	 * @author zhenqiang.zhao org.springframework.data.domain.Pageable)
	 * com.pcitc.fms.bll.itf.ConnectionsService#getPageConnections(com.pcitc.fms.service.model.Connections,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	@Transactional(rollbackFor=BusinessException.class)
	public Pager<Connections> getPageConnections(com.pcitc.fms.service.model.Connections connectionsModel,
			Pageable pageable,String factoryCode) throws BusinessException {
		Page<com.pcitc.fms.dal.pojo.Connections>  properties = connectionsDao.getPageConnections(connectionsModel,pageable,factoryCode);
		List<Connections> propertiesEntity= null;
		try {
			propertiesEntity = ObjectConverter.listConverter(properties.getContent(), Connections.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<Connections> pageData = new Pager<>();
		pageData.setContent(propertiesEntity);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getTotalElements());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}

}
