/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: NodeAndAreaServiceImpl
 * Date:18-3-8 下午6:22
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.NodeAndArea;
import com.pcitc.fms.bll.itf.NodeAndAreaService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.dal.dao.TypeDao;
import com.pcitc.fms.dal.pojo.INodeAndArea;
import com.pcitc.fms.exception.BusinessException;

/**
 * Title: OrgRelationServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年7月21日
 */
@Service
public class NodeAndAreaServiceImpl implements NodeAndAreaService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(NodeAndAreaServiceImpl.class);
	/**
	 * The Type dao.
	 */
	@Autowired
	private TypeDao typeDao;

	/**
	 * Title: getNodeAndAreas Description: TODO task mark zhenqiang.zhao
	 *
	 * @param nodeAndAreaModel the node and area model
	 * @return node and areas
	 * @throws BusinessException the business exception
	 * @date 2017年8月6日
	 * @author 赵振强
	 * @see com.pcitc.fms.bll.itf.NodeAndAreaService#getNodeAndAreas(com.pcitc.fms.service.model.NodeAndArea)
	 * com.pcitc.fms.bll.itf.NodeAndAreaService#getNodeAndAreas(com.pcitc.fms.service.model.NodeAndArea)
	 */
	@Override
	public com.pcitc.fms.bll.entity.NodeAndArea getNodeAndAreas(com.pcitc.fms.service.model.NodeAndArea nodeAndAreaModel) throws BusinessException {
		INodeAndArea objectEntity = null;
		try {
			NodeAndArea nodeandarea = new NodeAndArea();
			objectEntity = (INodeAndArea)typeDao.getNodeEntity(nodeAndAreaModel.getNodeType(), nodeAndAreaModel.getNodeCode());
			if (objectEntity == null) {
				throw new BusinessException("", "", CheckError.IS_NOT_EXIST+nodeAndAreaModel.getNodeType()+":"+nodeAndAreaModel.getNodeCode());
			}
			nodeandarea.setNodeCode(objectEntity.getCode());
			nodeandarea.setNodeName(objectEntity.getName());
			nodeandarea.setNodeType(nodeAndAreaModel.getNodeType());
			String parentType = objectEntity.getParentType();
			objectEntity = (INodeAndArea)typeDao.getNodeEntity(parentType, objectEntity.getParentCode());
			if (objectEntity != null) {
				nodeandarea.setAreaCode(objectEntity.getCode());
				nodeandarea.setAreaName(objectEntity.getName());
				nodeandarea.setAreaType(parentType);
			}else {
				throw new BusinessException("", "", CheckError.PARENTTYPE_NOT);
			}
			return  nodeandarea;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

}
