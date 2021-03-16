package com.pcitc.fms.bll.itf.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.bll.entity.CapacityUnit;
import com.pcitc.fms.bll.itf.CapacityUnitService;
import com.pcitc.fms.dal.dao.CapacityUnitDao;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class CapacityUnitServiceImpl implements CapacityUnitService{
	
	@Autowired
	private CapacityUnitDao capacityUnitDao;

	@Override
	public Pager<CapacityUnit> findAll(com.pcitc.fms.service.model.CapacityUnit capacityUnit, Pageable pageable)
			throws Exception {
		Pager<CapacityUnit> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.CapacityUnit> page = capacityUnitDao.findCapacityUnits(capacityUnit, pageable);
		List<CapacityUnit> CapacityUnit_entity = ObjectConverter.listConverter(page.getContent(), CapacityUnit.class);
		pageData.setContent(CapacityUnit_entity);
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

	@Override
	public List<CapacityUnit> findOne(String capacityUnitCode) throws Exception {
		List<com.pcitc.fms.dal.pojo.CapacityUnit> CapacityUnit_pojo = capacityUnitDao.findOneByCapacityUnitCode(capacityUnitCode);
		if (CapacityUnit_pojo == null) {
			throw new BusinessException("", capacityUnitCode, ":该加工能力单位编码不存在！");
		}
		List<CapacityUnit> result = ObjectConverter.listConverter(CapacityUnit_pojo, CapacityUnit.class);
		return result;
	}

}
