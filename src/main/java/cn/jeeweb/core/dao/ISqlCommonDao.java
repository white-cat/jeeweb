package cn.jeeweb.core.dao;

import java.util.List;
import java.util.Map;

public interface ISqlCommonDao {

	void executeSql(String sql);

	Integer countBySql(String sql);

	List<Map<String, Object>> listBySql(String sql, int page, int rows);

	<T> List<T> listEntityBySql(String sql, int page, int rows, Class<T> entityClass);

}