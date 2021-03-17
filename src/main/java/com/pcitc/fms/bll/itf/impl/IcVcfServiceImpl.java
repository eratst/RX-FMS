package com.pcitc.fms.bll.itf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.IcVcf;
import com.pcitc.fms.bll.entity.VcfDegree;
import com.pcitc.fms.bll.itf.IcVcfService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.common.RentCondition;
import com.pcitc.fms.dal.dao.IcVcfDao;
import com.pcitc.fms.dal.pojo.CommunityArea;
import com.pcitc.fms.service.model.DataDegree;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class IcVcfServiceImpl implements IcVcfService{

	@Autowired
	private IcVcfDao icVcfDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<IcVcf> findIcVcf(com.pcitc.fms.service.model.IcVcf icVcf, Pageable pageable) throws Exception {
		Pager<IcVcf> pageData = new Pager<>();
		MyPageImpl IcVcf_pojo = icVcfDao.findVcfs(icVcf, pageable);
		List<IcVcf> targets = ObjectConverter.listConverter(IcVcf_pojo.getContent(), IcVcf.class);
		pageData.setContent(targets);
		pageData.setFirst(IcVcf_pojo.isFirst());
		pageData.setLast(IcVcf_pojo.isLast());
		pageData.setNumber(IcVcf_pojo.getNumber());
		pageData.setNumberOfElements(IcVcf_pojo.getNumberOfElements());
		pageData.setSize(IcVcf_pojo.getSize());
		pageData.setSort(IcVcf_pojo.getSort());
		pageData.setTotalElements(IcVcf_pojo.getCount());
		pageData.setTotalPages(IcVcf_pojo.getTotalPages());
		return pageData;
	}

	@Override
	public List getVcfDegree(com.pcitc.fms.service.model.VcfDegree model) {
		List<VcfDegree> VcfDegreeList =new ArrayList<VcfDegree>();
		
		List<Object> list=new ArrayList<Object>();
		
		list=icVcfDao.findVcfDegrees();
		
		for (Object object : list) {
			Object[] obj=(Object[]) object;
			VcfDegree vcfDegree = new VcfDegree();
			BigDecimal big=(BigDecimal) obj[0];
			BigDecimal big1=(BigDecimal) obj[1];
			BigDecimal big2=(BigDecimal) obj[2];
			String str=(String) obj[3];
			String str1=(String) obj[4];
			vcfDegree.setAmount(big.longValue());
			vcfDegree.setInterpolateV(big1.doubleValue());
			vcfDegree.setMtrlTypeId(big2.longValue());
			vcfDegree.setMtrlTypeCode(str);
			vcfDegree.setMtrlTypeName(str1);
			VcfDegreeList.add(vcfDegree);
		}
		
		return VcfDegreeList;
	}

	

}
