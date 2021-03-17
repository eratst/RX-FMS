package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.PrdtcellMeasindex;
import com.pcitc.fms.bll.itf.PrdtcellMeasindexService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.PrdtcellMeasindexDao;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 *
 * zhao.li
 */
@Service
@Component
public class PrdtcellMeasindexServiceImpl implements PrdtcellMeasindexService {

	@Autowired
	private PrdtcellMeasindexDao prdtcellMeasindexDao;

	@SuppressWarnings("unchecked")
	@Override
	public Pager<PrdtcellMeasindex> findPrdtcellMeasindexs(
			com.pcitc.fms.service.model.PrdtcellMeasindex prdtcellMeasindex, Pageable pageable) throws Exception {
		Pager<PrdtcellMeasindex> pageData = new Pager<>();
		MyPageImpl properties = null;
		if(prdtcellMeasindex.getInUse()!=null){
			if(prdtcellMeasindex.getInUse()!=0 && prdtcellMeasindex.getInUse()!=1){
				throw new BusiException("", "inUse只能为0和1！");
			}
		}
		try {
			properties = prdtcellMeasindexDao
			.getPrdtcellMeasindexs(prdtcellMeasindex, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusiException("", e.getCause().getMessage());
			}
		}
		
		List<PrdtcellMeasindex> entityList = ObjectConverter.listConverter(properties.getContent(),
				PrdtcellMeasindex.class);
		pageData.setContent(entityList);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<PrdtcellMeasindex> findPrdtcellMeasindexByIdxCode(String idxCode) throws Exception {
		List<com.pcitc.fms.dal.pojo.PrdtcellMeasindex> prdtcellMeasindexPojo = prdtcellMeasindexDao
				.getPrdtcellMeasindexByIdxCode(idxCode);
		if (prdtcellMeasindexPojo.isEmpty()) {
			throw new BusiException("", "生产单元度量指标不存在:" + idxCode);
		}
		return ObjectConverter.listConverter(prdtcellMeasindexPojo, PrdtcellMeasindex.class);
	}


}
