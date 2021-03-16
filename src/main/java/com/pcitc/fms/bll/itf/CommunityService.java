package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Community;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.fms.service.model.Pager;


public  interface CommunityService {

	
	

 public Pager<Community> getPageCommunities(com.pcitc.fms.service.model.Community modelStr, Pageable pageable)
			throws BusinessException;

}



















