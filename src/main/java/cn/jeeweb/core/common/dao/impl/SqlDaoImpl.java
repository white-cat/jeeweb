package cn.jeeweb.core.common.dao.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import cn.jeeweb.core.common.dao.ISqlDao;

@Repository("sqlDao")
@SuppressWarnings("unchecked")
public class SqlDaoImpl implements ISqlDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	protected final Session getSession() {
		return sessionFactory.getCurrentSession();
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

	/**
	 * 设置别名参数
	 *
	 * @param query
	 * @param params
	 */
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
	public void executeSql(String sql, final Object... params) {
		Query query = getSession().createSQLQuery(sql);
		setParameters(query, params);
		query.executeUpdate();
	}

	@Override
	public void executeAliasSql(String sql, final Map<String, Object> alias) {
		Query query = getSession().createSQLQuery(sql);
		setAliasParameter(query, alias);
		query.executeUpdate();
	}

	@Override
	public Integer countBySql(String sql, final Object... params) {
		Query query = getSession().createSQLQuery(sql);
		setParameters(query, params);
		BigInteger uniqueResult = (BigInteger) query.uniqueResult();
		return uniqueResult.intValue();
	}

	@Override
	public Integer countByAliasSql(String sql, final Map<String, Object> alias) {
		Query query = getSession().createSQLQuery(sql);
		setAliasParameter(query, alias);
		BigInteger uniqueResult = (BigInteger) query.uniqueResult();
		return uniqueResult.intValue();
	}

	@Override
	public List<Map<String, Object>> listBySql(String sql, Object... params) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setParameters(query, params);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listPageBySql(String sql, int page, int rows, final Object... params) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setParameters(query, params);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listByAliasSql(String sql, Map<String, Object> alias) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setAliasParameter(query, alias);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listPageByAliasSql(String sql, int page, int rows, Map<String, Object> alias) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setAliasParameter(query, alias);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public <T> List<T> listEntityBySql(String sql, Class<T> entityClass, Object... params) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		setParameters(query, params);
		query.addEntity(entityClass);
		return query.list();
	}

	@Override
	public <T> List<T> listPageEntityBySql(String sql, int page, int rows, Class<T> entityClass,
			final Object... params) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		setParameters(query, params);
		query.addEntity(entityClass);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public <T> List<T> listPageEntityByAliasSql(String sql, int page, int rows, Class<T> entityClass,
			Map<String, Object> alias) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		setAliasParameter(query, alias);
		query.addEntity(entityClass);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public <T> List<T> listEntityByAliasSql(String sql, Class<T> entityClass, Map<String, Object> alias) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		setAliasParameter(query, alias);
		query.addEntity(entityClass);
		return query.list();
	}

}