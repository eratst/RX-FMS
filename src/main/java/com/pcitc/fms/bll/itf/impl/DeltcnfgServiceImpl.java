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

import com.pcitc.fms.bll.entity.Deltcnfg;
import com.pcitc.fms.bll.itf.DeltcnfgService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.DeltcnfgRepository;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.Exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
@Component
public class DeltcnfgServiceImpl implements DeltcnfgService{
	
	@Autowired
	private DeltcnfgRepository deltcnfgRepository;
	

	@Override
	public Pager<Deltcnfg> getPageDeltcnfg(com.pcitc.fms.service.model.Deltcnfg deltcnfgModel, Pageable pageable) throws BusiException {
		MyPageImpl properties = null;
		List<com.pcitc.fms.bll.entity.Deltcnfg> tankCnfgEntityList = null;
		try {
			properties = deltcnfgRepository.findAllDeltCnfgs(deltcnfgModel ,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusiException("", e.getCause().getMessage());
			}
		}
		try {
			tankCnfgEntityList = ObjectConverter.listConverter(properties.getContent(),com.pcitc.fms.bll.entity.Deltcnfg.class);
		} catch (Exception e) {
			throw  new BusiException("",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.Deltcnfg> pageData = new Pager<>();
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
