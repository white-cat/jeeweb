package cn.jeeweb.core.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;

import cn.jeeweb.core.common.dao.support.OrderHelper;
import cn.jeeweb.core.common.data.DuplicateValid;
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

	int updateByHql(final String hql, final Object... params);
	
	int updateByIndexHql(final String hql, final Object... params);

	int updateByAliasHql(final String hql, final Map<String, Object> alias);

	Long countByHql(final String hql, final Object... params);

	Long countByAliasHql(final String hql, final Map<String, Object> alias);

	List<T> listByHql(final String hql, final Object... params);

	List<T> listByAliasHql(final String hql, final Map<String, Object> alias);

	List<T> listByHql(String hql, int page, int rows, Object... params);

	List<Map<String, Object>> listMapByHql(final String hql, final Object... params);

	List<Map<String, Object>> listMapByAliasHql(final String hql, final Map<String, Object> alias);

	/**************************************************************/
	/**************** 执行SQL语句区域 *********************************/
	/**************************************************************/
	/**************************************************************/
	void executeSql(String sql, final Object... params);

	void executeAliasSql(String sql, final Map<String, Object> alias);

	Integer countBySql(String sql, final Object... params);

	Integer countByAliasSql(String sql, final Map<String, Object> alias);

	List<Map<String, Object>> listBySql(String sql, final Object... params);

	List<Map<String, Object>> listByAliasSql(String sql, final Map<String, Object> alias);

	List<Map<String, Object>> listPageBySql(String sql, int page, int rows, final Object... params);

	List<Map<String, Object>> listPageByAliasSql(String sql, int page, int rows, final Map<String, Object> alias);

	List<T> listEntityBySql(String sql, final Object... params);

	List<T> listEntityByAliasSql(String sql, final Map<String, Object> alias);

	List<T> listPageEntityBySql(String sql, int page, int rows, final Object... params);

	List<T> listPageEntityByAliasSql(String sql, int page, int rows, final Map<String, Object> alias);

	/**************************************************************/
	/**************** 执行动态SQL区域 *********************************/
	/**************************************************************/
	/**************************************************************/
	int updateByHqlQueryId(final String queryId, final Object... params);

	int updateByAliasHqlQueryId(final String queryId, final Map<String, Object> alias);

	Long countByHqlQueryId(final String queryId, final Object... params);

	Long countByAliasHqlQueryId(final String queryId, final Map<String, Object> alias);

	List<T> listByHqlQueryId(final String queryId, final Object... params);

	List<T> listByAliasHqlQueryId(final String queryId, final Map<String, Object> alias);

	List<T> listByHqlQueryId(final String queryId, int page, int rows, final Object... params);

	List<Map<String, Object>> listMapByHqlQueryId(final String queryId, final Object... params);

	List<Map<String, Object>> listMapByHqlQueryId(final String queryId, int page, int rows, final Object... params);

	List<Map<String, Object>> listMapByAliasHqlQueryId(final String queryId, final Map<String, Object> alias);

	List<Map<String, Object>> listMapByAliasHqlQueryId(final String queryId, int page, int rows,
			final Map<String, Object> alias);

	void executeSqlQueryId(String queryId, final Object... params);

	Integer countBySqlQueryId(String queryId, final Object... params);

	List<Map<String, Object>> listBySqlQueryId(String queryId, final Object... params);

	List<Map<String, Object>> listByAliasSqlQueryId(String queryId, final Map<String, Object> alias);

	List<Map<String, Object>> listPageBySqlQueryId(String queryId, int page, int rows, final Object... params);

	List<Map<String, Object>> listPageByAliasSqlQueryId(String queryId, int page, int rows,
			final Map<String, Object> alias);

	List<T> listEntityBySqlQueryId(String queryId, final Object... params);

	List<T> listEntityByAliasSqlQueryId(String queryId, final Map<String, Object> alias);

	List<T> listPageEntityBySqlQueryId(String queryId, int page, int rows, final Object... params);

	List<T> listPageEntityByAliasSqlQueryId(String queryId, int page, int rows, final Map<String, Object> alias);
	
	Boolean doValid(DuplicateValid duplicateValid); 
}