package cn.jeeweb.core.common.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import cn.jeeweb.core.common.dao.ICommonDao;
import cn.jeeweb.core.common.dao.ISqlDao;
import cn.jeeweb.core.common.dao.support.OrderHelper;
import cn.jeeweb.core.common.entity.MarkDeleteable;
import cn.jeeweb.core.common.hibernate.dynamic.adapter.IDynamicHibernateAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@SuppressWarnings({ "unchecked" })
@Repository("commonDao")
public class CommonDaoImpl extends BaseDaoImpl implements ICommonDao, ISqlDao, IDynamicHibernateAdapter {

	@Autowired
	private ISqlDao sqlDao;
	@Autowired
	private IDynamicHibernateAdapter dynamicHibernateAdapter;

	private <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	@Override
	public <T> void save(T entity) {
		Assert.notNull(entity);
		try {
			getSession().save(entity);
			getSession().flush();
		} catch (RuntimeException e) {
			logger.error("添加异常", e);
			throw e;
		}
	}

	@Override
	public <T> void batchSave(List<T> entitys) {
		Assert.notNull(entitys);
		for (int i = 0; i < entitys.size(); i++) {
			getSession().save(entitys.get(i));
			if (i % 20 == 0) {
				getSession().flush();
				getSession().clear();
			}
		}
		getSession().flush();
		getSession().clear();
	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		Assert.notNull(entity);
		try {
			getSession().saveOrUpdate(entity);
		} catch (RuntimeException e) {
			logger.error("添加或更新失败", e);
			throw e;
		}
	}

	@Override
	public <T> void update(T entity) {
		Assert.notNull(entity);
		try {
			getSession().update(entity);
		} catch (RuntimeException e) {
			logger.error("更新失败", e);
			throw e;
		}
	}

	@Override
	public <T> T load(Serializable id, Class<T> entityClass) {
		Assert.notNull(id);
		try {
			T t = (T) getSession().load(entityClass, id);
			return t;
		} catch (RuntimeException e) {
			logger.error("获取失败", e);
			throw e;
		}
	}

	@Override
	public <T> T get(Serializable id, Class<T> entityClass) {
		Assert.notNull(id);
		try {
			T t = (T) getSession().get(entityClass, id);
			return t;
		} catch (RuntimeException e) {
			logger.error("获取失败", e);
			throw e;
		}
	}

	@Override
	public <T> T get(String propertyName, Object value, Class<T> entityClass) {
		Assert.hasText(propertyName);
		Assert.notNull(value);
		try {
			T t = (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
			return t;
		} catch (RuntimeException e) {
			logger.error("获取失败", e);
			throw e;
		}
	}

	@Override
	public <T> void delete(T entity) {
		Assert.notNull(entity);
		try {
			if (entity instanceof MarkDeleteable) {
				MarkDeleteable markDeleteable = (MarkDeleteable) entity;
				if (markDeleteable.markStatus()) {
					saveOrUpdate(entity);
				} else {
					getSession().delete(entity);
				}
			} else {
				getSession().delete(entity);
			}
		} catch (RuntimeException e) {
			logger.error("删除失败", e);
			throw e;
		}
	}

	@Override
	public <T> void deleteById(Serializable id, Class<T> entityClass) {
		Assert.notNull(id);
		try {
			delete(get(id, entityClass));
		} catch (RuntimeException e) {
			logger.error("删除失败", e);
			throw e;
		}
	}

	@Override
	public <T> void batchDeleteByProperty(String propertyName, Object value, Class<T> entityClass) {
		Assert.hasText(propertyName);
		Assert.notNull(value);
		try {
			List<T> list = createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
			batchDelete(list);
		} catch (RuntimeException e) {
			logger.error("删除失败", e);
			throw e;
		}

	}

	@Override
	public <T> void batchDelete(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			delete(entitys.get(i));
			if (i % 20 == 0) {
				getSession().flush();
				getSession().clear();
			}
		}
		getSession().flush();
		getSession().clear();
	}

	@Override
	public <T> Long count(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		// 获取根据条件分页查询的总行数
		Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		if (total == null) {
			total = (long) 0;
		}
		return total;
	}

	@Override
	public <T> boolean exists(Serializable id, Class<T> entityClass) {
		Assert.notNull(id);
		try {
			getSession().get(entityClass, id);
			return false;
		} catch (RuntimeException e) {
			logger.error("获取失败", e);
			throw e;
		}
	}

	@Override
	public <T> int count(Class<T> entityClass) {
		try {
			return createCriteria(entityClass).list().size();
		} catch (RuntimeException e) {
			logger.error("获取失败", e);
			throw e;
		}
	}

	@Override
	public <T> int count(Class<T> entityClass, Criterion... criterions) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		if (criterions != null) {
			for (Criterion criterion : criterions) {
				if (criterion != null) {
					criteria.add(criterion);
				}

			}
		}
		Long total = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
		if (total == null) {
			total = (long) 0;
		}
		return total.intValue();
	}

	@Override
	public <T> List<T> list(Class<T> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		return criteria.list();
	}

	@Override
	public <T> List<T> list(Class<T> entityClass, OrderHelper orderHelper) {
		Criteria criteria = createCriteria(entityClass);
		for (Order order : orderHelper) {
			criteria.addOrder(order);
		}
		return criteria.list();
	}

	@Override
	public <T> List<T> list(int page, int rows, DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.setProjection(null);
		criteria.setFirstResult((page - 1) * rows);
		criteria.setMaxResults(rows);
		List<T> content = criteria.list();
		return content;
	}

	@Override
	public <T> List<T> list(DetachedCriteria detachedCriteria) {
		Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
		criteria.setProjection(null);
		List<T> content = criteria.list();
		return content;
	}

	/**
	 * 按顺序设置Query参数
	 *
	 * @param query
	 * @param params
	 */
	public void setParameters(Query query, Object[] params) {
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if (alias != null) {
			Set<String> keys = alias.keySet();
			for (String key : keys) {
				Object val = alias.get(key);
				if (val instanceof Collection) {
					// 查询条件是列表
					query.setParameterList(key, (Collection) val);
				} else {
					query.setParameter(key, val);
				}
			}
		}
	}

	@Override
	public int updateByHql(final String hql, final Object... params) {
		Query query = getSession().createQuery(hql);
		setParameters(query, params);
		return query.executeUpdate();
	}

	@Override
	public int updateByAliasHql(String hql, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		return query.executeUpdate();
	}

	@Override
	public <T> List<T> listByCriterion(Class<T> entityClass, Criterion... criterions) {
		try {
			Criteria criteria = this.getSession().createCriteria(entityClass);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					if (criterion != null) {
						criteria.add(criterion);
					}
				}
			}
			return criteria.list();
		} catch (RuntimeException re) {
			throw re;
		} finally {

		}
	}

	@Override
	public <T> List<T> listByCriterion(Class<T> entityClass, OrderHelper orderHelper, Criterion... criterions) {
		try {
			Criteria criteria = this.getSession().createCriteria(entityClass);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					if (criterion != null) {
						criteria.add(criterion);
					}
				}
			}
			for (Order order : orderHelper) {
				criteria.addOrder(order);
			}
			return criteria.list();
		} catch (RuntimeException re) {
			throw re;
		} finally {

		}
	}

	@Override
	public <T> List<T> listByCriterion(int page, int rows, Class<T> entityClass, Criterion... criterions) {
		try {
			Criteria criteria = this.getSession().createCriteria(entityClass);
			if (criterions != null) {
				for (Criterion criterion : criterions) {
					if (criterion != null) {
						criteria.add(criterion);
					}
				}
			}
			criteria.setFirstResult((page - 1) * rows);
			criteria.setMaxResults(rows);
			return criteria.list();
		} catch (RuntimeException re) {
			throw re;
		} finally {
		}
	}

	@Override
	public <T> List<T> listByCriterion(int page, int rows, Class<T> entityClass, OrderHelper orderHelper,
			Criterion... criterions) {

		Criteria criteria = this.getSession().createCriteria(entityClass);
		if (criterions != null) {
			for (Criterion criterion : criterions) {
				if (criterion != null) {
					criteria.add(criterion);
				}
			}
		}
		for (Order order : orderHelper) {
			criteria.addOrder(order);
		}
		criteria.setFirstResult((page - 1) * rows);
		criteria.setMaxResults(rows);
		return criteria.list();
	}

	@Override
	public Long countByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		setParameters(query, params);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long countByAliasHql(String hql, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		return (Long) query.uniqueResult();
	}

	@Override
	public <T> List<T> listByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		setParameters(query, params);
		return query.list();
	}

	@Override
	public <T> List<T> listByAliasHql(String hql, int page, int rows, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		return query.list();
	}

	@Override
	public <T> List<T> listByHql(String hql, int page, int rows, Object... params) {
		Query query = getSession().createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public <T> List<T> listByAliasHql(String hql, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listMapByHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listMapByAliasHql(String hql, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setAliasParameter(query, alias);
		return query.list();
	}

	@Override
	public <T> void merge(T entity) {
		getSession().merge(entity);
	}

	@Override
	public <T> List<T> list(String propertyName, Object value, Class<T> entityClass) {
		Assert.hasText(propertyName);
		Assert.notNull(value);
		try {
			return createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
		} catch (RuntimeException e) {
			logger.error("获取失败", e);
			throw e;
		}
	}

	/**************************************************************/
	/**************** 执行SQL语句区域 *********************************/
	/**************************************************************/
	/**************************************************************/
	@Override
	public void executeSql(String sql, Object... params) {
		sqlDao.executeSql(sql, params);
	}

	@Override
	public void executeAliasSql(String sql, Map<String, Object> alias) {
		sqlDao.executeAliasSql(sql, alias);
	}

	@Override
	public Integer countBySql(String sql, Object... params) {
		return sqlDao.countBySql(sql, params);
	}

	@Override
	public Integer countByAliasSql(String sql, Map<String, Object> alias) {
		return sqlDao.countByAliasSql(sql, alias);
	}

	@Override
	public List<Map<String, Object>> listBySql(String sql, Object... params) {
		return sqlDao.listBySql(sql, params);
	}

	@Override
	public List<Map<String, Object>> listByAliasSql(String sql, Map<String, Object> alias) {
		return sqlDao.listByAliasSql(sql, alias);
	}

	@Override
	public List<Map<String, Object>> listPageBySql(String sql, int page, int rows, Object... params) {
		return sqlDao.listPageBySql(sql, page, rows, params);
	}

	@Override
	public List<Map<String, Object>> listPageByAliasSql(String sql, int page, int rows, Map<String, Object> alias) {
		return sqlDao.listPageByAliasSql(sql, page, rows, alias);
	}

	@Override
	public <T> List<T> listEntityBySql(String sql, Class<T> entityClass, Object... params) {
		return sqlDao.listEntityBySql(sql, entityClass, params);
	}

	@Override
	public <T> List<T> listPageEntityBySql(String sql, int page, int rows, Class<T> entityClass, Object... params) {
		return sqlDao.listPageEntityBySql(sql, page, rows, entityClass, params);
	}

	@Override
	public <T> List<T> listEntityByAliasSql(String sql, Class<T> entityClass, Map<String, Object> alias) {
		return sqlDao.listEntityByAliasSql(sql, entityClass, alias);
	}

	@Override
	public <T> List<T> listPageEntityByAliasSql(String sql, int page, int rows, Class<T> entityClass,
			Map<String, Object> alias) {
		return sqlDao.listPageEntityByAliasSql(sql, page, rows, entityClass, alias);
	}

	/**************************************************************/
	/**************** 动态 SQL *********************************/
	/**************************************************************/
	/**************************************************************/
	@Override
	public int updateByHqlQueryId(String queryId, Object... params) {
		return dynamicHibernateAdapter.updateByHqlQueryId(queryId, params);
	}

	@Override
	public Long countByHqlQueryId(String queryId, Object... params) {
		return dynamicHibernateAdapter.countByHqlQueryId(queryId, params);
	}

	@Override
	public <T> List<T> listByHqlQueryId(String queryId, Object... params) {
		return dynamicHibernateAdapter.listByHqlQueryId(queryId, params);
	}

	@Override
	public <T> List<T> listByHqlQueryId(String queryId, int page, int rows, Object... params) {
		return dynamicHibernateAdapter.listByHqlQueryId(queryId, page, rows, params);
	}

	@Override
	public <T> List<T> listByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		return dynamicHibernateAdapter.listByAliasHqlQueryId(queryId, alias);
	}

	@Override
	public List<Map<String, Object>> listMapByHqlQueryId(String queryId, Object... params) {
		return dynamicHibernateAdapter.listMapByHqlQueryId(queryId, params);
	}

	@Override
	public List<Map<String, Object>> listMapByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		return dynamicHibernateAdapter.listMapByAliasHqlQueryId(queryId, alias);
	}

	@Override
	public void executeSqlQueryId(String queryId, Object... params) {
		dynamicHibernateAdapter.executeSqlQueryId(queryId, params);
	}

	@Override
	public Integer countBySqlQueryId(String queryId, Object... params) {
		return dynamicHibernateAdapter.countBySqlQueryId(queryId, params);
	}

	@Override
	public List<Map<String, Object>> listBySqlQueryId(String queryId, Object... params) {
		return dynamicHibernateAdapter.listBySqlQueryId(queryId, params);
	}

	@Override
	public List<Map<String, Object>> listByAliasSqlQueryId(String queryId, Map<String, Object> alias) {
		return dynamicHibernateAdapter.listByAliasSqlQueryId(queryId, alias);
	}

	@Override
	public List<Map<String, Object>> listPageBySqlQueryId(String queryId, int page, int rows, Object... params) {
		return dynamicHibernateAdapter.listPageBySqlQueryId(queryId, page, rows, params);
	}

	@Override
	public List<Map<String, Object>> listPageByAliasSqlQueryId(String queryId, int page, int rows,
			Map<String, Object> alias) {
		return dynamicHibernateAdapter.listPageByAliasSqlQueryId(queryId, page, rows, alias);
	}

	@Override
	public <T> List<T> listEntityBySqlQueryId(String queryId, Class<T> entityClass, Object... params) {
		return dynamicHibernateAdapter.listEntityBySqlQueryId(queryId, entityClass, params);
	}

	@Override
	public <T> List<T> listEntityByAliasSqlQueryId(String queryId, Class<T> entityClass, Map<String, Object> alias) {
		return dynamicHibernateAdapter.listEntityByAliasSqlQueryId(queryId, entityClass, alias);
	}

	@Override
	public <T> List<T> listPageEntityBySqlQueryId(String queryId, int page, int rows, Class<T> entityClass,
			Object... params) {
		return dynamicHibernateAdapter.listPageEntityBySqlQueryId(queryId, page, rows, entityClass, params);
	}

	@Override
	public <T> List<T> listPageEntityByAliasSqlQueryId(String queryId, int page, int rows, Class<T> entityClass,
			Map<String, Object> alias) {
		return dynamicHibernateAdapter.listPageEntityByAliasSqlQueryId(queryId, page, rows, entityClass, alias);
	}

	@Override
	public int updateByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		return dynamicHibernateAdapter.updateByAliasHqlQueryId(queryId, alias);
	}

	@Override
	public Long countByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		return dynamicHibernateAdapter.countByAliasHqlQueryId(queryId, alias);
	}

	@Override
	public List<Map<String, Object>> listMapByHqlQueryId(String queryId, int page, int rows, Object... params) {
		return dynamicHibernateAdapter.listMapByHqlQueryId(queryId, page, rows, params);
	}

	@Override
	public List<Map<String, Object>> listMapByAliasHqlQueryId(String queryId, int page, int rows,
			Map<String, Object> alias) {
		return dynamicHibernateAdapter.listMapByAliasHqlQueryId(queryId, page, rows, alias);
	}

}