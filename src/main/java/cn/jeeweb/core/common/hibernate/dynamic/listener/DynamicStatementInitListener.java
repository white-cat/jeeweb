package cn.jeeweb.core.common.hibernate.dynamic.listener;

import java.io.StringReader;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import cn.jeeweb.core.common.hibernate.dynamic.builder.DynamicHibernateStatementBuilder;
import cn.jeeweb.core.common.hibernate.dynamic.builder.NoneDynamicHibernateStatementBuilder;
import cn.jeeweb.core.common.hibernate.dynamic.data.Constant;
import cn.jeeweb.core.common.hibernate.dynamic.data.StatementTemplate;
import cn.jeeweb.core.utils.SpringContextHolder;

public class DynamicStatementInitListener implements ApplicationListener<ContextRefreshedEvent> {

	protected DynamicHibernateStatementBuilder dynamicStatementBuilder = SpringContextHolder.getApplicationContext()
			.getBean(DynamicHibernateStatementBuilder.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			if (this.dynamicStatementBuilder == null) {
				this.dynamicStatementBuilder = new NoneDynamicHibernateStatementBuilder();
			}
			dynamicStatementBuilder.init();
			Map<String, String> namedHQLQueries = dynamicStatementBuilder.getNamedHQLQueries();
			Map<String, String> namedSQLQueries = dynamicStatementBuilder.getNamedSQLQueries();
			Configuration configuration = new Configuration();
			configuration.setNumberFormat("#");
			StringTemplateLoader stringLoader = new StringTemplateLoader();
			for (Entry<String, String> entry : namedHQLQueries.entrySet()) {
				stringLoader.putTemplate(entry.getKey(), entry.getValue());
				Constant.templateCache.put(entry.getKey(), new StatementTemplate(StatementTemplate.TYPE.HQL,
						new Template(entry.getKey(), new StringReader(entry.getValue()), configuration)));
			}
			for (Entry<String, String> entry : namedSQLQueries.entrySet()) {
				stringLoader.putTemplate(entry.getKey(), entry.getValue());
				Constant.templateCache.put(entry.getKey(), new StatementTemplate(StatementTemplate.TYPE.SQL,
						new Template(entry.getKey(), new StringReader(entry.getValue()), configuration)));
			}
			configuration.setTemplateLoader(stringLoader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}