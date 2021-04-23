package com.pcitc.fms.bll.itf;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.dal.pojo.EnNodeType;
import com.pcitc.fms.service.model.Pager;

@Service
public interface EnNodeTypeService {

    Pager<EnNodeType> getEnNodeTypes(com.pcitc.fms.service.model.EnNodeType enNodeType, Pageable pageable) throws Exception;
    
}

