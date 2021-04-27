package com.pcitc.fms.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import com.pcitc.fms.common.VerifyUtil;
import com.pcitc.fms.common.annotation.CheckField;
import com.pcitc.fms.common.annotation.CheckNameType;
import com.pcitc.fms.common.annotation.SpecialResource;
import com.pcitc.fms.exception.BusinessException;
import com.pcitc.imp.common.exception.BusiException;

import io.vertx.ext.web.RoutingContext;

/**
 * 
 * @author lei.y
 *
 */
public class QueryParams {

	public static <T> T getQueryParams(RoutingContext routingContext, Class<T> clazz) throws Exception {
		// 获取对象实例
		T obj = clazz.newInstance();
		// 获取对象属性数组
		Field[] objFields = clazz.getDeclaredFields();
		// 遍历上述数组
		for (Field objField : objFields) {
			// 设置暴力访问
			objField.setAccessible(true);
			// 判断该属性上是否有CheckField注解，并获取CheckField对象
			boolean objFieldAnnotationPresent = objField.isAnnotationPresent(CheckField.class);
			CheckField ObjSupperFieldAnnotation = objField.getAnnotation(CheckField.class);
			// 获取该属性名称
			String objFieldName = objField.getName();
			String value = null;
			// 如果该属性加了CheckField注解
			if (objFieldAnnotationPresent == true) {
				// 该属性加了CheckField注解，并且类型为PAGEINFO，ORDER，CODELIST，则说明该属性为资源以外的属性
				if (ObjSupperFieldAnnotation.CheckName().equals(CheckNameType.PAGEINFO)
						|| ObjSupperFieldAnnotation.CheckName().equals(CheckNameType.ORDER)
						|| ObjSupperFieldAnnotation.CheckName().equals(CheckNameType.TREE)
						|| ObjSupperFieldAnnotation.CheckName().equals(CheckNameType.CODELIST)) {
					// 资源以外的属性在获取参数时要加上“$”
					value = routingContext.request().getParam("$" + objFieldName) == null ? null
							: routingContext.request().getParam("$" + objFieldName).trim();
				} else {
					// 资源以内的属性获取参数值直接根据属性名称获取
					value = routingContext.request().getParam(objFieldName) == null ? null
							: routingContext.request().getParam(objFieldName).trim();
				}
			}

			if (StringUtils.isNotEmpty(value)) {
				// 判断属性是否有注解
				if (objFieldAnnotationPresent == true) {

					checkCode(obj, objFieldName, objField, value, ObjSupperFieldAnnotation);

					checkName(obj, objFieldName, objField, value, ObjSupperFieldAnnotation);

					checkId(obj, objField, value, ObjSupperFieldAnnotation);

					checkInUse(obj, objField, value, ObjSupperFieldAnnotation);

					getOrder(obj, objField, value, ObjSupperFieldAnnotation);
					
					checkTree(obj, objFieldName, objField, value, ObjSupperFieldAnnotation);

					checkPage(obj, objFieldName, objField, value, ObjSupperFieldAnnotation);
					
					checkCondition(obj, objFieldName, objField, value, ObjSupperFieldAnnotation);
				}
			}

		}

		return obj;
	}


	private static void checkTree(Object obj, String objFieldName, Field objField, String value,
			CheckField objSupperFieldAnnotation) throws IllegalArgumentException, IllegalAccessException {
		if (objSupperFieldAnnotation.CheckName().equals(CheckNameType.TREE)) {
			objField.set(obj, value);
		}
		
	}

	// 获取动态过滤参数
	public static <T> Specification<T> getWhereClause(Object paramObject) {
		return new Specification<T>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> listPre = new ArrayList<Predicate>();

				// 获取model实体对象中的全部属性
				Field[] fields = paramObject.getClass().getDeclaredFields();
				// 获取实体对象的名称，并将首字母小写（如：NewTank变为newTank）
				String classNameTemp = paramObject.getClass().getName().substring(
						paramObject.getClass().getName().lastIndexOf(".") + 1,
						paramObject.getClass().getName().length());
				String className = classNameTemp.substring(0, 1).toLowerCase()
						+ classNameTemp.substring(1, classNameTemp.length());
				Predicate[] p = null;
				// 遍历属性数组
				for (Field field : fields) {
					// 设置暴力访问
					field.setAccessible(true);
					try {
						// 根据属性field与对象Object反射获取该属性对应的值
						Object fieldValue = field.get(paramObject);
						if (fieldValue != null) {

							// 判断属性上面是否加了CheckField注解，并且获得CheckField对象
							boolean objFieldAnnotationPresent = field.isAnnotationPresent(CheckField.class);
							CheckField ObjSupperFieldAnnotation = field.getAnnotation(CheckField.class);

							// 判断属性上面是否加了SpecialResource注解，并且获得SpecialResource对象
							boolean resourceMemberFlag = field.isAnnotationPresent(SpecialResource.class);
							SpecialResource resourceMember = field.getAnnotation(SpecialResource.class);

							// 如果属性上加了CheckField注解
							if (objFieldAnnotationPresent == true) {

								// 如果该属性加了CheckField注解并且类型为CODE
								if (ObjSupperFieldAnnotation.CheckName().equals(CheckNameType.CODE)) {
									Predicate predicate = null;
									// 该属性加了SpecialResource注解，说明该属性是特殊的（如tankTypeCode，该属性应该是在newTank对象下的tankType对象中，即newTank.tankType.tankTypeCode）
									if (resourceMemberFlag == true) {
										// 根据“.”来分割，如（newTank.tankType.tankTypeCode分割完后为[newTank,tankType,tankTypeCode]）
										if (resourceMember.name().contains(".")) {
											// 将条件拼进predicate对象中
											predicate = setSpecialCodeProp(root, cb, field, fieldValue, resourceMember,
													predicate);
										}
									} else {
										// 该属性没有加SpecialResource注解，说明该属性是一个普通属性，直接将条件加到predicate对象中
										predicate = cb.equal(root.get(field.getName()).as(String.class),
												fieldValue);
									}
									listPre.add(predicate);
								}

								// 如果该属性加了CheckField注解并且类型为NAME
								if (ObjSupperFieldAnnotation.CheckName().equals(CheckNameType.NAME)) {
									Predicate predicate = null;
									// 该属性加了SpecialResource注解，说明该属性是特殊的（如tankTypeName，该属性应该是在newTank对象下的tankType对象中，即newTank.tankType.tankTypeName）
									if (resourceMemberFlag == true) {
										// 与CODE类似，差别在于CODE类型是cb.equal，NAME类型是cb.like，并且值加了%如："%"
										// + fieldValue + "%"
										if (resourceMember.name().contains(".")) {
											predicate = setSpecialNameProp(root, cb, field, fieldValue, resourceMember,
													predicate);
										} else {
											predicate = cb.like(
													root.get(field.getName()).as(String.class),
													"%" + fieldValue + "%");
										}
									} else {
										predicate = cb.like(root.get(className).get(field.getName()).as(String.class),
												"%" + fieldValue + "%");
									}
									listPre.add(predicate);
								}

								// 如果该属性加了CheckField注解并且类型为CODELIST
								if (ObjSupperFieldAnnotation.CheckName().equals(CheckNameType.CODELIST)) {
									String subFieldName = field.getName().substring(0, field.getName().length() - 1);
									In in = cb.in(root.get(subFieldName).as(String.class));
									in.value(fieldValue);
									listPre.add(in);
								}

								// 如果该属性加了CheckField注解并且类型为ID或ENABLED
								if (ObjSupperFieldAnnotation.CheckName().equals(CheckNameType.ENABLED)
										|| ObjSupperFieldAnnotation.CheckName().equals(CheckNameType.ID)) {
									Predicate predicate = cb.equal(
											root.get(field.getName()).as(Integer.class), fieldValue);
									listPre.add(predicate);
								}
							}
						}
						p = new Predicate[listPre.size()];
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return cb.and(listPre.toArray(p));
			}

			/**
			 * @author lei.y
			 * @param root
			 * @param cb
			 * @param field
			 * @param fieldValue
			 * @param resourceMember
			 * @param predicate
			 * @return
			 * 
			 * 		特别说明：setSpecialNameProp与setSpecialCodeProp这两个方法实在有点蠢，
			 *         找不到该如何循环遍历的将层数未知的属性动态拼进去，望大神指教。 能够解决的大神私我：18209182814，三口。
			 */
			@SuppressWarnings("hiding")
			private <T> Predicate setSpecialNameProp(Root<T> root, CriteriaBuilder cb, Field field, Object fieldValue,
					SpecialResource resourceMember, Predicate predicate) {
				String fieldAllName = resourceMember.name();
				String[] nameArr = fieldAllName.split("\\.");
				if (nameArr.length == 2) {
					predicate = cb.like(root.get(nameArr[0]).get(field.getName()).as(String.class),
							"%" + fieldValue + "%");
				}
				if (nameArr.length == 3) {
					predicate = cb.like(root.get(nameArr[0]).get(nameArr[1]).get(field.getName()).as(String.class),
							"%" + fieldValue + "%");
				}
				if (nameArr.length == 4) {
					predicate = cb.like(
							root.get(nameArr[0]).get(nameArr[1]).get(nameArr[2]).get(field.getName()).as(String.class),
							"%" + fieldValue + "%");
				}
				if (nameArr.length == 5) {
					predicate = cb.like(root.get(nameArr[0]).get(nameArr[1]).get(nameArr[2]).get(nameArr[3])
							.get(field.getName()).as(String.class), "%" + fieldValue + "%");
				}
				return predicate;
			}

			@SuppressWarnings("hiding")
			private <T> Predicate setSpecialCodeProp(Root<T> root, CriteriaBuilder cb, Field field, Object fieldValue,
					SpecialResource resourceMember, Predicate predicate) {
				String fieldAllName = resourceMember.name();
				String[] nameArr = fieldAllName.split("\\.");
				if (nameArr.length == 1) {
					predicate = cb.equal(root.get(nameArr[0]).get(field.getName()).as(String.class),
							fieldValue);
				}
				if (nameArr.length == 3) {
					predicate = cb.equal(root.get(nameArr[0]).get(nameArr[1]).get(field.getName()).as(String.class),
							fieldValue);
				}
				if (nameArr.length == 4) {
					predicate = cb.equal(
							root.get(nameArr[0]).get(nameArr[1]).get(nameArr[2]).get(field.getName()).as(String.class),
							fieldValue);
				}
				if (nameArr.length == 5) {
					predicate = cb.equal(root.get(nameArr[0]).get(nameArr[1]).get(nameArr[2]).get(nameArr[3])
							.get(field.getName()).as(String.class), fieldValue);
				}
				return predicate;
			}
		};
	}

	private static void checkCode(Object obj, String fieldName, Field field, String value,
			CheckField supperFieldAnnotation) throws BusinessException, IllegalAccessException, BusiException {
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.CODE)) {
			if (value.length() > 36) {
				throw new BusiException("", fieldName + "长度不能大于36");
			} else if (!value.matches(VerifyUtil.NewregexForString)) {//regexForCode
				throw new BusiException("", fieldName + "由数字、26个英文字母或者下划线组成");
			} else {
				field.set(obj, value);
			}
		}

        if (supperFieldAnnotation.CheckName().equals(CheckNameType.CODEMAYBENULL)){
            if (StringUtils.isNotEmpty(value)){
                if (value.length() > 36) {
                    throw new BusiException("", fieldName + "长度不能大于36");
                } else if (!value.matches(VerifyUtil.regexForCode)) {
                    throw new BusiException("", fieldName + "由数字、26个英文字母或者下划线组成");
                } else {
                    field.set(obj, value);
                }
            }
        }

		if (supperFieldAnnotation.CheckName().equals(CheckNameType.CODELIST)) {
			List<String> codeList = CheckUtil.checkCodeList(value);
			field.set(obj, codeList);
		}
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.RENTCODE)) {
			if (value.length() > 36) {
				throw new BusiException("", fieldName + "长度不能大于36");
			} else if (!value.matches(VerifyUtil.NewregexForString)) {
				throw new BusiException("", fieldName + "由数字、26个英文字母或者汉字或者下划线组成的字符串，字符串前后允许有空格 中划线 和.组成");
			} else {
				field.set(obj, value);
			}
		}
	}

	private static void checkName(Object obj, String fieldName, Field field, String value,
			CheckField supperFieldAnnotation) throws BusiException, IllegalAccessException {
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.NAME)) {
			if (value.length() > 50) {
				throw new BusiException("", fieldName + "长度不能大于50");
			} else {
				if (!value.matches(VerifyUtil.NewregexForString)) {
					throw new BusiException("", fieldName + "由数字、26个英文字母或者汉字或者下划线组成");
				} else {
					field.set(obj, value);
				}
			}
		}

        if (supperFieldAnnotation.CheckName().equals(CheckNameType.NAMEMAYBENULL)) {
            if (StringUtils.isNotEmpty(value)) {
                if (value.length() > 50) {
                    throw new BusiException("", fieldName + "长度不能大于50");
                } else {
                    if (!value.matches(VerifyUtil.NewregexForString)) {
                        throw new BusiException("", fieldName + "由数字、26个英文字母或者汉字或者下划线组成");
                    } else {
                        field.set(obj, value);
                    }
                }
            }
        }
		
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.UNITNAME)) {
			if (value.length() > 50) {
				throw new BusiException("", fieldName + "长度不能大于50");
			} else {
				if (!value.matches(VerifyUtil.NewregexForUnitName)) {
					throw new BusiException("", fieldName + "由数字、26个英文字母或者汉字或者正斜杠组成");
				} else {
					field.set(obj, value);
				}
			}
		}
		
	}
	
	private static void checkCondition(Object obj, String fieldName, Field field, String value,
			CheckField supperFieldAnnotation) throws BusiException, IllegalAccessException{
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.CONDITION)) {
			if(value.length() >50){
				throw new BusiException("", fieldName + "长度不能大于50");
			}else{
				field.set(obj, value);
			}
		}
	}

	private static void checkId(Object obj, Field field, String value, CheckField supperFieldAnnotation)
			throws BusiException {
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.ID)) {
			try {
				Long longValue = Long.parseLong(value);
				if (Long.MAX_VALUE < longValue || longValue < 0) {
					throw new BusiException("", "" + field.getName() + "只能为0到" + Long.MAX_VALUE + "的数字");
				}
				field.set(obj, longValue);
			} catch (Exception e) {
				throw new BusiException("", "" + field.getName() + "只能为0到" + Long.MAX_VALUE + "的数字");
			}

		}
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.IDFORQUERY)) {
			try {
				Long intValue = Long.valueOf(value);
				if (Long.MAX_VALUE < intValue || intValue < 0) {
					throw new BusiException("", "" + field.getName() + "只能为0到" + Long.MAX_VALUE + "的数字");
				}
				field.set(obj, intValue);
			} catch (Exception e) {
				throw new BusiException("", "" + field.getName() + "只能为0到" + Long.MAX_VALUE + "的数字");
			}

		}
	}

	private static void checkInUse(Object obj, Field field, String value, CheckField supperFieldAnnotation)
			throws IllegalAccessException, BusiException {
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.ENABLED)) {
			if (value.equals("1") || value.equals("0")) {
				Integer inUse = Integer.parseInt(value);
				field.set(obj, inUse);
			} else {
				throw new BusiException("", "inUse只能为0或1");
			}
		}
	}
	
	private static void getOrder(Object obj, Field field, String value, CheckField supperFieldAnnotation)
			throws BusiException, IllegalAccessException {
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.ORDER)) {
			field.set(obj, value);
		}
	}


	private static void checkOrder(Object obj, Field field, String value, CheckField supperFieldAnnotation)
			throws BusiException, IllegalAccessException {
		boolean flag = false;
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.ORDER)) {
			Field[] allFields = obj.getClass().getDeclaredFields();
			List<String> orderLists = Arrays.asList(value.split(","));
			List<org.springframework.data.domain.Sort.Order> sortOrderLists = new ArrayList<>();
			for (String orderList : orderLists) {
				String[] oneOrderArr = orderList.split(" ");
				for (Field allField : allFields) {
					if (oneOrderArr[0].trim().equals(allField.getName())) {
						flag = true;
						break;
					}
				}
				if (flag == false) {
					throw new BusiException("", "排序条件" + orderList + "有误");
				} else {
					if (oneOrderArr[1].trim().equalsIgnoreCase("asc")) {
						Order oder = new Order(Direction.ASC, oneOrderArr[0]);
						sortOrderLists.add(oder);
					}
					if (oneOrderArr[1].trim().equalsIgnoreCase("desc")) {
						Order oder = new Order(Direction.DESC, oneOrderArr[0]);
						sortOrderLists.add(oder);
					}
				}
			}

			Sort sort = new Sort(sortOrderLists);

			field.set(obj, sort);
		}
	}

	private static void checkPage(Object obj, String fieldName, Field field, String value,
			CheckField supperFieldAnnotation) throws BusinessException, IllegalAccessException {
		if (supperFieldAnnotation.CheckName().equals(CheckNameType.PAGEINFO)) {
			if (fieldName.contains("top")) {
				Integer _top = CheckUtil.checkTop(value);
				field.set(obj, _top);
			}
			if (fieldName.contains("skip")) {
				Integer _skip = CheckUtil.checkSkip(value);
				field.set(obj, _skip);
			}
		}
	}
}
