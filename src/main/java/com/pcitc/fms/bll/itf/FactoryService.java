/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: FactoryService
 * Date:18-3-5 上午10:24
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf;

import com.pcitc.fms.bll.entity.Factory;
import com.pcitc.fms.service.model.FactoryModelStr;
import com.pcitc.fms.service.model.Pager;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.pcitc.fms.exception.BusinessException;

/**
 * The interface Factory service.
 */
public interface FactoryService {

	/**
	 * Gets factory by code.
	 *
	 * @param factoryCode the factory code
	 * @return the factory by code
	 * @throws BusinessException the business exception
	 */
	public List<Factory> getFactoryByCode(String factoryCode) throws BusinessException;

	/**
	 * Add factories list.
	 *
	 * @param factoryEntityList the factory entity list
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	public List<Factory> addFactories(List<Factory> factoryEntityList) throws BusinessException;

	/**
	 * Update factory.
	 *
	 * @param factoryEntity the factory entity
	 * @throws BusinessException the business exception
	 */
	public void updateFactory(List<Factory> factoryEntity)throws BusinessException;

	/**
	 * Delete by factory code.
	 *
	 * @param factoryId the factory id
	 * @param urlType the url type
	 * @throws BusinessException the business exception
	 */
	public void deleteByFactoryCode(String factoryId,String urlType)throws BusinessException;

	/**
	 * Gets factories.
	 *
	 * @param modelStr the model str
	 * @param pageable the pageable
	 * @return the factories
	 * @throws BusinessException the business exception
	 */
	public Pager<Factory> getFactories(FactoryModelStr modelStr,Pageable pageable)throws BusinessException;

	/**
	 * Add factories list.
	 *
	 * @param listConverter the list converter
	 * @param orgId the org id
	 * @param typeByUrl the type by url
	 * @return the list
	 * @throws BusinessException the business exception
	 */
	public List<Factory> addFactories(List listConverter, String orgId,String typeByUrl) throws  BusinessException;

	/**
	 * Gets factory by org code.
	 *
	 * @param orgId the org id
	 * @param type the type
	 * @param urlType the url type
	 * @return the factory by org code
	 * @throws BusinessException the business exception
	 */
	public List<Factory> getFactoryByOrgCode(String orgId,String type,String urlType) throws BusinessException;
	
//	Pager<Factory> getPageFactories(FactoryModelStr modelStr, Pageable pageable) throws Exception;


}
