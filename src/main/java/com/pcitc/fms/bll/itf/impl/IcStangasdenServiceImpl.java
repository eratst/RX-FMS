package com.pcitc.fms.bll.itf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.IcStangasden;
import com.pcitc.fms.bll.itf.IcStangasdenService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.IcStangasdenDao;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.Exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
@SuppressWarnings("unchecked")
public class IcStangasdenServiceImpl implements IcStangasdenService{
	
	@Autowired
	private IcStangasdenDao icStangasdenDao;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Pager<IcStangasden> findIcStangasdens(com.pcitc.fms.service.model.IcStangasden icStangasden,
			Pageable pageable) throws Exception {
		Pager<IcStangasden> pageData = new Pager<>();
		MyPageImpl icStangasden_pojo = icStangasdenDao.findIcStangasdens(icStangasden, pageable);
		List<IcStangasden> targets = ObjectConverter.listConverter(icStangasden_pojo.getContent(), IcStangasden.class);
		pageData.setContent(targets);
		pageData.setFirst(icStangasden_pojo.isFirst());
		pageData.setLast(icStangasden_pojo.isLast());
		pageData.setNumber(icStangasden_pojo.getNumber());
		pageData.setNumberOfElements(icStangasden_pojo.getNumberOfElements());
		pageData.setSize(icStangasden_pojo.getSize());
		pageData.setSort(icStangasden_pojo.getSort());
		pageData.setTotalElements(icStangasden_pojo.getCount());
		pageData.setTotalPages(icStangasden_pojo.getTotalPages());
		return pageData;
	}

	@Override
	public List<IcStangasden> findIcStangasdenById(Long icStangasdenId) throws Exception {
		com.pcitc.fms.dal.pojo.IcStangasden icStangasden_pojo = icStangasdenDao.findOne(icStangasdenId);
		if (null == icStangasden_pojo) {
			throw new BusinessException("", "", "数据不存在！");
		}
		List<com.pcitc.fms.dal.pojo.IcStangasden> results = new ArrayList<>();
		results.add(icStangasden_pojo);
		List<IcStangasden> targets = ObjectConverter.listConverter(results, IcStangasden.class);
		return targets;
	}


}
