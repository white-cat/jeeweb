package cn.jeeweb.core.common.hibernate.dialect;

import java.sql.Types;

public class SQLServer2008Dialect extends org.hibernate.dialect.SQLServer2008Dialect {
	private static final int MAX_LENGTH = 8000;

	public SQLServer2008Dialect() {
		registerColumnType(Types.CLOB, "nvarchar(MAX)");
		registerColumnType(Types.LONGVARCHAR, "nvarchar(MAX)");
		registerColumnType(Types.VARCHAR, "nvarchar(MAX)");
		registerColumnType(Types.VARCHAR, MAX_LENGTH, "nvarchar($l)");
	}
}
