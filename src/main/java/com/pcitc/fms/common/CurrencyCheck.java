package com.pcitc.fms.common;

import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.Operation;
import com.pcitc.fms.exception.BusinessException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Title: CurrencyCheck Description: TODO task mark zhenqiang.zhao
 *
 * @author zhenqiang.zhao
 * @version 1.0
 * @date 2017年7月5日
 */
public class CurrencyCheck {

	/**
	 * @Title: checkObject
	 * @Description: add or update
	 * @date 2017年7月5日
	 * @return: List
	 * @author zhenqiang.zhao
	 */
	public static List checkObject(Object list, Operation add) throws Exception {
		boolean len = true;
		List objList = (List) list;
		int count = 0;
		Map<Integer, Object> map = new ConcurrentHashMap();
		for (Object obj : objList) {
			List<String> errList = new ArrayList<>();
			Field[] fields = obj.getClass().getDeclaredFields(); // getDeclaredFields被声明字段
																	// ,
																	// 标识类或接口中声明的成员集。不包括继承的成员
			String message = null;
			for (Field field : fields) {
				field.setAccessible(true);// set访问权限
				boolean annotationPresent = field.isAnnotationPresent(CheckField.class);
				if (annotationPresent == true) {
					CheckField annotation = field.getAnnotation(CheckField.class);
					String name = field.getName();
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					String type = field.getGenericType().getTypeName();
					Method m = obj.getClass().getMethod("get" + name);
					Object value = null;
					if ("java.lang.String".equals(type)) {
						value = (String) m.invoke(obj) == null ? null : (String) m.invoke(obj); // invoke
																								// 调用由这个{@
																								// code方法}对象表示的底层方法
																								// update
																								// by
																								// zhaozhenqiang
						if (null != value) {
							field.set(obj, ((String) value).trim());// set
																	// 设置由这个{@
																	// code
																	// field}对象表示的字段，以指定的对象参数为指定的新值。
																	// update by
																	// zhaozhenqiang。
						}
					}
					if ("java.util.Date".equals(type)) {
						value = (java.util.Date) m.invoke(obj);
					}

					if ("java.lang.Integer".equals(type)) {
						value = (java.lang.Integer) m.invoke(obj);
						if (null != value) {
							field.set(obj, value);
							// field.set(obj,
							// Integer.parseInt(value.toString().trim()));
						}
					}

					if (annotation.CheckName().equals(CheckNameType.CODE)) {//validateCodeException
						BusinessException exception = CheckUtil.validateCodeForRentException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.RENTCODE)) {
						BusinessException exception = CheckUtil.validateCodeForRentException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.NAME)) {//validateNameException
						BusinessException exception = CheckUtil.validateCodeForRentException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.NAMEMAYBENULL)) {
						BusinessException exception = CheckUtil.validateNameStrMayBeNullException(name,
								(String) field.get(obj), annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}

					if (annotation.CheckName().equals(CheckNameType.IDMAYBENULL)) {
						BusinessException exception = CheckUtil.validateintMaybeNullException(name, (Integer) value);
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.DES)) {
						BusinessException exception1 = null;
						BusinessException exception = null;
						if (value != null) {
							String des = (String) value.toString();
							if (des.contains("<") || des.contains(">")) {
								exception1 = new BusinessException("", "des不能含有特殊字符");
							} 
						}
						exception = CheckUtil.validateLengthException(name, (String) value,
								annotation.StrLength());
						
//						BusinessException exception = CheckUtil.validateNameStrMayBeNullException(name,
//								(String) field.get(obj), annotation.StrLength());
						
						if (null != exception1) {
							errList.add(exception1.getMessage());
						} 
						if  (null != exception) {
							errList.add(exception.getMessage());
						}
					}

					if (annotation.CheckName().equals(CheckNameType.ENABLED)) {
						try {
							if (value == null) {
//								field.set(obj, 1);
								errList.add(
										new BusinessException("", "", name + ": 不能为空！ ").getMessage());
							} else {
								Integer intValue = (Integer) value;
								
								if (intValue != 0 && intValue != 1) {
									errList.add(
											new BusinessException("", "", name + ":" + CheckError.FLAG_ERR).getMessage());
								}
							}
							
						} catch (Exception e) {
							errList.add(new BusinessException("", "", name + ":" + CheckError.FLAG_ERR).getMessage());
						}

					}
					// if (annotation.CheckName().equals(CheckNameType.ENABLED))
					// {
					// if (add.equals(Operation.UPDATE) && (null == (Integer)
					// value || ""
					// .equals((Integer) value))) {
					// errList.add(
					// new BusinessException("", "", name + ":" +
					// CheckError.FLAG_ERR).getMessage());
					// }
					// BusinessException exception =
					// CheckUtil.validateEnabled(name, (Integer) value);
					// if (null != exception) {
					// errList.add(exception.getMessage());
					// } else if (add.equals(Operation.ADD)) {
					// field.set(obj, value == null ? 1 : (Integer) value);
					// }
					// }
					if (annotation.CheckName().equals(CheckNameType.ID)) {
						try {
							BusinessException exception = CheckUtil.validateIdException(name, (Integer) value);
							if (null != exception) {
								errList.add(exception.getMessage());
							}
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to")) {
								errList.add(name + ":" + CheckError.CAST_ERROR);
							}
						}
					}
					if (annotation.CheckName().equals(CheckNameType.IDTYPE)) {
						try {
							BusinessException exception = CheckUtil.validateIdExceptions(name, (Integer) value);
							if (null != exception) {
								errList.add(exception.getMessage());
							}
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to")) {
								errList.add(name + ":" + CheckError.CAST_ERROR);
							}
						}
					}

					if (annotation.CheckName().equals(CheckNameType.NEGATIVEID)) {
						try {
							BusinessException exception = CheckUtil.validateNegativeIdException(name, (Integer) value);
							if (null != exception) {
								errList.add(exception.getMessage());
							}
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to")) {
								errList.add(name + ":" + CheckError.CAST_ERROR);
							}
						}
					}
					if (annotation.CheckName().equals(CheckNameType.CREATETIME) && add.equals(Operation.ADD)) {
						field.set(obj, new Date());
					}

					if (annotation.CheckName().equals(CheckNameType.MAINTAINTIME) && add.equals(Operation.UPDATE)) {
						field.set(obj, new Date());
						// BusinessException exception =
						// CheckUtil.checkTimeStamp(value == null? null :
						// (Date)value ,"yyyyMMddHHmmss");
						// if (null == exception) {
						// if (null != value) {
						// field.set(obj, value);
						// }else {
						// field.set(obj, new Date());
						// }
						// }
						// if (null != exception) {
						// errList.add(exception.getMessage());
						// }
					}
					// CREATOR 新增不可为空
					if (annotation.CheckName().equals(CheckNameType.CREATOR) && add.equals(Operation.ADD)) {
						BusinessException exception = CheckUtil.validateNameException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.SOURCEDATATYPE) && add.equals(Operation.ADD)) {
						BusinessException exception = CheckUtil.validateNameException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					// CREATOR 更新时只作校验，不取值
					if (annotation.CheckName().equals(CheckNameType.CREATOR) && add.equals(Operation.UPDATE)) {
						BusinessException exception = CheckUtil.validateNameStrMayBeNullException(name,
								(String) field.get(obj), annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}

					// EDITOR 新增时可以为空
					if (annotation.CheckName().equals(CheckNameType.EDITOR) && add.equals(Operation.ADD)) {
						BusinessException exception = CheckUtil.validateNameStrMayBeNullException(name,
								(String) field.get(obj), annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					// EDITOR 更新时可以不为空
					if (annotation.CheckName().equals(CheckNameType.EDITOR) && add.equals(Operation.UPDATE)) {
						BusinessException exception = CheckUtil.validateNameStrMayBeNullException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					// CREATORID 只有新增时校验
					if (annotation.CheckName().equals(CheckNameType.CREATORID) && add.equals(Operation.ADD)) {
						try {
							BusinessException exception = CheckUtil.validateIdException(name, (Integer) value);
							if (null != exception) {
								errList.add(exception.getMessage());
							}
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to"))
								errList.add(name + ":" + CheckError.CAST_ERROR);
						}
					}
					// CREATORID 更新时只作校验不取值
					if (annotation.CheckName().equals(CheckNameType.CREATORID) && add.equals(Operation.UPDATE)) {
						try {
							BusinessException exception = CheckUtil.validateintMaybeNullException(name,
									(Integer) value);
							if (null != exception) {
								errList.add(exception.getMessage());
							}
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to"))
								errList.add(name + ":" + CheckError.CAST_ERROR);
						}
					}
					// EDITOR_ID 新增时可以为空
					if (annotation.CheckName().equals(CheckNameType.EDITORID) && add.equals(Operation.ADD)) {
						BusinessException exception = CheckUtil.validateintMaybeNullException(name, (Integer) value);
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					// //EDITOR_ID 修改时不能为空
					if (annotation.CheckName().equals(CheckNameType.EDITORID) && add.equals(Operation.UPDATE)) {
						BusinessException exception = CheckUtil.validateIdException(name, (Integer) value);
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					// SORTNUM 排序 必填非负数
					if (annotation.CheckName().equals(CheckNameType.SORTNUM)) {
						BusinessException exception = CheckUtil.validateNegativeIdException(name, (Integer) value);
						if (exception != null) {
							errList.add(exception.getMessage());
						}
					}

				}
			}
			count++;
			if (errList.size() > 0) {
				map.put(count, errList);
			}

		}

		StringBuffer sb = new StringBuffer();
		for (Map.Entry<Integer, Object> entry : map.entrySet()) {
			if (null != entry.getValue() && null != entry.getValue() && !entry.getValue().equals("")) {
				sb.append("第" + entry.getKey() + "条:" + entry.getValue());
				sb.append(System.getProperty("line.separator"));
			}
		}
		if (!"".equals(sb.toString())) {
			throw new BusinessException("", "", sb.toString());
		}
		return objList;
	}

	// Long型检验方法
	public static List checkObjectInLong(Object list, Operation add) throws Exception {
		boolean len = true;
		List objList = (List) list;
		int count = 0;
		Map<Integer, Object> map = new ConcurrentHashMap();
		for (Object obj : objList) {
			List<String> errList = new ArrayList<>();
			Field[] fields = obj.getClass().getDeclaredFields(); // getDeclaredFields被声明字段
																	// ,
																	// 标识类或接口中声明的成员集。不包括继承的成员
			String message = null;
			for (Field field : fields) {
				field.setAccessible(true);// set访问权限
				boolean annotationPresent = field.isAnnotationPresent(CheckField.class);
				if (annotationPresent == true) {
					CheckField annotation = field.getAnnotation(CheckField.class);
					String name = field.getName();
					name = name.substring(0, 1).toUpperCase() + name.substring(1);
					String type = field.getGenericType().getTypeName();
					Method m = obj.getClass().getMethod("get" + name);
					Object value = null;
					if ("java.lang.String".equals(type)) {
						value = (String) m.invoke(obj) == null ? null : (String) m.invoke(obj); // invoke
																								// 调用由这个{@
																								// code方法}对象表示的底层方法
																								// update
																								// by
																								// zhaozhenqiang
						if (null != value) {
							field.set(obj, ((String) value).trim());// set
																	// 设置由这个{@
																	// code
																	// field}对象表示的字段，以指定的对象参数为指定的新值。
																	// update by
																	// zhaozhenqiang。
						}
					}
					if ("java.util.Date".equals(type)) {
						value = (java.util.Date) m.invoke(obj);
					}

					if ("java.lang.Integer".equals(type)) {
						value = (java.lang.Long) m.invoke(obj);
						if (null != value) {
							field.set(obj, value);
							// field.set(obj,
							// Integer.parseInt(value.toString().trim()));
						}
					}
					if ("java.lang.Long".equals(type)) {
						value = (java.lang.Long) m.invoke(obj);
						if (null != value) {
							field.set(obj, value);
							// field.set(obj,
							// Integer.parseInt(value.toString().trim()));
						}
					}

					if (annotation.CheckName().equals(CheckNameType.CODE)) {
						BusinessException exception = CheckUtil.validateCodeException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.NAME)) {
						BusinessException exception = CheckUtil.validateNameException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.NAMEMAYBENULL)) {
						BusinessException exception = CheckUtil.validateNameStrMayBeNullException(name,
								(String) field.get(obj), annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}

					if (annotation.CheckName().equals(CheckNameType.IDMAYBENULL)) {
						BusinessException exception = CheckUtil.validateintMaybeNullAndLongException(name,
								(Long) value);
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.DES)) {
						BusinessException exception = CheckUtil.validateLengthException(name, (String) value,
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.ENABLED)) {
						if (add.equals(Operation.UPDATE) && (null == (Long) value || "".equals((Long) value))) {
							errList.add(new BusinessException("", "", name + ":" + CheckError.FLAG_ERR).getMessage());
						}
						BusinessException exception = CheckUtil.validateEnabledInLong(name, (Long) value);
						if (null != exception) {
							errList.add(exception.getMessage());
						} else if (add.equals(Operation.ADD)) {
							field.set(obj, value == null ? 1 : (Long) value);
						}
					}

					if (annotation.CheckName().equals(CheckNameType.ID)) {
						try {
							BusinessException exception = CheckUtil.validateintMaybeNullAndLongException(name,
									(Long) value);
							if (null != exception) {
								errList.add(exception.getMessage());
							}
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to"))
								errList.add(name + ":" + CheckError.CAST_ERROR);
						}
					}
					if (annotation.CheckName().equals(CheckNameType.IDTYPE)) {
						try {
							BusinessException exception = CheckUtil.validateIdExceptions(name, (Integer) value);
							if (null != exception) {
								errList.add(exception.getMessage());
							}
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to"))
								errList.add(name + ":" + CheckError.CAST_ERROR);
						}
					}

					if (annotation.CheckName().equals(CheckNameType.NEGATIVEID)) {
						try {
							BusinessException exception = CheckUtil.validateNegativeIdException(name, (Integer) value);
							if (null != exception) {
								errList.add(exception.getMessage());
							}
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to"))
								errList.add(name + ":" + CheckError.CAST_ERROR);
						}
					}
					if (annotation.CheckName().equals(CheckNameType.CREATETIME) && add.equals(Operation.ADD)) {
						field.set(obj, new Date());
					}

					if (annotation.CheckName().equals(CheckNameType.MAINTAINTIME) && add.equals(Operation.UPDATE)) {
						field.set(obj, new Date());
						// BusinessException exception =
						// CheckUtil.checkTimeStamp(value == null? null :
						// (Date)value ,"yyyyMMddHHmmss");
						// if (null == exception) {
						// if (null != value) {
						// field.set(obj, value);
						// }else {
						// field.set(obj, new Date());
						// }
						// }
						// if (null != exception) {
						// errList.add(exception.getMessage());
						// }
					}
					// CREATOR 新增不可为空
					if (annotation.CheckName().equals(CheckNameType.CREATOR) && add.equals(Operation.ADD)) {
						BusinessException exception = CheckUtil.validateNameException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					if (annotation.CheckName().equals(CheckNameType.SOURCEDATATYPE) && add.equals(Operation.ADD)) {
						BusinessException exception = CheckUtil.validateNameException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					// CREATOR 更新时只作校验，不取值
					if (annotation.CheckName().equals(CheckNameType.CREATOR) && add.equals(Operation.UPDATE)) {
						BusinessException exception = CheckUtil.validateNameStrMayBeNullException(name,
								(String) field.get(obj), annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}

					// EDITOR 新增时可以为空
					if (annotation.CheckName().equals(CheckNameType.EDITOR) && add.equals(Operation.ADD)) {
						BusinessException exception = CheckUtil.validateNameStrMayBeNullException(name,
								(String) field.get(obj), annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					// EDITOR 更新时可以不为空
					if (annotation.CheckName().equals(CheckNameType.EDITOR) && add.equals(Operation.UPDATE)) {
						BusinessException exception = CheckUtil.validateNameException(name, (String) field.get(obj),
								annotation.StrLength());
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					// CREATORID 只有新增时校验
					if (annotation.CheckName().equals(CheckNameType.CREATORID) && add.equals(Operation.ADD)) {
						try {
							if ((Integer) value == null){
								
							} else {
								BusinessException exception = CheckUtil.validateIdException(name, (Integer) value);
								if (null != exception) {
									errList.add(exception.getMessage());
								}
							}
							
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to"))
								errList.add(name + ":" + CheckError.CAST_ERROR);
						}
					}
					// CREATORID 更新时只作校验不取值
					if (annotation.CheckName().equals(CheckNameType.CREATORID) && add.equals(Operation.UPDATE)) {
						try {
							BusinessException exception = CheckUtil.validateintMaybeNullException(name,
									(Integer) value);
							if (null != exception) {
								errList.add(exception.getMessage());
							}
						} catch (Exception e) {
							if (e.toString().contains("cannot be cast to"))
								errList.add(name + ":" + CheckError.CAST_ERROR);
						}
					}
					// EDITOR_ID 新增时可以为空
					if (annotation.CheckName().equals(CheckNameType.EDITORID) && add.equals(Operation.ADD)) {
						BusinessException exception = CheckUtil.validateintMaybeNullException(name, (Integer) value);
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}
					// //EDITOR_ID 修改时不能为空
					if (annotation.CheckName().equals(CheckNameType.EDITORID) && add.equals(Operation.UPDATE)) {
						BusinessException exception = CheckUtil.validateIdException(name, (Integer) value);
						if (null != exception) {
							errList.add(exception.getMessage());
						}
					}

				}
			}
			count++;
			if (errList.size() > 0) {
				map.put(count, errList);
			}

		}

		StringBuffer sb = new StringBuffer();
		for (Map.Entry<Integer, Object> entry : map.entrySet()) {
			if (null != entry.getValue() && null != entry.getValue() && !entry.getValue().equals("")) {
				sb.append("第" + entry.getKey() + "条:" + entry.getValue());
				sb.append(System.getProperty("line.separator"));
			}
		}
		if (!"".equals(sb.toString())) {
			throw new BusinessException("", "", sb.toString());
		}
		return objList;
	}

}
