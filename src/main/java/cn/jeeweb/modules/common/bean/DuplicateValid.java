package cn.jeeweb.modules.common.bean;

/**
 * 重复验证
 * 
 * @author 王存见
 *
 */
@SuppressWarnings("serial")
public class DuplicateValid implements java.io.Serializable {

	/** 编辑数据ID */
	private String dataId;
	/**
	 * 表名
	 */
	private String table;

	/**
	 * 字段名
	 */
	private String field;

	/**
	 * 字段值
	 */
	private String vlaue;

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getVlaue() {
		return vlaue;
	}

	public void setVlaue(String vlaue) {
		this.vlaue = vlaue;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

}