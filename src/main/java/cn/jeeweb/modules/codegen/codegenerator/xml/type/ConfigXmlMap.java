package cn.jeeweb.modules.codegen.codegenerator.xml.type;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

import cn.jeeweb.core.mapper.JaxbMapper;
import cn.jeeweb.core.mapper.JsonMapper;

@SuppressWarnings("serial")
@XmlRootElement(name = "config")
public class ConfigXmlMap implements Serializable {

	List<TypeXmlMap> typeXmlMapList;

	@XmlElementWrapper(name = "types")
	@XmlElements({ @XmlElement(name = "type", type = TypeXmlMap.class) })
	public List<TypeXmlMap> getTypeXmlMapList() {
		return typeXmlMapList;
	}

	public void setTypeXmlMapList(List<TypeXmlMap> typeXmlMapList) {
		this.typeXmlMapList = typeXmlMapList;
	}

	public static void main(String[] args) {
		ConfigXmlMap xmlMap = JaxbMapper.fromLocation("mapper/codegen/javatohibernate/mysql.xml", ConfigXmlMap.class);
		System.out.println(JsonMapper.toJsonString(xmlMap));
	}
}
