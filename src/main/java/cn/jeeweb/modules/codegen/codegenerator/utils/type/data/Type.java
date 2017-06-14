package cn.jeeweb.modules.codegen.codegenerator.utils.type.data;

import java.io.Serializable;

import cn.jeeweb.modules.codegen.codegenerator.xml.type.TypeXmlMap;

public class Type implements Serializable {

	private static final long serialVersionUID = -6503628496702183110L;
	private String javaType; // JAVA类型
	private String fullType; // 类型全名
	private String dbType; // 数据库类型

	public Type() {

	}

	public Type(TypeXmlMap typeXmlMap) {
		this.dbType = typeXmlMap.getDbType();
		this.fullType = typeXmlMap.getFullType();
		this.javaType = typeXmlMap.getJavaType();
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getFullType() {
		return fullType;
	}

	public void setFullType(String fullType) {
		this.fullType = fullType;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
}