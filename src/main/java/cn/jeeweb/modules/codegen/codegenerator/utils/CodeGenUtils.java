package cn.jeeweb.modules.codegen.codegenerator.utils;

import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;

import cn.jeeweb.core.utils.SpringContextHolder;

public class CodeGenUtils {
	private static String DB_TYPE_ORACL = "ORACLE";
	private static String DB_TYPE_SQLSERVER = "SQLSERVER";
	private static String DB_TYPE_POSTGRESQL = "POSTGRESQL";
	private static String DB_TYPE_MYSQL = "MYSQL";

	public static String getDbType() {
		SessionFactoryImpl sessionFactory = (SessionFactoryImpl) SpringContextHolder.getBean(SessionFactory.class);
		String dialect = sessionFactory.getDialect().getClass().getName().toUpperCase();
		String dbType = DB_TYPE_MYSQL;
		if (dialect.contains(DB_TYPE_MYSQL)) {
			dbType = DB_TYPE_MYSQL;
		} else if (dialect.contains(DB_TYPE_ORACL)) {
			dbType = DB_TYPE_ORACL;
		} else if (dialect.contains(DB_TYPE_SQLSERVER)) {
			dbType = DB_TYPE_SQLSERVER;
		} else if (dialect.contains(DB_TYPE_POSTGRESQL)) {
			dbType = DB_TYPE_POSTGRESQL;
		}
		return dbType;
	}
}
