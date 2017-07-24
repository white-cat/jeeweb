package ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import cn.jeeweb.core.common.entity.AbstractEntity;
<#list importTypes as importType>
import ${importType};
</#list>

/**   
 * @Title: ${functionName}
 * @Description: ${functionDesc}
 * @author ${functionAuthor}
 * @date ${time}
 * @version V1.0   
 *
 */
@Entity
@Table(name = "${tableName}")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class ${entityName?cap_first} extends AbstractEntity<String> {

    <#list attributeInfos as attributeInfo>
    /**${attributeInfo.remarks}*/
	private <#if attributeInfo.type=='this'>${entityName?cap_first}<#else>${attributeInfo.type}</#if> ${attributeInfo.name};
	</#list>
	<#list attributeInfos as attributeInfo>
	/**
	 * 获取  ${attributeInfo.name}
	 *@return: ${attributeInfo.type}  ${attributeInfo.remarks}
	 */
	 <#if attributeInfo.parmaryKey>
	
	<#if generatorType == 'uuid'>
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	</#if>
	<#if generatorType == 'identity'>
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	</#if>
	<#if generatorType == 'sequence'>
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="sequence")
	@SequenceGenerator(name="sequence",sequenceName="${jeecg_sequence_code}",allocationSize=1)
	</#if>
	</#if>
	<#if attributeInfo.importedKey>
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "${attributeInfo.dbName}")
	<#else>
	@Column(name ="${attributeInfo.dbName}",nullable=<#if attributeInfo.nullable>true<#else>false</#if><#if attributeInfo.length?exists><#if attributeInfo.length != ''>,length=${attributeInfo.length}</#if></#if><#if attributeInfo.decimalDigits?exists><#if attributeInfo.decimalDigits != ''>,scale=${attributeInfo.decimalDigits}</#if></#if><#if attributeInfo.precision != ''>,precision=${attributeInfo.precision}</#if>)
	</#if>
	public <#if attributeInfo.type=='this'>${entityName?cap_first}<#else>${attributeInfo.type}</#if> get${attributeInfo.name?cap_first}(){
		return this.${attributeInfo.name};
	}

	/**
	 * 设置  ${attributeInfo.name}
	 *@param: ${attributeInfo.name}  ${attributeInfo.remarks}
	 */
	public void set${attributeInfo.name?cap_first}(<#if attributeInfo.type=='this'>${entityName?cap_first}<#else>${attributeInfo.type}</#if> ${attributeInfo.name}){
		this.${attributeInfo.name} = ${attributeInfo.name};
	}
	</#list>
	
}
