package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.LiquidProdCoef;
import com.pcitc.fms.bll.itf.LiquidProdCoefService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.LiquidProdCoefDao;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class LiquidProdCoefServiceImpl implements LiquidProdCoefService {

	@Autowired
	private LiquidProdCoefDao liquidProdCoefDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<LiquidProdCoef> findLiquidProdCoefs(com.pcitc.fms.service.model.LiquidProdCoef liquidProdCoef,
			Pageable pageable) throws Exception {
		Pager<LiquidProdCoef> pageData = new Pager<>();
		List<LiquidProdCoef> result = new ArrayList<>();
		MyPageImpl p_liq =  liquidProdCoefDao.findLiquidProdCoefs(liquidProdCoef, pageable);
		result = ObjectConverter.listConverter(p_liq.getContent(), LiquidProdCoef.class);
		pageData.setContent(result);
		pageData.setFirst(p_liq.isFirst());
		pageData.setLast(p_liq.isLast());
		pageData.setNumber(p_liq.getNumber());
		pageData.setNumberOfElements(p_liq.getNumberOfElements());
		pageData.setSize(p_liq.getSize());
		pageData.setSort(p_liq.getSort());
		pageData.setTotalElements(p_liq.getCount());
		pageData.setTotalPages(p_liq.getTotalPages());
		return pageData;
	}

}
