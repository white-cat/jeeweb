package cn.jeeweb.core.common.hibernate.dynamic.adapter;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface IDynamicHibernateAdapter {

	Session getSession();

	int updateByHqlQueryId(final String queryId, final Object... params);
	
	int updateByAliasHqlQueryId(final String queryId, final Map<String, Object> alias);

	Long countByHqlQueryId(final String queryId, final Object... params);
	
	Long countByAliasHqlQueryId(final String queryId, final Map<String, Object> alias);

	<T> List<T> listByHqlQueryId(final String queryId, final Object... params);
	
	<T> List<T> listByAliasHqlQueryId(final String queryId, final Map<String, Object> alias);

	<T> List<T> listByHqlQueryId(final String queryId, int page, int rows, final Object... params);

	List<Map<String, Object>> listMapByHqlQueryId(final String queryId, final Object... params);
	
	List<Map<String, Object>> listMapByHqlQueryId(final String queryId,int page, int rows, final Object... params);

	List<Map<String, Object>> listMapByAliasHqlQueryId(final String queryId, final Map<String, Object> alias);
	
	List<Map<String, Object>> listMapByAliasHqlQueryId(final String queryId, int page, int rows,final Map<String, Object> alias);

	void executeSqlQueryId(String queryId, final Object... params);

	Integer countBySqlQueryId(String queryId, final Object... params);

	List<Map<String, Object>> listBySqlQueryId(String queryId, final Object... params);

	List<Map<String, Object>> listByAliasSqlQueryId(String queryId, final Map<String, Object> alias);

	List<Map<String, Object>> listPageBySqlQueryId(String queryId, int page, int rows, final Object... params);

	List<Map<String, Object>> listPageByAliasSqlQueryId(String queryId, int page, int rows,
			final Map<String, Object> alias);

	<T> List<T> listEntityBySqlQueryId(String queryId, Class<T> entityClass, final Object... params);

	<T> List<T> listEntityByAliasSqlQueryId(String queryId, Class<T> entityClass, final Map<String, Object> alias);

	<T> List<T> listPageEntityBySqlQueryId(String queryId, int page, int rows, Class<T> entityClass,
			final Object... params);

	<T> List<T> listPageEntityByAliasSqlQueryId(String queryId, int page, int rows, Class<T> entityClass,
			final Map<String, Object> alias);

}