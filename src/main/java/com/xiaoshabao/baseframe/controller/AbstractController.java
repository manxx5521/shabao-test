package com.xiaoshabao.baseframe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象Controller方法
 * <p>
 * 所有的Controller都要继承这个类,在这个类的基础上可以实习一个自己的BaseController
 * </p>
 */
public abstract class AbstractController {
	public AbstractController() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}

	protected Logger logger;
}
