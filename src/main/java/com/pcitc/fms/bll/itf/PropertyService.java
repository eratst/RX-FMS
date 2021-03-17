package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.PropertyMeta;
import com.pcitc.fms.service.model.Pager;
@Service
public interface PropertyService {
	public Pager<com.pcitc.fms.bll.entity.PropertyMeta> getProperties(com.pcitc.fms.service.model.PropertyMeta propertyModel,Pageable pageable)
			throws BusinessException;

	public void updateProperty(List<com.pcitc.fms.bll.entity.PropertyMeta> propertyMetaList) throws BusinessException;

	public void deletePropertyByCode(String propertyCode) throws BusinessException;

	public List<com.pcitc.fms.bll.entity.PropertyMeta> getProperty(String propertyCode) throws BusinessException;

	public List<PropertyMeta> getProperty(String propertyCode, String entityCode)throws BusinessException;

	public List addProperties(List<PropertyMeta> propertyMetaList, String entityCode)
			throws BusinessException;

	public List<PropertyMeta> getPropertyByEntityCode(String entityCode) throws BusinessException;

	public List<PropertyMeta> getPropertyByEntityAndPropertyCode(String propertyCode, String entityCode) throws BusinessException;
}
