package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.StanDen;
import com.pcitc.fms.bll.itf.StanDenService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.StanDenDao;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class StanDenServiceImpl implements StanDenService{
	
	@Autowired
	private StanDenDao stanDenDao;

	@Override
	public Pager<StanDen> getStanDens(com.pcitc.fms.service.model.StanDen stanDen, Pageable pageable) throws Exception {

		Pager<StanDen> pageData = new Pager<>();
		MyPageImpl properties = null;
		List<StanDen> EntityList = new ArrayList<>();
		try {
			properties = stanDenDao.findStanDens(stanDen, pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(), StanDen.class);
		} catch (Exception e) {
			throw new BusiException("", e.getMessage());
		}
		pageData.setContent(EntityList);
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
