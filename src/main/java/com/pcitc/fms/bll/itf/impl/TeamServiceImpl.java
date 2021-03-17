/*
 * Copyright (c) 2018. PCITC. All rights reserved.
 * 项目名称:  NewFactoryModel
 * 文件名称: TeamServiceImpl
 * Date:18-3-8 下午6:29
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

import com.pcitc.fms.bll.entity.Team;
import com.pcitc.fms.bll.itf.TeamService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.DbPrimaryIdDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.dao.TeamDao;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

/**
 * Title: TeamServiceImpl Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年11月21日
 */
@Service
public class TeamServiceImpl implements TeamService {

	/**
	 * The constant log.
	 */
	private static Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);
	/**
	 * The Team dao.
	 */
	@Autowired
	private  TeamDao teamDao;
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
	 * Gets teams.
	 *
	 * @param teamModel the team model
	 * @param pageable the pageable
	 * @return the teams
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Pager<Team> getTeams(com.pcitc.fms.service.model.Team teamModel,Pageable pageable) throws BusinessException {
		Pager<com.pcitc.fms.bll.entity.Team>  pageData = new Pager<>();
		MyPageImpl properties=null;
		List<Team> EntityList = null;
		try {
			properties = teamDao.findTeams(teamModel,pageable);
			EntityList = ObjectConverter.listConverter(properties.getContent(),Team.class);
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}else{
				throw new BusinessException("", e.getMessage());
			}
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
	 * Gets team by code.
	 *
	 * @param orgCode the org code
	 * @return the team by code
	 * @throws BusinessException the business exception
	 */
	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public Team getTeamByCode(String orgCode)throws BusinessException {
		com.pcitc.fms.dal.pojo.Team team =  teamDao.getTeamByOrgCode(orgCode);
			if(null == team){
					 throw new BusinessException("",orgCode,":不存在该组织机构编码");
			}
		try {
			return ObjectConverter.entityConverter(team, Team.class);
		} catch (Exception e) {
			log.error("fail",e);
			throw  new BusinessException("","",e.getMessage());
		}
	}

}
