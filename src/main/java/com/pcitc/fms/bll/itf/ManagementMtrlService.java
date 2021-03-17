package com.pcitc.fms.bll.itf;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.ManagementMtrl;
import com.pcitc.fms.service.model.Pager;

import io.vertx.core.http.HttpServerRequest;

@Service
public interface ManagementMtrlService {
	public Pager<ManagementMtrl> getPageManagementMtrl(String uriStr,com.pcitc.fms.service.model.ManagementMtrl managementMtrl, Pageable pageable)throws Exception;
	
	public List<Object> getManagementMtrls() throws Exception;
}
