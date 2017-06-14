package cn.jeeweb.core.common.hibernate.dynamic;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import cn.jeeweb.core.common.hibernate.dynamic.data.Constant;
import cn.jeeweb.core.common.hibernate.dynamic.data.StatementTemplate;
import cn.jeeweb.core.common.hibernate.dynamic.exception.DynamicException;
/**
 * 
 * All rights Reserved, Designed By www.jeeweb.cn
 * @title:  DynamicStatementUtils.java   
 * @package cn.jeeweb.core.common.hibernate.dynamic   
 * @description:    TODO(用一句话描述该文件做什么)   
 * @author: 王存见   
 * @date:   2017年5月18日 上午8:58:22   
 * @version V1.0 
 * @copyright: 2017 www.jeeweb.cn Inc. All rights reserved. 
 *
 */
public class DynamicStatementUtils {
	/**
	 * 通过别名获得SQL
	 * 
	 * @title: getSqlBy
	 * @description: 通过别名获得SQL
	 * @return: String
	 */
	public static String getSql(String sqlId, Map<String, ?> parameters) {
		StatementTemplate statementTemplate = Constant.templateCache.get(sqlId);
		if (statementTemplate!=null&&statementTemplate.getType() == StatementTemplate.TYPE.SQL) {
			String statement = processTemplate(statementTemplate, parameters);
			return statement;
		} else {
			return "";
		}
	}
	public static String getSql(String sqlId) {
		Map<String, Object> parameters=new HashMap<String, Object>();
		return getSql(sqlId,parameters);
	}
	
	/**
	 * 通过别名获得SQL
	 * 
	 * @title: getSqlBy
	 * @description: 通过别名获得SQL
	 * @return: String
	 */
	public static String getHql(String hqlId, Map<String, ?> parameters) {
		StatementTemplate statementTemplate = Constant.templateCache.get(hqlId);
		if (statementTemplate!=null&&statementTemplate.getType() == StatementTemplate.TYPE.HQL) {
			String statement = processTemplate(statementTemplate, parameters);
			return statement;
		} else {
			return "";
		}
	}
	
	public static String getHql(String hqlId) {
		Map<String, Object> parameters=new HashMap<String, Object>();
		return getHql(hqlId,parameters);
	}

	/**
	 * 通过别名获得SQL
	 * 
	 * @title: getSqlBy
	 * @description: 通过别名获得SQL
	 * @return: String
	 */
	public static StatementTemplate getStatement(String statementId, Map<String, ?> parameters) {
		return  Constant.templateCache.get(statementId);
	}

	public static String processTemplate(StatementTemplate statementTemplate, Map<String, ?> parameters) {
		StringWriter stringWriter = new StringWriter();
		try {
			statementTemplate.getTemplate().process(parameters, stringWriter);
		} catch (Exception e) {
			// LOGER.error("处理DAO查询参数模板时发生错误：{}",e.toString());
			throw new DynamicException(e);
		}
		return stringWriter.toString();
	}
}
