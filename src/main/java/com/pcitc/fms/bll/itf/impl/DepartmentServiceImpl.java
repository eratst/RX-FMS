/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: DepartmentServiceImpl
 * Date:18-3-8 下午6:21
 * Author: zhaozhenqiang
 */

package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.bll.entity.Department;
import com.pcitc.fms.bll.itf.DepartmentService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.DepartmentDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: DepartmentServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	/**
	 * The Department dao.
	 */
	@Autowired
	private  DepartmentDao departmentDao;
	/**
	 * The T pm org dao.
	 */
	@Autowired
	private TPmOrgDao tPmOrgDao;
	/**
	 * The Db primary id dao.
	 */
	@Autowired
	private DbPrimaryIdDao dbPrimaryIdDao;
	/**
	 * The Bizorgdtldao.
	 */
	@Autowired
	private BizOrgDTLDao bizorgdtldao;

	/**
	 * Gets departments.
	 *
	 * @param departmentModel the department model
	 * @param pageable the pageable
	 * @return the departments
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Pager<Department> getDepartments(com.pcitc.fms.service.model.Department departmentModel,Pageable pageable) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.Department>  pageData = new Pager<>();
		MyPageImpl properties = null;
		List<Department> EntityList = null;
		try {
			properties = departmentDao.findDepartments(departmentModel,pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(),Department.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw  new BusinessException("","",e.getCause().getMessage());
			}
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
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


	/**
	 * Gets department by code.
	 *
	 * @param orgCode the org code
	 * @return the department by code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Department getDepartmentByCode(String orgCode)throws BusinessException {
		com.pcitc.fms.dal.pojo.Department department =  departmentDao.getDepartmentByOrgCode(orgCode);
			if(null == department){
					 throw new BusinessException("",orgCode,":不存在该组织机构编码");
			}
		try {
			return ObjectConverter.entityConverter(department, Department.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

}
