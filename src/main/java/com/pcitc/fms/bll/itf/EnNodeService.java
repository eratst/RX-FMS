package com.pcitc.fms.bll.itf;
import org.springframework.data.domain.Pageable;

import com.pcitc.fms.dal.pojo.EnNode;
import com.pcitc.fms.service.model.Pager;

public interface EnNodeService {

	Pager<EnNode> getEnNodes(com.pcitc.fms.service.model.EnNode enNode, Pageable pageable) throws Exception;

}
