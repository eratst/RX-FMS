package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.StdSec;
import com.pcitc.fms.bll.itf.StdSecService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.StdSecDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;
	
@Service
public class StdSecServiceImpl implements StdSecService {
	@Autowired
	private StdSecDao stdSecDao;
	@Override
	public Pager<StdSec> getPageStdSec(com.pcitc.fms.service.model.StdSec stdSecModel,
			Pageable pageable) throws Exception {

		MyPageImpl stdSecPage=null;
		Pager<StdSec> pageData = new Pager<>();
		try{
		stdSecPage= stdSecDao.getStdSec(stdSecModel, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		List<StdSec> stdSecEntityList = ObjectConverter.listConverter(stdSecPage.getContent(),
				StdSec.class);
		pageData.setContent(stdSecEntityList);
		pageData.setFirst(stdSecPage.isFirst());
		pageData.setLast(stdSecPage.isLast());
		pageData.setNumber(stdSecPage.getNumber());
		pageData.setNumberOfElements(stdSecPage.getNumberOfElements());
		pageData.setSize(stdSecPage.getSize());
		pageData.setSort(stdSecPage.getSort());
		pageData.setTotalElements(stdSecPage.getCount());
		pageData.setTotalPages(stdSecPage.getTotalPages());
		return pageData;
	}

}
