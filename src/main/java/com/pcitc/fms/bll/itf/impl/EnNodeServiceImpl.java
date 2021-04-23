package com.pcitc.fms.bll.itf.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.itf.EnNodeService;
import com.pcitc.fms.dal.dao.EnNodeDao;
import com.pcitc.fms.dal.pojo.EnNode;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@SuppressWarnings("unchecked")
@Service
public class EnNodeServiceImpl implements EnNodeService {

	@Autowired
	private EnNodeDao enNodeRepository;
	
	public Pager<EnNode> getEnNodes(com.pcitc.fms.service.model.EnNode enNode, Pageable pageable) throws Exception {
		Pager<EnNode> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.EnNode> page = enNodeRepository.findEnNodes(enNode, pageable);
		List<EnNode> entityList = ObjectConverter.listConverter(page.getContent(), EnNode.class);
		pageData.setContent(entityList);
		pageData.setFirst(page.isFirst());
		pageData.setLast(page.isLast());
		pageData.setNumber(page.getNumber());
		pageData.setNumberOfElements(page.getNumberOfElements());
		pageData.setSize(page.getSize());
		pageData.setSort(page.getSort());
		pageData.setTotalElements(page.getTotalElements());
		pageData.setTotalPages(page.getTotalPages());
		return pageData;
	}

}
