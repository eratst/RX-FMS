package com.pcitc.fms.bll.itf.impl;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.User;
import com.pcitc.fms.bll.itf.UserService;
import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.dal.dao.BizOrgDTLDao;
import com.pcitc.fms.dal.dao.TPmOrgDao;
import com.pcitc.fms.dal.dao.UserDao;
import com.pcitc.fms.dal.pojo.TPmBizOrgDTL;
import com.pcitc.fms.dal.pojo.TPmOrg;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@SuppressWarnings({ "unchecked", "unused" })
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BizOrgDTLDao bizOrgDTLDao;
	
	@Autowired
	private TPmOrgDao orgDao;
	
	@Override
	public Pager<User> getUsers(com.pcitc.fms.service.model.User user, Pageable pageable) throws Exception {
		Pager<User> pageData = new Pager<>();
		MyPageImpl page=null;
		try{
			if(StringUtils.isNotEmpty(user.getOperType()) && user.getOperType().equals("children")){
				if(StringUtils.isEmpty(user.getOrgCode())){
					throw new BusinessException("", "请传入组织机构编码！");
				}
				//找上级组织机构
				Set<String> orgCodeSet=new HashSet<String>();
				TPmOrg tPmOrg = orgDao.getTPmOrg(user.getOrgCode());
				List<TPmBizOrgDTL> orgDtlList = bizOrgDTLDao.findBizOrgDTLByOrgId(tPmOrg.getOrgId());
				for(TPmBizOrgDTL orgDtl:orgDtlList){
					 List<String> allChildrenOrgCode=new ArrayList<String>();
					 TPmOrg org = orgDao.getTPmOrgByOrgId(orgDtl.getParentOrgId());
					 //找子组织机构
					 allChildrenOrgCode = bizOrgDTLDao.getAllChildrenOrgCodesNotLevel(org.getOrgCode());
					 orgCodeSet.addAll(allChildrenOrgCode);
					 orgCodeSet.remove(org.getOrgCode());
				}
				user.setOrgCodes(new ArrayList<String>(orgCodeSet));
				user.setOrgCode(null);
				page = userDao.findUsers(user, pageable);
			}else{
				page = userDao.findUsers(user, pageable);
			}
		} catch (Exception e) {
			if (e instanceof UndeclaredThrowableException) {
				throw new BusinessException("", e.getCause().getMessage());
			}
		}
		List<User> entityList = ObjectConverter.listConverter(page.getContent(), User.class);
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
