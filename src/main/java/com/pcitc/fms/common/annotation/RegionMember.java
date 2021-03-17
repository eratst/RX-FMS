package com.pcitc.fms.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

 /**
 * Title: AnnotationUtil
 * Description: TODO task mark zhenqiang.zhao
 * @author zhenqiang.zhao
 * @date 2017年7月4日
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface RegionMember {
	  public String Name() default "";
	  
	  public boolean IsRegion() default true;
	  
	  public boolean type() default true;
}
