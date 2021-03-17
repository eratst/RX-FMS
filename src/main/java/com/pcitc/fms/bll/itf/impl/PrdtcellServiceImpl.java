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

import com.pcitc.fms.bll.entity.Prdtcell;
import com.pcitc.fms.bll.itf.PrdtcellService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.PrdtcellRepository;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.Exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;


@Service
@Component
public class PrdtcellServiceImpl implements PrdtcellService {
	
	@Autowired
	private PrdtcellRepository repo;
	
	@Override
	public Pager<Prdtcell> getPrdtcellByParam(com.pcitc.fms.service.model.Prdtcell prdtcell, Pageable pageable)
			throws Exception {
		Pager<Prdtcell> pageData = new Pager<>();
		MyPageImpl properties = null;
		if(prdtcell.getInUse()!=null){
			if(prdtcell.getInUse()!=0 && prdtcell.getInUse()!=1){
				throw new BusiException("", "inUse只能为0和1！");
			}
		}
		try {
			properties = repo.getPrdtcells(prdtcell, pageable);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusiException("", e.getCause().getMessage());
			}
		}
		
		List<Prdtcell> entityList = ObjectConverter.listConverter(properties.getContent(), Prdtcell.class);
		pageData.setContent(entityList);
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

	@Override
	public List<Prdtcell> getPrdtcellByCellCode(String cellCode) throws Exception {
		List<com.pcitc.fms.dal.pojo.Prdtcell> Prdtcell_pojos = repo.getPrdtcellByCellCode(cellCode);
		if (Prdtcell_pojos.size() == 0) {
			throw new BusiException("", "生产单元编码不存在:" + cellCode);
		}
		return ObjectConverter.listConverter(Prdtcell_pojos, Prdtcell.class);
	}


}
