package com.pcitc.fms.bll.itf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.LoadPointType;
import com.pcitc.fms.bll.itf.LoadPointTypeService;
import com.pcitc.fms.common.CheckError;
import com.pcitc.fms.common.DataException;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.LoadPointTypeDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class LoadPointTypeServiceImpl implements LoadPointTypeService {
	
	@Autowired
	private LoadPointTypeDao loadPointTypeDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<LoadPointType> findAll(com.pcitc.fms.service.model.LoadPointType loadPointType, Pageable pageable)
			throws Exception {
		Pager<LoadPointType> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.LoadPointType> page = loadPointTypeDao.findLoadPointTypes(loadPointType, pageable);
		List<LoadPointType> loadPointType_entity = ObjectConverter.listConverter(page.getContent(), LoadPointType.class);
		pageData.setContent(loadPointType_entity);
		pageData.setFirst(page.isFirst());
		pageData.setLast(page.isLast());
		pageData.setNumber(page.getNumber());
		pageData.setNumberOfElements(page.getNumberOfElements());
		pageData.setSize(page.getSize());
		pageData.setSort(page.getSort());
		pageData.setTotalElements(page.getTotalElements());
		pageData.setTotalPages(page.getTotalPages());
		return pageData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoadPointType> findOne(String loadPointTypeCode) throws Exception {
		List<com.pcitc.fms.dal.pojo.LoadPointType> type_pojo = loadPointTypeDao.findOneByLoadPointTypeCode(loadPointTypeCode);
		if (type_pojo == null) {
			throw new BusinessException("", loadPointTypeCode, ":不存在该类型编码！");
		}
		List<LoadPointType> result = ObjectConverter.listConverter(type_pojo, LoadPointType.class);
		return result;
	}


}
