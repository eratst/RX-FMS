package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.StdcmmmCubas;
import com.pcitc.fms.bll.itf.StdcmmmCubasService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.StdcmmmCubasDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class StdcmmmCubasServiceImpl implements StdcmmmCubasService {
	@Autowired
	private StdcmmmCubasDao stdcmmmCubasDao;
	
	@Override
	public Pager<StdcmmmCubas> getstdcmmmCubas(com.pcitc.fms.service.model.StdcmmmCubas stdcmmmCubasModel,
			Pageable pageable) throws Exception {
		
		Pager<StdcmmmCubas> pageData = new Pager<>();
		MyPageImpl stdcmmmCubasPage=null;
		try{
		stdcmmmCubasPage= stdcmmmCubasDao.getStdcmmmCubas(stdcmmmCubasModel, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		List<StdcmmmCubas> stdcmmmCubasEntityList = ObjectConverter.listConverter(stdcmmmCubasPage.getContent(),
				StdcmmmCubas.class);
		pageData.setContent(stdcmmmCubasEntityList);
		pageData.setFirst(stdcmmmCubasPage.isFirst());
		pageData.setLast(stdcmmmCubasPage.isLast());
		pageData.setNumber(stdcmmmCubasPage.getNumber());
		pageData.setNumberOfElements(stdcmmmCubasPage.getNumberOfElements());
		pageData.setSize(stdcmmmCubasPage.getSize());
		pageData.setSort(stdcmmmCubasPage.getSort());
		pageData.setTotalElements(stdcmmmCubasPage.getCount());
		pageData.setTotalPages(stdcmmmCubasPage.getTotalPages());
		return pageData;
	}

}
