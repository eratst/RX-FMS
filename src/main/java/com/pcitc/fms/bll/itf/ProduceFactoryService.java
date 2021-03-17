package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.ProduceFactory;
import com.pcitc.fms.service.model.Pager;

 /**
 * Title: ProduceFactoryService
* Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
public interface ProduceFactoryService {

	 public Pager<ProduceFactory> getProduceFactories(com.pcitc.fms.service.model.ProduceFactory ProduceFactory, Pageable pageable)throws BusinessException;

	 public ProduceFactory getProduceFactoryByCode(String produceFactoryCode)throws BusinessException;

}
