package com.pcitc.fms.bll.itf;

import java.util.List;

import com.alibaba.fastjson.JSONArray;


public interface RESTClientService {

	/**
	 * 根据URL发起get请求，获得泛型指定的类构成的List。
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public List<Object> getList(String url) throws Exception;
	
	/**
	 * 根据URL发起get请求，获得泛型指定的类构成的List。
	 * @param url
	 * @return
	 * @throws Exception 
	 */
	public List<Object> getList(StringBuffer url) throws Exception;

	/**
	 * 根据URL返回单个对象示例。
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Object getOne(String url) throws Exception;
	
	/**
	 * 根据URL返回单个对象示例。
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Object getOne(StringBuffer url) throws Exception;

	/**
	 * 根据URL返回JSON array。
	 * @param urlObject
	 * @return
	 * @throws Exception
	 */
	public JSONArray getJSONArray(StringBuffer url) throws Exception;
}
