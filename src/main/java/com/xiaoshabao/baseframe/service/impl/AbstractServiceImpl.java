package com.xiaoshabao.baseframe.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiaoshabao.baseframe.bean.PageValue;
import com.xiaoshabao.baseframe.bean.PagingPrams;
import com.xiaoshabao.baseframe.dao.BaseDao;
import com.xiaoshabao.baseframe.exception.DaoException;
import com.xiaoshabao.baseframe.exception.ServiceException;
import com.xiaoshabao.baseframe.service.AbstractService;

/**
 * 抽象Service类
 * <p>
 * 添加共性的东西<br/>
 * 要想使用这个需要在项目中实现一个BaseServiceImpl来添加项目个性的东西，<br/>
 * 即使不用也可实现一个空类，达到命名一致
 * </p>
 */
public abstract class AbstractServiceImpl implements AbstractService {
	public AbstractServiceImpl() {
		// 统一添加日志
		logger = LoggerFactory.getLogger(getClass());
	}

	protected Logger logger;

	@Resource(name = "mybatisBaseDao")
	protected BaseDao baseDao;

	/**
	 * 通过这个方法把分页查询dao实例
	 */
	@Override
	public <T, P extends PagingPrams> PageValue<T> pagingQuery(Class<T> clasz,
			P pageParams) {
		PageValue<T> pageValue = new PageValue<T>();
		List<T> models = this.baseDao.pagingqueryData(clasz, pageParams);
		pageValue.setModels(models);
		Integer rowcount = this.baseDao.queryRowCount(clasz, pageParams);
		pageValue.setTotalrowcount(rowcount);
		// 计算页数
		Integer pageCount = rowcount / pageParams.getPagesize();
		if (rowcount % pageParams.getPagesize() > 0) {
			pageCount = pageCount + 1;
		}
		pageValue.setPagesize(pageParams.getPagesize());
		pageValue.setPagecount(pageCount);
		return pageValue;
	}

	@Override
	public <T> void update(Class<T> clasz, T t, Object p)
			throws ServiceException {
		try {
			this.baseDao.update(clasz, p);
		} catch (Exception ex) {
			throw new ServiceException(ex.getMessage());
		}

	}

	@Override
	public <T> void insert(Class<T> clasz, T t) throws ServiceException {
		try {
			this.baseDao.insert(clasz, t);
		} catch (Exception ex) {

		}
	}

	@Override
	public <T> boolean exists(Class<T> clasz, T t) throws ServiceException {
		try {
			return this.baseDao.exists(clasz, t);
		} catch (Exception ex) {

		}
		return false;
	}

	@Override
	public <T, P extends PagingPrams> List<T> queryModels(Class<T> clasz, P p)
			throws ServiceException {
		return this.baseDao.pagingqueryData(clasz, p);
	}

	@Override
	public <T> List<T> getData(Class<T> clasz, Object params)
			throws DaoException {
		return this.baseDao.getData(clasz, params);
	}

	@Override
	public <T> List<T> getData(String sqlid, Object param) throws DaoException {
		return this.baseDao.getData(sqlid, param);
	}

	@Override
	public <T> T getDataSingle(String sqlid, Object param) throws DaoException {
		return this.baseDao.getDataSingle(sqlid, param);
	}

	@Override
	public <T> T getDataSingle(Class<T> clazz, Object param)
			throws DaoException {
		// TODO Auto-generated method stub
		return this.baseDao.getDataSingle(clazz, param);
	}
}
