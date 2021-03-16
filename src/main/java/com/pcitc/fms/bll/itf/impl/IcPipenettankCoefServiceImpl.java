package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.IcPipenettankCoef;
import com.pcitc.fms.bll.itf.IcPipenettankCoefService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.IcPipenettankCoefDao;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.Exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class IcPipenettankCoefServiceImpl implements IcPipenettankCoefService{

	@Autowired
	private IcPipenettankCoefDao icPipenettankCoefDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<IcPipenettankCoef> findIcPipenettankCoefs(
			com.pcitc.fms.service.model.IcPipenettankCoef icPipenettankCoef, Pageable pageable) throws Exception {
		Pager<IcPipenettankCoef> pageData = new Pager<>();
		MyPageImpl IcPipenettankCoef_pojo = icPipenettankCoefDao.findIcPipenettankCoefs(icPipenettankCoef, pageable);
		List<IcPipenettankCoef> targets = ObjectConverter.listConverter(IcPipenettankCoef_pojo.getContent(), IcPipenettankCoef.class);
		pageData.setContent(targets);
		pageData.setFirst(IcPipenettankCoef_pojo.isFirst());
		pageData.setLast(IcPipenettankCoef_pojo.isLast());
		pageData.setNumber(IcPipenettankCoef_pojo.getNumber());
		pageData.setNumberOfElements(IcPipenettankCoef_pojo.getNumberOfElements());
		pageData.setSize(IcPipenettankCoef_pojo.getSize());
		pageData.setSort(IcPipenettankCoef_pojo.getSort());
		pageData.setTotalElements(IcPipenettankCoef_pojo.getCount());
		pageData.setTotalPages(IcPipenettankCoef_pojo.getTotalPages());
		return pageData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IcPipenettankCoef> findIcPipenettankCoefById(Long pipenettankCoefId) throws Exception {
		com.pcitc.fms.service.model.IcPipenettankCoef icPipenettankCoef = new com.pcitc.fms.service.model.IcPipenettankCoef();
		icPipenettankCoef.setPipenettankCoefId(pipenettankCoefId);
		Page<com.pcitc.fms.dal.pojo.IcPipenettankCoef> IcPipenettankCoef_pojo = icPipenettankCoefDao.findIcPipenettankCoefs(icPipenettankCoef, null);

		if (null == IcPipenettankCoef_pojo || IcPipenettankCoef_pojo.getContent().isEmpty()) {
			throw new BusinessException("", "", "数据不存在！");
		}
		List<com.pcitc.fms.dal.pojo.IcPipenettankCoef> results = new ArrayList<>();
		results.addAll(IcPipenettankCoef_pojo.getContent());
		List<IcPipenettankCoef> targets = ObjectConverter.listConverter(results, IcPipenettankCoef.class);
		return targets;
	}

}
