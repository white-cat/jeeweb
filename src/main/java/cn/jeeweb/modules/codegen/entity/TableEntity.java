package cn.jeeweb.modules.codegen.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.jeeweb.core.common.entity.DataEntity;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: CodegenTableEntity
 * @Description:代码生成的字段信息
 * @author: 王存见
 * @date: 2017年2月27日 下午5:35:23
 * 
 * @Copyright: 2017 www.jeeweb Inc. All rights reserved.
 *
 */
@Entity
@Table(name = "codegen_table", schema = "")
@SuppressWarnings("serial")
public class TableEntity extends DataEntity<String> implements java.io.Serializable {

	private String id;

	private String title;

	private String tableName;

	private String className;

	private String tableType;

	private String tablePKType;

	private Boolean syncDatabase;

	private String parentField;

	@JsonIgnore
	private List<ColumnEntity> columns = new ArrayList<ColumnEntity>();

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/** 实体名称 */
	@Column(name = "title", nullable = true, length = 200)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "table_name", nullable = true, length = 200)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Column(name = "class_name", nullable = true, length = 200)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "table_type", nullable = true, length = 200)
	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "table")
	@Fetch(FetchMode.SELECT)
	@OrderBy("sort asc")
	public List<ColumnEntity> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnEntity> columns) {
		this.columns = columns;
	}

	@Column(name = "table_pk_type", nullable = true, length = 5)
	public String getTablePKType() {
		return tablePKType;
	}

	public void setTablePKType(String tablePKType) {
		this.tablePKType = tablePKType;
	}

	@Column(name = "sync_database", nullable = true, length = 1)
	public Boolean getSyncDatabase() {
		return syncDatabase;
	}

	public void setSyncDatabase(Boolean syncDatabase) {
		this.syncDatabase = syncDatabase;
	}

	@Transient
	public String getParentField() {
		return parentField;
	}

	public void setParentField(String parentField) {
		this.parentField = parentField;
	}

}
