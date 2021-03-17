package com.pcitc.fms.bll.itf;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.User;
import com.pcitc.fms.service.model.Pager;

public interface UserService {
	
	Pager<User> getUsers(com.pcitc.fms.service.model.User user, Pageable pageable) throws Exception;

}
