package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Associatives;
import com.pcitc.fms.bll.itf.AssociativesService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.AssociativesDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
@Component
public class AssociativesServiceImpl implements AssociativesService{

	@Autowired
	private AssociativesDao associativesDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Associatives> findAssociatives(com.pcitc.fms.service.model.Associatives associatives, Pageable pageable) throws Exception {
		Pager<Associatives> pageData = new Pager<>();
		MyPageImpl properties=null;
		List<Associatives> EntityList = null;
		try {
			properties = associativesDao.findAssociatives(associatives, pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(), Associatives.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
			throw new BusinessException("", "", e.getMessage());
		}
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
