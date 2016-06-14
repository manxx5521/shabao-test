package com.xiaoshabao.wechat.api.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 微信请求处理类型
 * <P>
 * 请求参数注解，可以根据注解value的值来确定是是什么类型的请求
 * </P>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqType {

	public String value() default "";
}
