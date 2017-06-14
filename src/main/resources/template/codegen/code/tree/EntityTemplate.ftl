package ${packageName}<#if moduleName?exists><#if moduleName!=''>.${moduleName}</#if></#if>.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import cn.jeeweb.modules.common.entity.TreeEntity;

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
public class ${entityName?cap_first}Entity extends TreeEntity {
    // 这里需要设置CascadeType.ALL，否则无法保存
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "parent_id", nullable = true, updatable = false, insertable = false)
	private ${entityName?cap_first}Entity parent;
	/**
	 * 是否有叶子节点
	 */
	@Formula(value = "(select count(*) from ${tableName} f_t where f_t.parent_id = id)")
	private boolean hasChildren;
	
    <#list attributeInfos as attributeInfo>
	<#if attributeInfo.name!='id'&&attributeInfo.name!='name'&& attributeInfo.name!='parentId'&& attributeInfo.name!='parentIds'>
	/**
	 * ${attributeInfo.remarks}
	 */
	@Column(name ="${attributeInfo.dbName}",nullable=<#if attributeInfo.nullable>true<#else>false</#if><#if attributeInfo.length?exists><#if attributeInfo.length != ''>,length=${attributeInfo.length}</#if></#if><#if attributeInfo.decimalDigits?exists><#if attributeInfo.decimalDigits != ''>,scale=${attributeInfo.decimalDigits}</#if></#if><#if attributeInfo.precision != ''>,precision=${attributeInfo.precision}</#if>)
	private ${attributeInfo.type} ${attributeInfo.name};
	</#if>
	</#list>
	
	@Override
	public boolean isHasChildren() {
		return hasChildren;
	}

	@Override
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public ${entityName?cap_first}Entity getParent() {
		return parent;
	}

	public void setParent(${entityName?cap_first}Entity parent) {
		this.parent = parent;
	}
	
	<#list attributeInfos as attributeInfo>
	<#if attributeInfo.name!='id'&&attributeInfo.name!='name'&& attributeInfo.name!='parentId'&& attributeInfo.name!='parentIds'>
	/**
	 * 获取  ${attributeInfo.name}
	 *@return: ${attributeInfo.type}  ${attributeInfo.remarks}
	 */
	public ${attributeInfo.type} get${attributeInfo.name?cap_first}(){
		return this.${attributeInfo.name};
	}

	/**
	 * 设置  ${attributeInfo.name}
	 *@param: ${attributeInfo.name}  ${attributeInfo.remarks}
	 */
	public void set${attributeInfo.name?cap_first}(${attributeInfo.type} ${attributeInfo.name}){
		this.${attributeInfo.name} = ${attributeInfo.name};
	}
	</#if>
	</#list>
	
}
