package cn.jeeweb.modules.codegen.codegenerator.xml.sql;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("serial")
@XmlRootElement(name = "config")
public class ConfigXmlMap implements Serializable {

	List<SqlXmlMap> sqlXmlMapList;
	
	@XmlElementWrapper(name = "sqls")
	@XmlElements({ @XmlElement(name = "sql", type = SqlXmlMap.class) })
	public List<SqlXmlMap> getSqlXmlMapList() {
		return sqlXmlMapList;
	}

	public void setSqlXmlMapList(List<SqlXmlMap> sqlXmlMapList) {
		this.sqlXmlMapList = sqlXmlMapList;
	}


	 
}
