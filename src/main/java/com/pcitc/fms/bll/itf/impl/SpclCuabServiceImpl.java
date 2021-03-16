package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.SpclCuab;
import com.pcitc.fms.bll.itf.SpclCuabService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.SpclCuabDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class SpclCuabServiceImpl implements SpclCuabService{

	@Autowired
	private SpclCuabDao spclCuabDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<SpclCuab> getSpclCuab(com.pcitc.fms.service.model.SpclCuab spclCuab, Pageable pageable)
			throws Exception {
		Pager<SpclCuab> pageData = new Pager<>();
		
		List<SpclCuab> entityStdpresCoefs = new ArrayList<>();
		MyPageImpl pageInfo=null;
		try{
		pageInfo= spclCuabDao.findSpclCuab(spclCuab, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		entityStdpresCoefs = ObjectConverter.listConverter(pageInfo.getContent(), SpclCuab.class);
		pageData.setContent(entityStdpresCoefs);
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
