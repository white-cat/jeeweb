package cn.jeeweb.core.common.hibernate.dynamic.builder;

import java.io.IOException;
import java.util.Map;

import com.google.common.collect.Maps;

public class NoneDynamicHibernateStatementBuilder implements DynamicHibernateStatementBuilder {

	@Override
	public Map<String, String> getNamedHQLQueries() {
		return Maps.newHashMap();
	}

	@Override
	public Map<String, String> getNamedSQLQueries() {
		return Maps.newHashMap();
	}

	@Override
	public void init() throws IOException {

	}

}
