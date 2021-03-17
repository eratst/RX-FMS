package com.pcitc.fms.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface CheckField {

	 public CheckNameType CheckName() ;
	 
	 public int StrLength () default 100;
	 
	 public String Explain() default "";
	 
}
