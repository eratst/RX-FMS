package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.StddmCuabs;
import com.pcitc.fms.bll.itf.StddmCuabsService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.StddmCuabsDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class StddmCuabsServiceImpl implements StddmCuabsService{
	@Autowired
	private StddmCuabsDao stddmCuabsDao;
	
	@Override
	public Pager<StddmCuabs> getPageStddmCuabs(com.pcitc.fms.service.model.StddmCuabs stddmCuabsModel, Pageable pageable)
			throws Exception {
		Pager<StddmCuabs> pageData = new Pager<>();
		MyPageImpl stddmCuabsPage=null;
		try{
			stddmCuabsPage = stddmCuabsDao.getStddmCuabs(stddmCuabsModel, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		List<StddmCuabs> stddmCuabsEntityList = ObjectConverter.listConverter(stddmCuabsPage.getContent(),
				StddmCuabs.class);
		pageData.setContent(stddmCuabsEntityList);
		pageData.setFirst(stddmCuabsPage.isFirst());
		pageData.setLast(stddmCuabsPage.isLast());
		pageData.setNumber(stddmCuabsPage.getNumber());
		pageData.setNumberOfElements(stddmCuabsPage.getNumberOfElements());
		pageData.setSize(stddmCuabsPage.getSize());
		pageData.setSort(stddmCuabsPage.getSort());
		pageData.setTotalElements(stddmCuabsPage.getCount());
		pageData.setTotalPages(stddmCuabsPage.getTotalPages());
		return pageData;
	}

}
