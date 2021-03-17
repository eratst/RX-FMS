package com.pcitc.fms.bll.itf;

import com.pcitc.fms.exception.BusinessException;
import java.util.List;

import org.springframework.data.domain.Pageable;

import com.pcitc.fms.bll.entity.Department;
import com.pcitc.fms.service.model.Pager;

 /**
 * Title: DepartmentService
* Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年11月21日
 * @version 1.0
 */
public interface DepartmentService {

	 public Pager<Department> getDepartments(com.pcitc.fms.service.model.Department Department, Pageable pageable)throws BusinessException;

	 public Department getDepartmentByCode(String departmentCode)throws BusinessException;

}
