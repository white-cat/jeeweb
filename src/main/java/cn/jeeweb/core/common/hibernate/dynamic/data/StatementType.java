package cn.jeeweb.core.common.hibernate.dynamic.data;

public enum StatementType {
	HQL_QUERY, SQL_QUERY, HQL, SQL;
	
	public static StatementType fromString(String value) {
		try {
			return StatementType.valueOf(value.toUpperCase().replace("-", "_"));
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format(
					"Invalid value '%s' for StatementType given!",
					value), e);
		}
	}
}
