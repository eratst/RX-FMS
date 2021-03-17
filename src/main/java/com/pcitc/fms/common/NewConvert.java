package com.pcitc.fms.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;

public class NewConvert {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object convert(Object sourceObject, Class targetClass) throws Exception {
		List source = (List) sourceObject;
		List targetList = new ArrayList<>();
	
		for (int i = 0; i < source.size(); i++) {
			
			Object targetObject = targetClass.newInstance();
			
			BeanUtils.copyProperties(targetObject, source.get(i));
			
			//自身属性
			recursion(targetObject, source.get(i));
			
			//父类属性
			superRecursion(targetObject, source.get(i));
			
			targetList.add(i, targetObject);
		}
		
		return targetList;
	}

	private static Object superRecursion(Object targetObject, Object sourceObject)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Field[] supperFields=null;
		if (sourceObject.getClass().getSuperclass()!=null) {
			try {
				supperFields = sourceObject.getClass().getSuperclass().getDeclaredFields();
				Object getSuperValue=null;
				for (Field supperField : supperFields) {
					boolean supperFieldAnnotationPresent = supperField.isAnnotationPresent(CheckField.class);
					CheckField supperFieldAnnotation = supperField.getAnnotation(CheckField.class);
					if (supperFieldAnnotationPresent==true) {
						if (supperFieldAnnotation.CheckName().equals(CheckNameType.OBJECTVALUE)) {
							
							supperField.setAccessible(true);
							
							getSuperValue =supperField.get(sourceObject);
							
							BeanUtils.copyProperties(targetObject, getSuperValue);
							
							Object superObj = recursion(targetObject,getSuperValue);
							
							superRecursion(targetObject,superObj);
						}
					} 
				}
				return getSuperValue;
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			return sourceObject;
		}
		
		return null;
		
	}

	private static Object recursion(Object targetObject, Object sourceObject)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Field[] myFields = sourceObject.getClass().getDeclaredFields();
		Object getValue=null;
		for (Field myField : myFields) {
			boolean myFieldAnnotationPresent = myField.isAnnotationPresent(CheckField.class);
			CheckField myFieldAnnotation = myField.getAnnotation(CheckField.class);
			if (myFieldAnnotationPresent == true) {
				if (myFieldAnnotation.CheckName().equals(CheckNameType.OBJECTVALUE)) {
					String getName="get"+myField.getName().substring(0, 1).toUpperCase()+myField.getName().substring(1, myField.getName().length());
					getValue = sourceObject.getClass().getDeclaredMethod(getName, (Class<?>[]) null)
							.invoke(sourceObject, (Object[]) null);
					BeanUtils.copyProperties(targetObject, getValue);
					recursion(targetObject,getValue);
				}
			}
		}
		return getValue;
	}
	
}
