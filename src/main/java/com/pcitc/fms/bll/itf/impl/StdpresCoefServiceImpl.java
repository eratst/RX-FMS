package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.StdpresCoef;
import com.pcitc.fms.bll.itf.StdpresCoefService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.StdpresCoefDao;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class StdpresCoefServiceImpl implements StdpresCoefService{

	@Autowired
	private StdpresCoefDao stdpresCoefDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<StdpresCoef> getStdpresCoe(com.pcitc.fms.service.model.StdpresCoef stdpresCoef, Pageable pageable)
			throws Exception {
		Pager<StdpresCoef> pageData = new Pager<>();
		
		List<StdpresCoef> entityStdpresCoefs = new ArrayList<>();
		
		MyPageImpl pageInfo = stdpresCoefDao.findStdpresCoef(stdpresCoef, pageable);
		
		entityStdpresCoefs = ObjectConverter.listConverter(pageInfo.getContent(), StdpresCoef.class);
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
