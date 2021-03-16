package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.GlbPreCoef;
import com.pcitc.fms.bll.itf.GlbPreCoefService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.GlbPreCoefDao;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;


@Service
public class GlbPreCoefServiceImpl implements GlbPreCoefService{
	
	@Autowired
	private GlbPreCoefDao glbPreCoefDao;

	@Override
	public Pager<GlbPreCoef> getGlbPreCoefs(com.pcitc.fms.service.model.GlbPreCoef glbPreCoef, Pageable pageable)
			throws Exception {

		Pager<GlbPreCoef> pageData = new Pager<>();
		MyPageImpl properties = null;
		List<GlbPreCoef> EntityList = new ArrayList<>();
		try {
			properties = glbPreCoefDao.findGlbPreCoefs(glbPreCoef, pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(), GlbPreCoef.class);
		} catch (Exception e) {
			throw new BusiException("" , e.getMessage());
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
