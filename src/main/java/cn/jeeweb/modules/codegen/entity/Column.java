package cn.jeeweb.modules.codegen.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import cn.jeeweb.core.common.entity.DataEntity;
import cn.jeeweb.core.utils.StringUtils;
import cn.jeeweb.modules.codegen.codegenerator.data.DbColumnInfo;
import cn.jeeweb.modules.codegen.codegenerator.utils.type.DbTypeConvert;
import cn.jeeweb.modules.codegen.codegenerator.utils.type.data.Type;

import java.lang.String;

@Entity
@javax.persistence.Table(name = "codegen_column", schema = "")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class Column extends DataEntity<String> implements java.io.Serializable {

	private String id;
	// 表的ID
	private Table table;
	// 获得字段名称
	private String columnName = "";
	// 获得字段类型名称
	private String typeName = "";
	// 获得字段大小
	private String columnSize = "";

	// 是否为主键
	private Boolean parmaryKey = false;
	// 是否为外键
	private Boolean importedKey = false;
	// 是否允许为空
	private Boolean nullable = false;
	// 默认值
	private String columnDef = "";
	// 小数部分的位数
	private String decimalDigits = "";

	/** 页面属性 */
	// JAVA类型
	private String javaType;
	// JAVA字段名
	private String javaField;
	// 是否列表显示
	private Boolean isList;
	// 是否查询字段
	private Boolean isQuery;
	// 查询方式
	private String queryType;
	// 查询模式
	private String queryModel;

	// 是否表单显示
	private Boolean isForm;
	// 显示表单类型
	private String formType;
	// 字段生成方案
	private String inputType;
	// 字典分组
	private String dictGroup;

	private Integer sort;

	/* 验证 */
	// 校验类型
	private String validType;
	// 验证规则
	private String regexValid;
	// 最大长度
	private Integer maxSize;
	// 最小长度
	private Integer minSize;
	// 最大值
	private String maxValue;
	// 最小值
	private String minValue;
	// 为空提示
	private String nullmsg;

	private String hibernateType;
	// 关联表
	private String foreignTable;

	public Column() {

	}

	public Column(DbColumnInfo dbColumnInfo) {
		this.columnName = dbColumnInfo.getColumnName().toLowerCase();
		this.remarks = dbColumnInfo.getRemarks();
		this.typeName = dbColumnInfo.getTypeName().toLowerCase();
		if (StringUtils.isEmpty(dbColumnInfo.getColumnSize())) {
			this.columnSize = "1";
		} else {
			this.columnSize = dbColumnInfo.getColumnSize();
		}
		this.nullable = dbColumnInfo.isNullable();
		this.parmaryKey = dbColumnInfo.isParmaryKey();
		this.importedKey = dbColumnInfo.isImportedKey();
		this.columnDef = dbColumnInfo.getColumnDef();
		this.decimalDigits = StringUtils.isEmpty(dbColumnInfo.getDecimalDigits()) ? "0"
				: dbColumnInfo.getDecimalDigits();

		Type type = DbTypeConvert.getTypeConvert(DbTypeConvert.TYPE_DB_TO_JAVA).getType(dbColumnInfo.getTypeName());
		if (type != null) {
			this.javaType = type.getJavaType();
		} else {
			this.javaType = "String";
		}
		this.javaField = StringUtils.underlineToCamel(this.columnName);
		this.isList = Boolean.TRUE;
		this.isQuery = Boolean.FALSE;
		this.queryType = "eq";
		this.isForm = Boolean.TRUE;
		this.formType = "input";
	}

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@javax.persistence.Column(name = "id", nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "table_id", updatable = false, insertable = true)
	@JsonIgnore
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	@javax.persistence.Column(name = "column_name")
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@javax.persistence.Column(name = "type_name")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@javax.persistence.Column(name = "column_size")
	public String getColumnSize() {
		return columnSize;
	}

	public void setColumnSize(String columnSize) {
		this.columnSize = columnSize;
	}

	@javax.persistence.Column(name = "parmary_key")
	public Boolean getParmaryKey() {
		return parmaryKey;
	}

	public void setParmaryKey(Boolean parmaryKey) {
		this.parmaryKey = parmaryKey;
	}

	@javax.persistence.Column(name = "imported_key")
	public Boolean getImportedKey() {
		return importedKey;
	}

	public void setImportedKey(Boolean importedKey) {
		this.importedKey = importedKey;
	}

	@javax.persistence.Column(name = "is_nullable")
	public Boolean getNullable() {
		return nullable;
	}

	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	@javax.persistence.Column(name = "column_def")
	public String getColumnDef() {
		return columnDef;
	}

	public void setColumnDef(String columnDef) {
		this.columnDef = columnDef;
	}

	@javax.persistence.Column(name = "decimal_digits")
	public String getDecimalDigits() {
		return decimalDigits;
	}

	public void setDecimalDigits(String decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	@javax.persistence.Column(name = "java_type")
	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	@javax.persistence.Column(name = "java_field")
	public String getJavaField() {
		return javaField;
	}

	public void setJavaField(String javaField) {
		this.javaField = javaField;
	}

	@javax.persistence.Column(name = "is_list")
	public Boolean getIsList() {
		return isList;
	}

	public void setIsList(Boolean isList) {
		this.isList = isList;
	}

	@javax.persistence.Column(name = "is_query")
	public Boolean getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(Boolean isQuery) {
		this.isQuery = isQuery;
	}

	@javax.persistence.Column(name = "query_type")
	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	@javax.persistence.Column(name = "is_form")
	public Boolean getIsForm() {
		return isForm;
	}

	public void setIsForm(Boolean isForm) {
		this.isForm = isForm;
	}

	@javax.persistence.Column(name = "input_type")
	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	@javax.persistence.Column(name = "dict_group")
	public String getDictGroup() {
		return dictGroup;
	}

	public void setDictGroup(String dictGroup) {
		this.dictGroup = dictGroup;
	}

	@javax.persistence.Column(name = "sort")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@javax.persistence.Column(name = "query_model")
	public String getQueryModel() {
		return queryModel;
	}

	public void setQueryModel(String queryModel) {
		this.queryModel = queryModel;
	}

	@javax.persistence.Column(name = "valid_type")
	public String getValidType() {
		return validType;
	}

	public void setValidType(String validType) {
		this.validType = validType;
	}

	@javax.persistence.Column(name = "max_size")
	public Integer getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}

	@javax.persistence.Column(name = "min_size")
	public Integer getMinSize() {
		return minSize;
	}

	public void setMinSize(Integer minSize) {
		this.minSize = minSize;
	}

	@javax.persistence.Column(name = "max_value")
	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	@javax.persistence.Column(name = "min_value")
	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	@javax.persistence.Column(name = "nullmsg")
	public String getNullmsg() {
		return nullmsg;
	}

	public void setNullmsg(String nullmsg) {
		this.nullmsg = nullmsg;
	}

	@javax.persistence.Column(name = "regex_valid")
	public String getRegexValid() {
		return regexValid;
	}

	public void setRegexValid(String regexValid) {
		this.regexValid = regexValid;
	}

	@javax.persistence.Column(name = "form_type")
	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	@Transient
	public String getHibernateType() {
		Type type = DbTypeConvert.getTypeConvert(DbTypeConvert.TYPE_JAVA_TO_HIBERNATE).getType(this.javaType);
		if (type != null) {
			hibernateType = type.getDbType();
		} else {
			hibernateType = "java.lang.String";
		}
		return hibernateType;
	}

	@javax.persistence.Column(name = "foreign_table")
	public String getForeignTable() {
		return foreignTable;
	}

	public void setForeignTable(String foreignTable) {
		this.foreignTable = foreignTable;
	}

}
