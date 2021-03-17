package com.pcitc.fms.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.pcitc.fms.service.model.Pager;
import com.pcitc.imp.common.model.Pagination;


public class PageUtil {

	/**
	 * 分页组装
	 * 
	 * @param top
	 *            显示条数
	 * @param skip
	 *            跳过条数
	 * @param sort
	 *            排序
	 * @return
	 */
	public static Pageable pageable(Integer top, Integer skip, Sort sort) {
		Integer pageSize = (top == null ? 10 : top);
		Integer pageIndex = skip / pageSize;
		return new PageRequest(pageIndex, pageSize, sort);
	}

	/**
	 * 合并返回Hander对象
	 * 
	 * @param pager
	 *            分页对象
	 * @return
	 */
	public static <T> Map<String, String> mergeHander(Pager<T> pager) {
		Map<String, String> handerMap = new HashMap<String, String>();
		handerMap.put("recordCount", pager.getTotalElements().toString());
		handerMap.put("first", pager.getFirst().toString());
		handerMap.put("last", pager.getLast().toString());
		handerMap.put("number", pager.getNumber().toString());
		handerMap.put("numberOfElements", pager.getNumberOfElements().toString());
		handerMap.put("size", pager.getSize().toString());
		handerMap.put("sort", pager.getSort().toString());
		handerMap.put("totalElements", pager.getTotalElements().toString());
		handerMap.put("totalPages", pager.getTotalPages().toString());
		handerMap.put("logs", pager.getLogs());
		return handerMap;
	}

	/**
	 * 合并分页对象
	 * 
	 * @param pagination
	 * @param pager
	 */
	public static <T> void mergePage(Pagination pagination, Pager<T> pager) {
		if (pager != null) {
			pagination.setRecordCount(pager.getTotalElements());
			
		}
	}
	
		public static <T> void mergePageTest(PageReturn pagination, Pager<T> pager) {
			if (pager != null) {
				pagination.setCount(pager.getTotalElements());
				pagination.setLogs(pager.getLogs());
			}
		}
	public static String likeParam(String param) {
		return "%" + param + "%";
	}
}
