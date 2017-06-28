package cn.jeeweb.core.common.dao;

import java.util.List;
import java.util.Map;

public interface ISqlDao {

	void executeSql(String sql, final Object... params);
	
	void executeAliasSql(String sql, final Map<String, Object> alias);
	
	Integer countBySql(String sql, final Object... params);
	
	Integer countByAliasSql(String sql, final Map<String, Object> alias);

	List<Map<String, Object>> listBySql(String sql, final Object... params);

	List<Map<String, Object>> listByAliasSql(String sql, final Map<String, Object> alias);

	List<Map<String, Object>> listPageBySql(String sql, int page, int rows, final Object... params);

	List<Map<String, Object>> listPageByAliasSql(String sql, int page, int rows,
			final Map<String, Object> alias);

	<T> List<T> listEntityBySql(String sql, Class<T> entityClass, final Object... params);

	<T> List<T> listEntityByAliasSql(String sql, Class<T> entityClass, final Map<String, Object> alias);

	<T> List<T> listPageEntityBySql(String sql, int page, int rows, Class<T> entityClass,
			final Object... params);

	<T> List<T> listPageEntityByAliasSql(String sql, int page, int rows, Class<T> entityClass,
			final Map<String, Object> alias);

}