package com.pcitc.fms.bll.itf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.LiqprodCubaTempCoef;
import com.pcitc.fms.dal.dao.LiqprodCubaTempCoefDao;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class LiqprodCubaTempCoefServiceImpl implements LiqprodCubaTempCoefService{

	@Autowired
	private LiqprodCubaTempCoefDao liqprodCubaTempCoefDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<LiqprodCubaTempCoef> findLiqprodCubaTempCoefs(Pageable pageable) throws Exception {

		Pager<LiqprodCubaTempCoef> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.LiqprodCubaTempCoef> p_liq = liqprodCubaTempCoefDao.findAll(pageable);
		List<LiqprodCubaTempCoef> result = new ArrayList<>();
		result = ObjectConverter.listConverter(p_liq.getContent(), LiqprodCubaTempCoef.class);
		pageData.setContent(result);
		pageData.setFirst(p_liq.isFirst());
		pageData.setLast(p_liq.isLast());
		pageData.setNumber(p_liq.getNumber());
		pageData.setNumberOfElements(p_liq.getNumberOfElements());
		pageData.setSize(p_liq.getSize());
		pageData.setSort(p_liq.getSort());
		pageData.setTotalElements(p_liq.getTotalElements());
		pageData.setTotalPages(p_liq.getTotalPages());
		return pageData;
	}

}
