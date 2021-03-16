package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.FltperCuab;
import com.pcitc.fms.bll.itf.FltperCuabService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.FltperCuabDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class FltperCuabServiceImpl implements FltperCuabService {

	@Autowired
	private FltperCuabDao fltperCuabDao;

	@SuppressWarnings({"unchecked"})
	@Override
	public Pager<FltperCuab> getFltperCuab(com.pcitc.fms.service.model.FltperCuab fltperCuab,Pageable pageable) throws Exception {
		
		Pager<FltperCuab> pageData = new Pager<>();
		
		List<FltperCuab> entityFltperCuabs = new ArrayList<>();
		MyPageImpl pageInfo=null;
		try{
		pageInfo= fltperCuabDao.findFltperCuabs(fltperCuab, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		
		entityFltperCuabs = ObjectConverter.listConverter(pageInfo.getContent(), FltperCuab.class);
		pageData.setContent(entityFltperCuabs);
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
