package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.EntityMeta;
import com.pcitc.fms.service.model.Pager;

/**
 * The interface Entity service.
 */
public interface EntityService {

	/**
	 * Delete entity by code.
	 *
	 * @param entityCode the entity code
	 * @throws Exception the exception
	 */
	public void deleteEntityByCode(String entityCode) throws BusinessException;

	/**
	 * Update entity.
	 *
	 * @param entity the entity
	 * @throws Exception the exception
	 */
	public void updateEntity(List<com.pcitc.fms.bll.entity.EntityMeta> entity) throws BusinessException;

	/**
	 * Add entities list.
	 *
	 * @param entityList the entity list
	 * @return the list
	 * @throws Exception the exception
	 */
	public List addEntities(List<com.pcitc.fms.bll.entity.EntityMeta> entityList) throws BusinessException;

	/**
	 * Gets entity by code.
	 *
	 * @param entityCode the entity code
	 * @return the entity by code
	 * @throws Exception the exception
	 */
	public List<com.pcitc.fms.bll.entity.EntityMeta> getEntityByCode(String entityCode) throws BusinessException;

	/**
	 * Gets entities.
	 *
	 * @param findEntities the find entities
	 * @param pageable the pageable
	 * @return the entities
	 * @throws Exception the exception
	 */
	public Pager<EntityMeta> getEntities(com.pcitc.fms.service.model.EntityMeta findEntities,Pageable pageable) throws BusinessException;

	/**
	 * Update entity.
	 *
	 * @param entity the entity
	 * @throws Exception the exception
	 */
	public void updateEntity(EntityMeta entity) throws BusinessException;

}
