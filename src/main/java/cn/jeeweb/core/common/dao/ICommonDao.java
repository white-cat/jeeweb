package cn.jeeweb.core.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import cn.jeeweb.core.common.dao.support.OrderHelper;
import cn.jeeweb.core.common.hibernate.dynamic.adapter.IDynamicHibernateAdapter;

public interface ICommonDao extends IDynamicHibernateAdapter, ISqlDao {

	/**
	 * 保存
	 * 
	 */
	<T> void save(T entity);

	<T> void batchSave(List<T> entitys);

	<T> void saveOrUpdate(T entity);

	/**
	 * 更新
	 */
	<T> void update(T entity);

	<T> void merge(T entity);

	/**
	 * 查找
	 * 
	 */
	<T> T load(Serializable id, Class<T> entityClass);

	<T> T get(Serializable id, Class<T> entityClass);

	<T> T get(String propertyName, Object value, Class<T> entityClass);

	/**
	 * 实体是否存在
	 * 
	 * @param <T>
	 * 
	 */
	<T> boolean exists(Serializable id, Class<T> entityClass);

	<T> int count(Class<T> entityClass);

	<T> Long count(DetachedCriteria detachedCriteria);

	<T> int count(Class<T> entityClass, Criterion... criterions);

	<T> List<T> list(Class<T> entityClass);

	<T> List<T> list(String propertyName, Object value, Class<T> entityClass);

	<T> List<T> list(Class<T> entityClass, OrderHelper orderHelper);

	<T> List<T> list(DetachedCriteria detachedCriteria);

	<T> List<T> listByCriterion(Class<T> entityClass, Criterion... criterions);

	<T> List<T> listByCriterion(Class<T> entityClass, OrderHelper orderHelper, Criterion... criterions);

	<T> List<T> listByCriterion(int page, int rows, Class<T> entityClass, Criterion... criterions);

	<T> List<T> listByCriterion(int page, int rows, Class<T> entityClass, OrderHelper orderHelper,
			Criterion... criterions);

	/** 分页查找 */
	<T> List<T> list(int page, int rows, DetachedCriteria detachedCriteria);

	/**
	 * 删除
	 * 
	 */
	<T> void delete(T entity);

	<T> void deleteById(Serializable id, Class<T> entityClass);

	<T> void batchDelete(List<T> entitys);

	<T> void batchDeleteByProperty(String propertyName, Object value, Class<T> entityClass);

	int updateByHql(final String hql, final Object... params);

	int updateByAliasHql(final String hql, final Map<String, Object> alias);

	Long countByHql(final String hql, final Object... params);

	Long countByAliasHql(final String hql, final Map<String, Object> alias);

	<T> List<T> listByHql(final String hql, final Object... params);

	<T> List<T> listByAliasHql(final String hql, final Map<String, Object> alias);

	<T> List<T> listByHql(final String hql, int page, int rows, final Object... params);
	
	<T> List<T> listByAliasHql(final String hql, int page, int rows, final Map<String, Object> alias);

	List<Map<String, Object>> listMapByHql(final String hql, final Object... params);

	List<Map<String, Object>> listMapByAliasHql(final String hql, final Map<String, Object> alias);

}