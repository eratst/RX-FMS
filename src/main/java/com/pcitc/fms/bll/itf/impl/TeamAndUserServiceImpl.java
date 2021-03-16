package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.TeamAndUser;
import com.pcitc.fms.bll.itf.TeamAndUserService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.TeamAndUserDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
@Component
public class TeamAndUserServiceImpl implements TeamAndUserService{
	
	@Autowired
	private TeamAndUserDao teamAndUserDao;

	@Override
	public Pager<TeamAndUser> getAll(com.pcitc.fms.service.model.TeamAndUser teamAndUser, Pageable pageable)
			throws Exception {
		Pager<TeamAndUser> pageData = new Pager<>();
		MyPageImpl properties=null;
		try{
			properties = teamAndUserDao.getTeamAndUsers(teamAndUser,pageable);
			
		}catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}else{
				throw new BusinessException("", e.getMessage());
			}
		}
		List<TeamAndUser> entityList = ObjectConverter.listConverter(properties.getContent(), TeamAndUser.class);
		pageData.setContent(entityList);
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
