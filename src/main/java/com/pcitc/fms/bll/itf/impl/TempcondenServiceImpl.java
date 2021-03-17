package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Tempconden;
import com.pcitc.fms.bll.itf.TempcondenService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.TempcondenDao;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class TempcondenServiceImpl implements TempcondenService{

	@Autowired
	private TempcondenDao tempcondenDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Tempconden> getTempconden(com.pcitc.fms.service.model.Tempconden tempconden, Pageable pageable)
			throws Exception {
		Pager<Tempconden> pageData = new Pager<>();
		
		List<Tempconden> entityTempcondens = new ArrayList<>();
		
		MyPageImpl pageInfo = tempcondenDao.findTempconden(tempconden, pageable);
		
		entityTempcondens = ObjectConverter.listConverter(pageInfo.getContent(), Tempconden.class);
		pageData.setContent(entityTempcondens);
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
