package cn.jeeweb.core.common.hibernate.dynamic.utils;

import cn.jeeweb.core.common.hibernate.dynamic.data.StatementType;

public class Utils {
	public static String getCacheKeyByType(String statementId, StatementType type) {
		String key = "[KEY_" + type + "_DYNAMIC" + statementId + "]";
		return key.toLowerCase();
	}

	public static void main(String[] args) {
		System.out.println(Utils.getCacheKeyByType("test", StatementType.HQL_QUERY));
	}
}
