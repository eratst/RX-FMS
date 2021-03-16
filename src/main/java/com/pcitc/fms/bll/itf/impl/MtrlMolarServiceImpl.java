package com.pcitc.fms.bll.itf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.dao.MtrlMolarDao;
import com.pcitc.fms.dal.pojo.CommunityArea;
import com.pcitc.fms.bll.entity.MtrlMolar;
import com.pcitc.fms.bll.itf.MtrlMolarService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.MtrlMolarDegree;
import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.exception.BusiException;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class MtrlMolarServiceImpl implements MtrlMolarService{
	
	@Autowired
	private MtrlMolarDao mtrlMolarDao;

	@Override
	public Pager<MtrlMolar> getMtrlMolars(com.pcitc.fms.service.model.MtrlMolar mtrlMolar, Pageable pageable)
			throws Exception {

		Pager<MtrlMolar> pageData = new Pager<>();
		MyPageImpl properties = null;
		List<MtrlMolar> EntityList = new ArrayList<>();
		try {
			properties = mtrlMolarDao.findMtrlMolars(mtrlMolar, pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(), MtrlMolar.class);
		} catch (Exception e) {
			throw new BusiException("", e.getMessage());
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

	@Override
	public List getMtrlMolarDegree(MtrlMolarDegree model) {
		List<MtrlMolarDegree> GlbCubaDegreeList =new ArrayList<MtrlMolarDegree>();
		
		List<Object> list=new ArrayList<Object>();
		
		list=mtrlMolarDao.findMtrlMolarDegrees();
		for (Object object : list) {
			Object[] obj=(Object[]) object;
			MtrlMolarDegree mtrlMolarDegree = new MtrlMolarDegree();
			BigDecimal big=(BigDecimal) obj[0];
			BigDecimal big1=(BigDecimal) obj[1];
			BigDecimal big2=(BigDecimal) obj[2];
			BigDecimal big3=(BigDecimal) obj[3];
			String str=(String) obj[4];
			String str1=(String) obj[5];
			mtrlMolarDegree.setAmount(big.longValue());
			mtrlMolarDegree.setInterpolateV(big1.doubleValue());
			mtrlMolarDegree.setDegree(big2.longValue());
			mtrlMolarDegree.setMtrlId(big3.longValue());
			mtrlMolarDegree.setMtrlCode(str);
			mtrlMolarDegree.setMtrlName(str1);
			GlbCubaDegreeList.add(mtrlMolarDegree);
		}
		
		return GlbCubaDegreeList;
	}

}
