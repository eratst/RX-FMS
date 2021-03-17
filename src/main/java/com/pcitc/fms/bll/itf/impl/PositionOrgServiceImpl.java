package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.PositionOrg;
import com.pcitc.fms.bll.itf.PositionOrgService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.PositionOrgDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class PositionOrgServiceImpl implements PositionOrgService{

	@Autowired
	private PositionOrgDao positionOrgDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<PositionOrg> getPositionOrgs(com.pcitc.fms.service.model.PositionOrg positionOrg, Pageable pageable)
			throws BusinessException {
		MyPageImpl properties  = null;
		List<PositionOrg> positionEntityList = null;
		try {
			properties = positionOrgDao.findPagePositionOrgs(positionOrg, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		try {
			positionEntityList = ObjectConverter.listConverter(properties.getContent(),PositionOrg.class);
		} catch (Exception e) {
			throw  new BusinessException("","",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.PositionOrg> pageData = new Pager<>();
		pageData.setContent(positionEntityList);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getCount());
		pageData.setTotalPages(properties.getTotalPages());
	    return pageData;
	}

}
