package com.xiaoshabao.shabaowebtest.service;

import com.xiaoshabao.baseframework.exception.DaoException;
import com.xiaoshabao.baseframework.exception.ServiceException;
import com.xiaoshabao.baseframework.service.AbstractService;
import com.xiaoshabao.shabaowebtest.dto.DemoDto;

/**
 * 示例
 */
public interface DemoService extends AbstractService{
	/**
	 * 添加文章
	 * @param article
	 * @param account_ids
	 * @param user_id
	 * @throws ServiceException
	 */
	public DemoDto testSQL()throws ServiceException, DaoException;
}
