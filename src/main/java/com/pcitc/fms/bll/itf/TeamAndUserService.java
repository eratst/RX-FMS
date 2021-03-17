package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.TeamAndUser;
import com.pcitc.fms.service.model.Pager;

@Service
public interface TeamAndUserService {
	
	public Pager<TeamAndUser> getAll(com.pcitc.fms.service.model.TeamAndUser teamAndUser,Pageable pageable) throws Exception;

}
