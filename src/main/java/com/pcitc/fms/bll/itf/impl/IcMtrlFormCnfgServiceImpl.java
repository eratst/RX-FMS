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

import com.pcitc.fms.bll.entity.IcMtrlFormCnfg;
import com.pcitc.fms.bll.itf.IcMtrlFormCnfgService;
import com.pcitc.fms.common.DataException;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.IcMtrlFormCnfgDao;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.Exception.BusinessException;
import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class IcMtrlFormCnfgServiceImpl implements IcMtrlFormCnfgService{
	
	@Autowired
	private IcMtrlFormCnfgDao icMtrlFormCnfgDao;
	
	@Autowired
	private DbPrimaryIdDao dbPrimaryIdDao;

	@SuppressWarnings("unchecked")
	@Override
	public Pager<IcMtrlFormCnfg> findIcMtrlFormCnfgs(com.pcitc.fms.service.model.IcMtrlFormCnfg icMtrlFormCnfg,
			Pageable pageable) throws Exception {
		Pager<IcMtrlFormCnfg> pageData = new Pager<>();
		MyPageImpl IcMtrlFormCnfg_pojo = icMtrlFormCnfgDao.findIcMtrlFormCnfgs(icMtrlFormCnfg, pageable);
		List<IcMtrlFormCnfg> targets = ObjectConverter.listConverter(IcMtrlFormCnfg_pojo.getContent(), IcMtrlFormCnfg.class);
		pageData.setContent(targets);
		pageData.setFirst(IcMtrlFormCnfg_pojo.isFirst());
		pageData.setLast(IcMtrlFormCnfg_pojo.isLast());
		pageData.setNumber(IcMtrlFormCnfg_pojo.getNumber());
		pageData.setNumberOfElements(IcMtrlFormCnfg_pojo.getNumberOfElements());
		pageData.setSize(IcMtrlFormCnfg_pojo.getSize());
		pageData.setSort(IcMtrlFormCnfg_pojo.getSort());
		pageData.setTotalElements(IcMtrlFormCnfg_pojo.getCount());
		pageData.setTotalPages(IcMtrlFormCnfg_pojo.getTotalPages());
		return pageData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IcMtrlFormCnfg> findIcMtrlFormCnfgById(Long mtrlFormCnfgId) throws Exception {
		com.pcitc.fms.service.model.IcMtrlFormCnfg icMtrlFormCnfg = new com.pcitc.fms.service.model.IcMtrlFormCnfg();
		icMtrlFormCnfg.setMtrlFormCnfgId(mtrlFormCnfgId);
		Page<com.pcitc.fms.dal.pojo.IcMtrlFormCnfg> IcMtrlFormCnfg_pojo = icMtrlFormCnfgDao.findIcMtrlFormCnfgs(icMtrlFormCnfg, null);

		if (null == IcMtrlFormCnfg_pojo || IcMtrlFormCnfg_pojo.getContent().isEmpty()) {
			throw new BusinessException("", "", "数据不存在！");
		}
		List<com.pcitc.fms.dal.pojo.IcMtrlFormCnfg> results = new ArrayList<>();
		results.addAll(IcMtrlFormCnfg_pojo.getContent());
		List<IcMtrlFormCnfg> targets = ObjectConverter.listConverter(results, IcMtrlFormCnfg.class);
		return targets;
	}

}
