package com.pcitc.fms.common;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pcitc.fms.service.handler.BaseHandler;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class JsonTool {
	private static Gson gson = new Gson();

	// String转bean
	public static <T extends BaseResRep> T stringToBean(String str, Class<T> classT) {
		T tt = gson.fromJson(str, classT);
		return tt;
	}

	// String转List<JavaBean>
	public static <T extends BaseResRep> List<T> stringToListBean(String str, Class<T> classT) {
		List<T> beanList = gson.fromJson(str, new TypeToken<List<T>>() {
		}.getType());
		return beanList;
	}

	// String转Map<String,Object>
	public static <T extends BaseResRep> Map<String, T> StringToMap(String str, Class<T> classT) {
		Map<String, T> map = gson.fromJson(str, new TypeToken<Map<String, T>>() {
		}.getType());
		return map;
	}

	// Object转json
	public static String ObjectToString(Object object) {
		String str = gson.toJson(object);
		return str;
	}

	// String转Map<String,List<Object>>
	public static <T extends BaseResRep> Map<String, List<T>> StringToMapList(String str, Class<T> classT) {
		Map<String, List<T>> map = gson.fromJson(str, new TypeToken<Map<String, List<T>>>() {
		}.getType());
		return map;
	}

	// String转List<Object>
	public static List<String> StringToStringtList(String str) {
		List<String> strList = gson.fromJson(str, new TypeToken<List<String>>() {
		}.getType());
		return strList;
	}

	// String转List<Object>
	public static List<Double> StringToDoubleList(String str) {
		List<Double> strList = gson.fromJson(str, new TypeToken<List<Double>>() {
		}.getType());
		return strList;
	}

	// String转List<Object>
	public static List<Integer> StringToIntegerList(String str) {
		List<Integer> strList = gson.fromJson(str, new TypeToken<List<Integer>>() {
		}.getType());
		return strList;
	}

}
