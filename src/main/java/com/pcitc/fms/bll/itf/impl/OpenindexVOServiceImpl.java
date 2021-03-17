/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: OpenindexVOServiceImpl
 * Date:18-3-8 下午6:23
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.itf.OpenindexVOService;
import com.pcitc.fms.dal.dao.OpenindexVODao;
import com.pcitc.fms.service.model.Openindex;
import com.pcitc.fms.service.model.OpenindexVO;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * The type Openindex vo service.
 */
@Service
@Component
public class OpenindexVOServiceImpl implements OpenindexVOService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(OpenindexVOServiceImpl.class);
	/**
	 * The Openindex vo dao.
	 */
	@Autowired
	private OpenindexVODao openindexVODao;

	/**
	 * Gets openindex v os.
	 *
	 * @param unitCode the unit code
	 * @return the openindex v os
	 * @throws BusinessException the business exception
	 */
	@Override
	public List<OpenindexVO> getOpenindexVOs(String unitCode) throws BusinessException {
		List<com.pcitc.fms.dal.pojo.OpenindexVO> openindexVOPojoList = openindexVODao.findOpenindexs(unitCode);
		List<OpenindexVO> openindexVOModleList = null;
		try {
			openindexVOModleList = ObjectConverter.listConverter(openindexVOPojoList, OpenindexVO.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
		return openindexVOModleList;
	}


}

	

