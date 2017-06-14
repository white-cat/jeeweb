package cn.jeeweb.modules.codegen.codegenerator.utils.sql;

import java.util.HashMap;
import java.util.Map;
import cn.jeeweb.core.mapper.JaxbMapper;
import cn.jeeweb.modules.codegen.codegenerator.utils.CodeGenUtils;
import cn.jeeweb.modules.codegen.codegenerator.utils.sql.data.Sql;
import cn.jeeweb.modules.codegen.codegenerator.xml.sql.ConfigXmlMap;
import cn.jeeweb.modules.codegen.codegenerator.xml.sql.SqlXmlMap;

public class SqlUtils {

	public static SqlUtils sqlUtils = null;
	private Map<String, Sql> sqlMap = new HashMap<String, Sql>();

	public static SqlUtils getSqlUtils() {
		///if (sqlUtils == null) {
			sqlUtils = new SqlUtils();
		//}
		return sqlUtils;
	}

	public SqlUtils() {
		String dbType = CodeGenUtils.getDbType().toLowerCase();
		ConfigXmlMap xmlMap = JaxbMapper.fromLocation("mapper/codegen/sql/" + dbType + ".xml", ConfigXmlMap.class);
		for (SqlXmlMap typeXmlMap : xmlMap.getSqlXmlMapList()) {
			Sql sql = new Sql(typeXmlMap);
			sqlMap.put(sql.getId(), sql);
		}
	}

	public Sql getSqlByID(String sqlId) {
		return sqlMap.get(sqlId);
	}
	
	public String getSqlContent(String sqlId,Map<String, Object> data) {
		Sql sql=sqlMap.get(sqlId);
		String content=sql.getContent();
		for (String key : data.keySet()) {
			content = content.replaceAll("\\$\\{".concat(key).concat("\\}")
	                , data.get(key).toString());
	    }
		return content;
	}

}
