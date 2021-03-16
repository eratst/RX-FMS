package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.AirDenModCoef;
import com.pcitc.fms.bll.entity.LieCubas;
import com.pcitc.fms.bll.itf.AirDenModCoefService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AirDenModCoefDao;
import com.pcitc.fms.dal.dao.LieCubasDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class AirDenModCoefServiceImpl implements AirDenModCoefService{
	
	@Autowired
	private AirDenModCoefDao airDenModCoefDao;

	@Override
	public Pager<AirDenModCoef> getPageAirDenModCoef(com.pcitc.fms.service.model.AirDenModCoef model,
			Pageable pageable) throws Exception {
		MyPageImpl airDenModCoefPage=null;
		Pager<AirDenModCoef> pageData = new Pager<>();
		try{
			airDenModCoefPage = airDenModCoefDao.getAllDenModCoefs(model, pageable);
		}catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		List<AirDenModCoef> lieCubasEntityList = ObjectConverter.listConverter(airDenModCoefPage.getContent(),
				AirDenModCoef.class);
		pageData.setContent(lieCubasEntityList);
		pageData.setFirst(airDenModCoefPage.isFirst());
		pageData.setLast(airDenModCoefPage.isLast());
		pageData.setNumber(airDenModCoefPage.getNumber());
		pageData.setNumberOfElements(airDenModCoefPage.getNumberOfElements());
		pageData.setSize(airDenModCoefPage.getSize());
		pageData.setSort(airDenModCoefPage.getSort());
		pageData.setTotalElements(airDenModCoefPage.getCount());
		pageData.setTotalPages(airDenModCoefPage.getTotalPages());
		return pageData;
	}

}
