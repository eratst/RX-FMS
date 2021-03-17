package com.pcitc.fms.bll.itf.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.Temppreden;
import com.pcitc.fms.bll.itf.TemppredenService;
import com.pcitc.fms.dal.dao.TemppredenDao;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;
@Service
public class TemppredenServiceImpl implements TemppredenService{

	@Autowired
	private TemppredenDao temppredenDao;
	
	@Override
	public Pager<Temppreden> findTemppredens(String temp, Pageable pageable) throws Exception {
		Specification<com.pcitc.fms.dal.pojo.Temppreden> specification = new Specification<com.pcitc.fms.dal.pojo.Temppreden>() {

			@Override
			public Predicate toPredicate(Root<com.pcitc.fms.dal.pojo.Temppreden> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<Predicate>();

				if (temp != null && !temp.equals("")) {
					Predicate predicate = cb.equal(root.get("temp"), Double.valueOf((temp)));
					list.add(predicate);
				}
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		Pager<Temppreden> pageData = new Pager<>();
		Page<com.pcitc.fms.dal.pojo.Temppreden> properties = temppredenDao.findAll(specification, pageable);
		List<Temppreden> entityTemppredens = ObjectConverter.listConverter(properties.getContent(), Temppreden.class);
		pageData.setContent(entityTemppredens);
		pageData.setFirst(properties.isFirst());
		pageData.setLast(properties.isLast());
		pageData.setNumber(properties.getNumber());
		pageData.setNumberOfElements(properties.getNumberOfElements());
		pageData.setSize(properties.getSize());
		pageData.setSort(properties.getSort());
		pageData.setTotalElements(properties.getTotalElements());
		pageData.setTotalPages(properties.getTotalPages());
		return pageData;
	}

}
