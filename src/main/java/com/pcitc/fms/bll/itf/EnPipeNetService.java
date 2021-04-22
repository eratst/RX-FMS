package com.pcitc.fms.bll.itf;

import com.pcitc.fms.dal.pojo.EnPipeNet;
import com.pcitc.fms.service.model.Pager;
import org.springframework.data.domain.Pageable;

public interface EnPipeNetService {

    Pager<EnPipeNet> getEnPipeNets(com.pcitc.fms.service.model.EnPipeNet enPipeNet, Pageable pageable) throws Exception;

}
