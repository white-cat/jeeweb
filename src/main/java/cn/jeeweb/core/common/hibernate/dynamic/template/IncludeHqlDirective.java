package cn.jeeweb.core.common.hibernate.dynamic.template;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import cn.jeeweb.core.common.hibernate.dynamic.data.Config;
import cn.jeeweb.core.common.hibernate.dynamic.data.StatementType;
import cn.jeeweb.core.common.hibernate.dynamic.exception.DynamicException;
import cn.jeeweb.core.common.hibernate.dynamic.utils.Utils;
import cn.jeeweb.core.utils.EhcacheUtil;
import cn.jeeweb.core.utils.StringUtils;
import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class IncludeHqlDirective implements TemplateDirectiveModel {
	protected final static EhcacheUtil ehcacheUtil = new EhcacheUtil(Config.DYNAMIC_CACHE);

	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		String statement = "";
		env.getOut().write(" ");//保证导入后两边空格
		if (params.get("refid") != null) {
			String statementId = ((SimpleScalar) params.get("refid")).getAsString();
			String templateKey = Utils.getCacheKeyByType(statementId, StatementType.HQL);
			statement = ehcacheUtil.getString(templateKey);
			if (StringUtils.isEmpty(statement)) {
				throw new DynamicException("请检查" + statementId + "的HQL是否已经定义");
			}
			env.include(new Template(statementId, new StringReader(statement), env.getConfiguration()));
		} else {
			throw new DynamicException("SQLinclude引用的ID未定义");
		}
		env.getOut().write(" ");//保证导入后两边空格
	}
}