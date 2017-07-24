package cn.jeeweb.modules.codegen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import cn.jeeweb.core.common.entity.DataEntity;

/**
 * @Title: 生成方案
 * @Description: 代码生成方案
 * @author jeeweb
 * @date 2017-05-29 21:17:42
 * @version V1.0
 *
 */
@Entity
@javax.persistence.Table(name = "codegen_scheme")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class Scheme extends DataEntity<String> {

	/** 生成模块名 */
	private String moduleName;

	private String id;
	/** 生成子模块名 */
	private String subModuleName;

	/** 生成包路径 */
	private String packageName;

	/** 表类型 */
	private String tableType;

	/** 生成功能作者 */
	private String functionAuthor;
	/** 生成功能名（简写） */
	private String functionDesc;
	/** 生成表编号 */
	// 表的ID
	private Table table;
	private String entityName = ""; // 实体名
	private String tableName = ""; // 实体名
	private String functionName;
	private String pathName;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32, scale = 0)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 moduleName
	 * 
	 * @return: String 生成模块名
	 */
	@Column(name = "module_name", nullable = true, length = 30, scale = 0)
	public String getModuleName() {
		return this.moduleName;
	}

	/**
	 * 设置 moduleName
	 * 
	 * @param: moduleName
	 *             生成模块名
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * 获取 subModuleName
	 * 
	 * @return: String 生成子模块名
	 */
	@Column(name = "sub_module_name", nullable = true, length = 30, scale = 0)
	public String getSubModuleName() {
		return this.subModuleName;
	}

	/**
	 * 设置 subModuleName
	 * 
	 * @param: subModuleName
	 *             生成子模块名
	 */
	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	/**
	 * 获取 packageName
	 * 
	 * @return: String 生成包路径
	 */
	@Column(name = "package_name", nullable = true, length = 500, scale = 0)

	public String getPackageName() {
		return this.packageName;
	}

	/**
	 * 设置 packageName
	 * 
	 * @param: packageName
	 *             生成包路径
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * 
	 * 
	 * /** 获取 tableType
	 * 
	 * @return: String 表类型
	 */
	@Column(name = "table_type", nullable = true, length = 2000, scale = 0)

	public String getTableType() {
		return this.tableType;
	}

	/**
	 * 设置 tableType
	 * 
	 * @param: tableType
	 *             表类型
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	/**
	 * 获取 functionAuthor
	 * 
	 * @return: String 生成功能作者
	 */
	@Column(name = "function_author", nullable = true, length = 100, scale = 0)
	public String getFunctionAuthor() {
		return this.functionAuthor;
	}

	/**
	 * 设置 functionAuthor
	 * 
	 * @param: functionAuthor
	 *             生成功能作者
	 */
	public void setFunctionAuthor(String functionAuthor) {
		this.functionAuthor = functionAuthor;
	}

	/**
	 * 获取 functionDesc
	 * 
	 * @return: String 生成功能名（简写）
	 */
	@Column(name = "function_desc", nullable = true, length = 100, scale = 0)
	public String getFunctionDesc() {
		return this.functionDesc;
	}

	/**
	 * 设置 functionDesc
	 * 
	 * @param: functionDesc
	 *             生成功能名（简写）
	 */
	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	/**
	 * 获取 functionName
	 * 
	 * @return: String 生成功能名
	 */
	/** 生成功能名 */
	@Column(name = "function_name", nullable = true, length = 500, scale = 0)
	public String getFunctionName() {
		return this.functionName;
	}

	/**
	 * 设置 functionName
	 * 
	 * @param: functionName
	 *             生成功能名
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * 获取 pathName
	 * 
	 * @return: String 代码生成目录
	 */
	@Column(name = "path_name", nullable = true, length = 2000, scale = 0)
	public String getPathName() {
		return this.pathName;
	}

	/**
	 * 设置 pathName
	 * 
	 * @param: pathName
	 *             代码生成目录
	 */
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "table_id", updatable = false, insertable = true)
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	@Column(name = "entity_name", nullable = true, length = 100, scale = 0)
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	@Column(name = "table_name", nullable = true, length = 100, scale = 0)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
