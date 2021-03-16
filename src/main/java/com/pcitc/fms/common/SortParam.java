package com.pcitc.fms.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.pcitc.fms.common.annotation.SpecialResource;
import com.pcitc.imp.common.exception.BusiException;

public class SortParam {
	public static <T> String getSortParam(Class<T> clazz, String orderby) throws BusiException {
		StringBuilder sb = new StringBuilder();
		List<String> orderLists = new ArrayList<>();
		if (StringUtils.isNotEmpty(orderby)){
			orderLists = Arrays.asList(orderby.split(","));
			sb.append(" order by ");
		}
		Field[] fields = clazz.getDeclaredFields();
		
		for (String orderList : orderLists) {
			String[] orderArr = orderList.split(" ");
			checkSpecial(orderList,fields);
			for (Field field : fields) {
				field.setAccessible(true);
				// 判断属性上面是否加了SpecialResource注解，并且获得SpecialResource对象
				boolean resourceMemberFlag = field.isAnnotationPresent(SpecialResource.class);
				SpecialResource resourceMember = field.getAnnotation(SpecialResource.class);
				if(resourceMemberFlag==true) {
					if (field.getName().equals(orderArr[0])) {
						sb.append(resourceMember.name()+" "+orderArr[1]+",");
					}
				}
			}
		}
		
		if(sb.toString().equals(" order by ")){
			return " ";
		} else {
			String value =  sb.toString().substring(0, sb.toString().lastIndexOf(","));
			return value;
		}
	}
	
	private static void checkSpecial(String order, Field[] fields)
			throws BusiException {
		boolean flag = false;
		String[] strArr = order.split(" ");
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getName().equals(strArr[0])) {
				flag = true;
				break;
			}
		}
		if (strArr.length == 2) {
			if (strArr[1].trim().equals("desc") || strArr[1].trim().equals("asc")) {
				if (flag == false) {
					throw new BusiException("", "排序条件" + order + "有误");
				}
			} else {
				throw new BusiException("", "排序条件" + order + "有误");
			}
		} else {
			throw new BusiException("", "排序条件" + order + "有误");
		}
	}
}
