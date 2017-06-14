package cn.jeeweb.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import cn.jeeweb.core.dao.support.OrderHelper;
import cn.jeeweb.core.query.data.Page;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;

public interface ICommonService<T extends Serializable> {

	/*************** 保存 ****************************/
	void save(T entity);

	void batchSave(List<T> entitys);

	void saveOrUpdate(T entity);

	/*************** 更新 ****************************/

	void update(T entity);
	
	void merge(T entity);

	/*************** 查找 ****************************/
	T get(Serializable id);

	T get(String propertyName, Object value);
	
	T load(Serializable id);

	/*************** 删除 ****************************/
	void delete(T entity);

	void deleteById(Serializable id);

	void batchDelete(List<T> entitys);

	void batchDeleteById(List<?> ids);

	void batchDeleteByProperty(String propertyName, Object value);

	int count();

	int count(Criterion... criterions);

	List<T> list();
	
	List<T> list(String propertyName, Object value);

	List<T> list(OrderHelper orderHelper);

	/** 分页查找 */
	List<T> list(int page, int rows, DetachedCriteria detachedCriteria);

	/** 分页查找 */
	Page<T> list(Pageable pageable, DetachedCriteria detachedCriteria);

	/** 分页查找 */
	Page<T> list(Queryable queryable, DetachedCriteria detachedCriteria);

	List<T> listWithNoPage(Queryable queryable, DetachedCriteria detachedCriteria);

	List<T> listByCriterion(Criterion... criterions);

	List<T> listByCriterion(OrderHelper orderHelper, Criterion... criterions);

	List<T> listByCriterion(int page, int rows, Criterion... criterions);

	List<T> listByCriterion(OrderHelper orderHelper, int page, int rows, Criterion... criterions);

	public int updateByHql(final String hql, final Object... params);

	Long countByHql(final String hql, final Object... params);
	
	public List<T> listByHql(final String hql, final Object... params);
	
	public List<T> listByHql(String hql,int page, int rows, Object... params);
	
	public List<T> listByIndexAliasHql(final String hql, final Object... params);

	public List<T> listByAliasHql(final String hql, final Map<String, Object> alias);

	List<Map<String, Object>> listMapByHql(final String hql, final Object... params);

	List<Map<String, Object>> listMapByIndexAliasHql(final String hql, final Object... params);

	List<Map<String, Object>> listMapByAliasHql(final String hql, final Map<String, Object> alias);

}