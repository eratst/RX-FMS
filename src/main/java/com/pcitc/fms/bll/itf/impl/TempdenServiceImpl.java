package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Tempden;
import com.pcitc.fms.bll.itf.TempdenService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.TempdenDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class TempdenServiceImpl implements TempdenService{

	@Autowired
	private TempdenDao tempdenDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Tempden> getTempden(com.pcitc.fms.service.model.Tempden tempden, Pageable pageable) throws Exception {
		Pager<Tempden> pageData = new Pager<>();
		
		List<Tempden> entityTempdens = new ArrayList<>();
		MyPageImpl pageInfo=null;
		try{
		pageInfo= tempdenDao.findTempden(tempden, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		entityTempdens = ObjectConverter.listConverter(pageInfo.getContent(), Tempden.class);
		pageData.setContent(entityTempdens);
		pageData.setFirst(pageInfo.isFirst());
		pageData.setLast(pageInfo.isLast());
		pageData.setNumber(pageInfo.getNumber());
		pageData.setNumberOfElements(pageInfo.getNumberOfElements());
		pageData.setSize(pageInfo.getSize());
		pageData.setSort(pageInfo.getSort());
		pageData.setTotalElements(pageInfo.getCount());
		pageData.setTotalPages(pageInfo.getTotalPages());
		
		return pageData;
	}

}
