package com.xiaoshabao.webframework.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * ajax请求数据时，将long类型转换成string类型，解决精度丢失问题
 */
public class LongToStringJsonConverter extends ObjectMapper {

	private static final long serialVersionUID = 1683531771040674386L;

	public LongToStringJsonConverter() {
		super();
		SimpleModule simpleModule = new SimpleModule();
		// simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
		simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
		registerModule(simpleModule);
	}
}
