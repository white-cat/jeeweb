package cn.jeeweb.core.common.hibernate.dynamic.adapter;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.jeeweb.core.common.hibernate.dynamic.template.TemplateToFragmentParser;

@SuppressWarnings("unchecked")
public class DynamicHibernateAdapter implements IDynamicHibernateAdapter {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public final Session getSession() {
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

	@Override
	public int updateByHqlQueryId(final String queryId, final Object... params) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId);
		Query query = getSession().createQuery(hql);
		setParameters(query, params);
		return query.executeUpdate();

	}

	@Override
	public int updateByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId);
		Query query = getSession().createQuery(hql);
		return query.executeUpdate();
	}

	@Override
	public Long countByHqlQueryId(String queryId, Object... params) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId);
		Query query = getSession().createQuery(hql);
		setParameters(query, params);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long countByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId);
		Query query = getSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public <T> List<T> listByHqlQueryId(String queryId, Object... params) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId);
		Query query = getSession().createQuery(hql);
		setParameters(query, params);
		return query.list();
	}

	@Override
	public <T> List<T> listByHqlQueryId(String queryId, int page, int rows, Object... params) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId);
		Query query = getSession().createQuery(hql);
		setParameters(query, params);
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		return query.list();
	}

	@Override
	public <T> List<T> listByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId, alias);
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listMapByHqlQueryId(String queryId, Object... params) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId);
		Query query = getSession().createQuery(hql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setParameters(query, params);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listMapByHqlQueryId(String queryId, int page, int rows, Object... params) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId);
		Query query = getSession().createQuery(hql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setParameters(query, params);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listMapByAliasHqlQueryId(String queryId, Map<String, Object> alias) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId, alias);
		Query query = getSession().createQuery(hql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listMapByAliasHqlQueryId(String queryId, int page, int rows,
			Map<String, Object> alias) {
		String hql = TemplateToFragmentParser.getFragmentParser().parseHql(queryId, alias);
		Query query = getSession().createQuery(hql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public void executeSqlQueryId(String queryId, final Object... params) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId);
		Query query = getSession().createSQLQuery(sql);
		setParameters(query, params);
		query.executeUpdate();
	}

	@Override
	public Integer countBySqlQueryId(String queryId, final Object... params) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId);
		Query query = getSession().createSQLQuery(sql);
		setParameters(query, params);
		BigInteger uniqueResult = (BigInteger) query.uniqueResult();
		return uniqueResult.intValue();
	}

	@Override
	public List<Map<String, Object>> listBySqlQueryId(String queryId, Object... params) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId);
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setParameters(query, params);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listPageBySqlQueryId(String queryId, int page, int rows, final Object... params) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId);
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setParameters(query, params);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listByAliasSqlQueryId(String queryId, Map<String, Object> alias) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId, alias);
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> listPageByAliasSqlQueryId(String queryId, int page, int rows,
			Map<String, Object> alias) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId, alias);
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public <T> List<T> listEntityBySqlQueryId(String queryId, Class<T> entityClass, Object... params) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId);
		SQLQuery query = this.getSession().createSQLQuery(sql);
		setParameters(query, params);
		query.addEntity(entityClass);
		return query.list();
	}

	@Override
	public <T> List<T> listPageEntityBySqlQueryId(String queryId, int page, int rows, Class<T> entityClass,
			final Object... params) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId);
		SQLQuery query = this.getSession().createSQLQuery(sql);
		setParameters(query, params);
		query.addEntity(entityClass);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public <T> List<T> listPageEntityByAliasSqlQueryId(String queryId, int page, int rows, Class<T> entityClass,
			Map<String, Object> alias) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId, alias);
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(entityClass);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public <T> List<T> listEntityByAliasSqlQueryId(String queryId, Class<T> entityClass, Map<String, Object> alias) {
		String sql = TemplateToFragmentParser.getFragmentParser().parseSql(queryId, alias);
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(entityClass);
		return query.list();
	}

}
