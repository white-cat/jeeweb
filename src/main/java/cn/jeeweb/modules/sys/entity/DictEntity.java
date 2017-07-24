package cn.jeeweb.modules.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import cn.jeeweb.core.common.entity.DataEntity;

import java.lang.String;
import java.lang.Integer;

@Entity
@Table(name = "sys_dict", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class DictEntity extends DataEntity<String> {
	/** 主键 */
	private String id;
	/** 分组ID */
	private String gid;
	/** 键值名称 */
	private String label;
	/** 值 */
	private String value;
	/** 排序 */
	private Integer sort;

	private DictGroup dictGroup;

	/**
	 * 获取 id
	 * 
	 * @return: String 主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 id
	 * 
	 * @param: id
	 *             主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 gid
	 * 
	 * @return: String 分组ID
	 */
	@Column(name = "gid", nullable = true, length = 32)
	public String getGid() {
		return this.gid;
	}

	/**
	 * 设置 gid
	 * 
	 * @param: gid
	 *             分组ID
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * 获取 name
	 * 
	 * @return: String 键值名称
	 */
	@Column(name = "label", nullable = true, length = 100)
	public String getLabel() {
		return this.label;
	}

	/**
	 * 设置 name
	 * 
	 * @param: name
	 *             键值名称
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 获取 value
	 * 
	 * @return: String 值
	 */
	@Column(name = "value", nullable = true, length = 100)
	public String getValue() {
		return this.value;
	}

	/**
	 * 设置 value
	 * 
	 * @param: value
	 *             值
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 获取 sort
	 * 
	 * @return: Integer 排序
	 */
	@Column(name = "sort", nullable = true, length = 10, scale = 0)
	public Integer getSort() {
		return this.sort;
	}

	/**
	 * 设置 sort
	 * 
	 * @param: sort
	 *             排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@ManyToOne
	@JoinColumn(name = "GID", nullable = false, updatable = false, insertable = false)
	public DictGroup getDictGroup() {
		return dictGroup;
	}

	public void setDictGroup(DictGroup dictGroup) {
		this.dictGroup = dictGroup;
	}
	
    @Transient
	public String getCode() {
		if (dictGroup != null) {
			return dictGroup.getCode();
		}
		return "";
	}

}
