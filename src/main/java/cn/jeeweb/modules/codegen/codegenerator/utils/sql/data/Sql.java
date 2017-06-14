package cn.jeeweb.modules.codegen.codegenerator.utils.sql.data;

import java.io.Serializable;

import cn.jeeweb.modules.codegen.codegenerator.xml.sql.SqlXmlMap;

@SuppressWarnings("serial")
public class Sql implements Serializable {
	private String id; // 主键
	private String description; // 描述
	private String content; // SQL

	public Sql() {

	}

	public Sql(SqlXmlMap sqlXmlMap) {
		this.id = sqlXmlMap.getId();
		this.description = sqlXmlMap.getDescription();
		this.content = sqlXmlMap.getContent();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}