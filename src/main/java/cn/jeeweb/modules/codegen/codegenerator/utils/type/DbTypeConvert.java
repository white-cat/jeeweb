package cn.jeeweb.modules.codegen.codegenerator.utils.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cn.jeeweb.core.mapper.JaxbMapper;
import cn.jeeweb.modules.codegen.codegenerator.utils.CodeGenUtils;
import cn.jeeweb.modules.codegen.codegenerator.utils.type.data.Type;
import cn.jeeweb.modules.codegen.codegenerator.xml.type.ConfigXmlMap;
import cn.jeeweb.modules.codegen.codegenerator.xml.type.TypeXmlMap;

public class DbTypeConvert implements ITypeConvert {
	public final static String TYPE_DB_TO_JAVA = "dbtojava";
	//public final static String TYPE_JAVA_TO_DB = "javatodb";
	public final static String TYPE_JAVA_TO_HIBERNATE = "javatohibernate";
	public final static String JAVA_TO_CLASS="javatoclass";
	
	public Map<String, Type> typeMap = new HashMap<String, Type>();
	public List<Type> typeList = new ArrayList<Type>();
	
	public static Map<String, DbTypeConvert> dbTypeConvertMap=new HashMap<String, DbTypeConvert>();
	public static ITypeConvert getTypeConvert(String type) {
		if (dbTypeConvertMap.containsKey(type)) {
			return dbTypeConvertMap.get(type);
		}else{
			DbTypeConvert typeConvert = new DbTypeConvert(type);
			dbTypeConvertMap.put(type, typeConvert);
			return typeConvert;
		}
	}

	public DbTypeConvert(String convertType) {
		String dbType = CodeGenUtils.getDbType().toLowerCase();
		ConfigXmlMap xmlMap = JaxbMapper.fromLocation("mapper/codegen/type/" + convertType + "/" + dbType + ".xml",
				ConfigXmlMap.class);
		for (TypeXmlMap typeXmlMap : xmlMap.getTypeXmlMapList()) {
			Type type = new Type(typeXmlMap);
			if (convertType.equals(TYPE_DB_TO_JAVA)) {
				typeMap.put(typeXmlMap.getDbType(), type);
			}else{
				typeMap.put(typeXmlMap.getJavaType(), type);
			}
			typeList.add(type);
		}
	}

	@Override
	public Type getType(String type) {
		return typeMap.get(type);
	}

	@Override
	public List<Type> getTypes() {
		return typeList;
	}

	@Override
	public List<String> getJavaTypes() {
		List<String> list = new ArrayList<String>();
		for (Map.Entry<String, Type> m : typeMap.entrySet()) {
			Type type = m.getValue();
			if (!list.contains(type.getJavaType())) {
				list.add(type.getJavaType());
			}
		}
		return list;
	}

	@Override
	public List<String> getFullTypes() {
		List<String> list = new ArrayList<String>();
		for (Map.Entry<String, Type> m : typeMap.entrySet()) {
			Type type = m.getValue();
			if (!list.contains(type.getFullType())) {
				list.add(type.getFullType());
			}
		}
		return list;
	}

	@Override
	public List<String> getDbTypes() {
		List<String> list = new ArrayList<String>();
		for (Map.Entry<String, Type> m : typeMap.entrySet()) {
			Type type = m.getValue();
			if (!list.contains(type.getDbType())) {
				list.add(type.getDbType());
			}
		}
		return list;
	}

}
