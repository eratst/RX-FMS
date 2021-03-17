package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.TankCnfg;
import com.pcitc.fms.bll.itf.TankCnfgService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.TankCnfgRepository;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.Exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
@Component
@SuppressWarnings("unchecked")
public class TankCnfgServiceImpl implements TankCnfgService {

	@Autowired
	private TankCnfgRepository tankCnfgRepo;

	@Override
	public Pager<TankCnfg> getPageTankCnfg(com.pcitc.fms.service.model.TankCnfg tankCnfgModel, Pageable pageable) throws BusiException {
		MyPageImpl properties = null;
		List<com.pcitc.fms.bll.entity.TankCnfg> tankCnfgEntityList = null;
		try {
			properties = tankCnfgRepo.findTankCnfgs(tankCnfgModel ,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusiException("", e.getCause().getMessage());
			}
		}
		try {
			tankCnfgEntityList = ObjectConverter.listConverter(properties.getContent(),com.pcitc.fms.bll.entity.TankCnfg.class);
		} catch (Exception e) {
			throw  new BusiException("",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.TankCnfg> pageData = new Pager<>();
		pageData.setContent(tankCnfgEntityList);
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
