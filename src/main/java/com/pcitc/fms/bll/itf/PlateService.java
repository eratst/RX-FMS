package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcitc.fms.bll.entity.NodeDictionary;
import com.pcitc.fms.bll.entity.Plate;
import com.pcitc.fms.service.model.Pager;

/**
 * Title: PlateService Description:TODO
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年6月16日
 */
@Service
public interface PlateService {


	/**
	 * Gets plates.
	 *
	 * @param palteModel the palte model
	 * @param pageable the pageable
	 * @param factoryCode the factory code
	 * @return plates
	 * @throws BusinessException the BusinessException
	 * @Title: getPlates
	 * @Description: TODO
	 * @date 2017年6月16日
	 * @return: List<com.pcitc.fms.bll.entity.Plate>
	 * @author zhenqiang.zhao
	 */
	public Pager<com.pcitc.fms.bll.entity.Plate> getPlates(com.pcitc.fms.service.model.Plate palteModel, Pageable pageable) throws BusinessException;


}
