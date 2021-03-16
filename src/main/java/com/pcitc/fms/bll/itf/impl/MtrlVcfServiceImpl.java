package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.MtrlVcf;
import com.pcitc.fms.bll.itf.MtrlVcfService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.MtrlVcfDao;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
@Component
public class MtrlVcfServiceImpl implements MtrlVcfService {

	@Autowired
	private MtrlVcfDao mtrlVcfDao;
	
	
	@Override
	public Pager<MtrlVcf> getPageMtrlVcf(com.pcitc.fms.service.model.MtrlVcf model, Pageable pageable) throws BusiException {
		MyPageImpl properties = null;
		List<com.pcitc.fms.bll.entity.MtrlVcf> EntityList = null;
		try {
			properties = mtrlVcfDao.findAllDeltCnfgs(model ,pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusiException("", e.getCause().getMessage());
			}
		}
		try {
			EntityList = ObjectConverter.listConverter(properties.getContent(),com.pcitc.fms.bll.entity.MtrlVcf.class);
		} catch (Exception e) {
			throw  new BusiException("",e.getMessage());
		}
		Pager<com.pcitc.fms.bll.entity.MtrlVcf> pageData = new Pager<>();
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
