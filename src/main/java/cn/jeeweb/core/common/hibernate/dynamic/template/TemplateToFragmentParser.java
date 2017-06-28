package cn.jeeweb.core.common.hibernate.dynamic.template;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import cn.jeeweb.core.common.hibernate.dynamic.data.Config;
import cn.jeeweb.core.common.hibernate.dynamic.data.StatementType;
import cn.jeeweb.core.common.hibernate.dynamic.exception.DynamicException;
import cn.jeeweb.core.common.hibernate.dynamic.utils.Utils;
import cn.jeeweb.core.utils.EhcacheUtil;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class TemplateToFragmentParser {
	private Configuration configuration;
	private static TemplateToFragmentParser templateToFragmentParser = new TemplateToFragmentParser();
	private Map<String, Template> templateCache = new HashMap<String, Template>();
	protected final static EhcacheUtil ehcacheUtil = new EhcacheUtil(Config.DYNAMIC_CACHE);

	public static TemplateToFragmentParser getFragmentParser() {
		//if (templateToFragmentParser == null) {
			templateToFragmentParser = new TemplateToFragmentParser();
		//}
		return templateToFragmentParser;
	}

	public TemplateToFragmentParser() {
		configuration = new Configuration();
		configuration.setNumberFormat("#");
		StringTemplateLoader stringLoader = new StringTemplateLoader();
		configuration.setTemplateLoader(stringLoader);
		configuration.setSharedVariable("include_hql", new IncludeHqlDirective());
		configuration.setSharedVariable("include_sql", new IncludeSqlDirective());
	}

	public String parse(String statementId, StatementType type, Map<String, Object> parameters) {
		try {
			String templateKey = Utils.getCacheKeyByType(statementId, type);
			Template template = null;
			if (templateCache.containsKey(templateKey)) {
				template = templateCache.get(templateKey);
			} else {
				String statement = ehcacheUtil.getString(templateKey);
				template = new Template(templateKey, new StringReader(statement), configuration);
			}
			StringWriter stringWriter = new StringWriter();
			template.process(parameters, stringWriter);
			return stringWriter.toString();
		} catch (Exception e) {
			throw new DynamicException(e);
		}

	}

	public String parseHql(String statementId, Map<String, Object> parameters) {
		return this.parse(statementId, StatementType.HQL_QUERY, parameters);
	}

	public String parseSql(String statementId, Map<String, Object> parameters) {
		return this.parse(statementId, StatementType.SQL_QUERY, parameters);
	}

	public String parseHql(String statementId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		return this.parse(statementId, StatementType.HQL_QUERY, parameters);
	}

	public String parseSql(String statementId) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		return this.parse(statementId, StatementType.SQL_QUERY, parameters);
	}

}
