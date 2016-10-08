package com.xiaoshabao.baseframework.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.github.abel533.sql.SqlMapper;
import com.xiaoshabao.baseframework.bean.PagingParams;
import com.xiaoshabao.baseframework.dao.BaseDao;
import com.xiaoshabao.baseframework.enums.DaoEnum;

/**
 * 通用的数据库操作组件
 * @param <T>
 * @param <P>
 */
@Repository("mybatisBaseDao")
public class MybatisBaseDaoImpl extends SqlSessionDaoSupport implements BaseDao {

	/**
	 * 目标对象类名称
	 */
	protected String clazzName;

	// 注入sqlSession
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public <T> int insert(Class<T> clazz, T t) {
		return this.getSqlSession().insert(
				DaoEnum.INSERT.getVlaue() + clazz.getSimpleName(), t);
	}

	@Override
	public <T> int delete(Class<T> clazz, T t) {
		return this.getSqlSession().delete(
				DaoEnum.DELETE.getVlaue() + clazz.getSimpleName(), t);
	}

	@Override
	public <T> int update(Class<T> clazz, Object p) {
		return this.getSqlSession().update(
				DaoEnum.UPDATE.getVlaue() + clazz.getSimpleName(), p);

	}

	@Override
	public <T> boolean exists(Class<T> clazz, T t) {
		List<T> result = this.getSqlSession().<T> selectList(
				DaoEnum.EXISTS.getVlaue() + clazz.getSimpleName(), t);
		if (result == null || result.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public <T> List<T> getData(Class<T> clazz, Object param) {
		return getSqlSession().selectList(
				DaoEnum.GETDATA.getVlaue() + clazz.getSimpleName(), param);
	}

	@Override
	public <T> List<T> getData(String sqlid, Object param) {
		return this.getSqlSession().selectList(sqlid, param);
	}

	@Override
	public <T> T getDataSingle(Class<T> clazz, Object param) {
		return this.getSqlSession().selectOne(
				DaoEnum.GETONE.getVlaue() + clazz.getSimpleName(), param);
	}

	@Override
	public <T> T getDataSingle(String sqlid, Object param) {
		return this.getSqlSession().selectOne(sqlid, param);
	}

	@Override
	public <T, P extends PagingParams> List<T> getDataPaging(Class<T> clazz,P pageParams) {
		return this.getSqlSession().<T> selectList(
				DaoEnum.PAGINGQUERY.getVlaue() + clazz.getSimpleName(), pageParams);
	}

	@Override
	public SqlMapper getSqlMapper() {
		return new SqlMapper(this.getSqlSession());
	}

}