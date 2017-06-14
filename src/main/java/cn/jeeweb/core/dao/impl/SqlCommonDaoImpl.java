package cn.jeeweb.core.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import cn.jeeweb.core.dao.ISqlCommonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository("sqlCommonDao")
public class SqlCommonDaoImpl implements ISqlCommonDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	protected final Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void executeSql(String sql) {
		getSession().createSQLQuery(sql);
	}

	@Override
	public Integer countBySql(String sql) {
		BigInteger uniqueResult = (BigInteger) getSession().createSQLQuery(sql).uniqueResult();
		return uniqueResult.intValue();
	}

	@Override
	public List<Map<String, Object>> listBySql(String sql, int page, int rows) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

	@Override
	public <T> List<T> listEntityBySql(String sql, int page, int rows, Class<T> entityClass) {
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.addEntity(entityClass);
		query.setMaxResults(rows); // 最大显示记录数
		query.setFirstResult((page - 1) * rows); // 从第几条开始
		return query.list();
	}

}