package com.pcitc.fms.dal.dao;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.fms.common.MyPageImpl;
import com.pcitc.fms.config.AreaNodeBasicSql;
import com.pcitc.fms.dal.pojo.Plate;

 /**
 * Title: PlateDao
 * Description:TODO
 * @author zhenqiang.zhao
 * @date 2017年6月16日
 * @version 1.0
 */
public interface PlateDao extends JpaSpecificationExecutor<com.pcitc.fms.dal.pojo.Plate>,
JpaRepository<com.pcitc.fms.dal.pojo.Plate, Integer>{

//	/**   
//	 * @Title: getPlates   
//	 * @Description: TODO  
//	 * @param palteModel
//	 * @return
//	 * @date 2017年6月16日      
//	 * @return: List<com.pcitc.fms.dal.pojo.Plate>
//	 * @author zhenqiang.zhao      
//	 */
	@Transactional(rollbackFor = BusinessException.class)
	MyPageImpl getPlates(com.pcitc.fms.service.model.Plate palteModel,Pageable pageable);
}
