package com.pcitc.fms.service.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;

/**
 * 分页基础类
 * 
 * @author sleep
 *
 */
public class Pager<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 内容数据
	 */
	private List<T> content;
	/**
	 * 是否最后一页
	 */
	private Boolean last;
	/**
	 * 总页数
	 */
	private Integer totalPages;
	/**
	 * 总个数
	 */
	private Long totalElements;
	/**
	 * 每页个数
	 */
	private Integer size;
	/**
	 * 当前页码
	 */
	private Integer number;
	/**
	 * 是否首页
	 */
	private Boolean first;
	/**
	 * 排序
	 */
	private Sort sort;
	/**
	 * 当前页总个数
	 */
	private Integer numberOfElements;
	
	private String logs;
	
	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Boolean getLast() {
		return last;
	}

	public void setLast(Boolean last) {
		this.last = last;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getFirst() {
		return first;
	}

	public void setFirst(Boolean first) {
		this.first = first;
	}

	public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public Integer getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(Integer numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

}
