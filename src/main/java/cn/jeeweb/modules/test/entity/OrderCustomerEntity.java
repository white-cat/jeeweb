package cn.jeeweb.modules.test.entity;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.jeeweb.core.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import java.util.Date;

/**
 * @Title: 订单客户信息
 * @Description: 订单客户信息
 * @author jeeweb
 * @date 2017-06-03 12:03:29
 * @version V1.0
 *
 */
@Entity
@Table(name = "test_order_customer")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class OrderCustomerEntity extends AbstractEntity<String> {

	/** 字段主键 */
	private String id;
	/** 客户姓名 */
	private String name;
	/** 性别 */
	private String sex;
	/** 电话 */
	private String phone;
	/** 创建者 */
	private User createBy;
	/** 创建时间 */
	private Date createDate;
	/** 更新者 */
	private User updateBy;
	/** 更新时间 */
	private Date updateDate;
	/** 删除标记（0：正常；1：删除） */
	private String delFlag;
	/** 备注信息 */
	private String remarks;

	private OrderMainEntity orderMain;

	/**
	 * 获取 id
	 * 
	 * @return: String 字段主键
	 */

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32, scale = 0)
	public String getId() {
		return this.id;
	}

	/**
	 * 设置 id
	 * 
	 * @param: id
	 *             字段主键
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 name
	 * 
	 * @return: String 客户姓名
	 */
	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	/**
	 * 设置 name
	 * 
	 * @param: name
	 *             客户姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 sex
	 * 
	 * @return: String 性别
	 */
	@Column(name = "sex", nullable = false, length = 4)
	public String getSex() {
		return this.sex;
	}

	/**
	 * 设置 sex
	 * 
	 * @param: sex
	 *             性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取 phone
	 * 
	 * @return: String 电话
	 */
	@Column(name = "phone", nullable = false, length = 11)
	public String getPhone() {
		return this.phone;
	}

	/**
	 * 设置 phone
	 * 
	 * @param: phone
	 *             电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取 createBy
	 * 
	 * @return: User 创建者
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_by")
	public User getCreateBy() {
		return this.createBy;
	}

	/**
	 * 设置 createBy
	 * 
	 * @param: createBy
	 *             创建者
	 */
	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	/**
	 * 获取 createDate
	 * 
	 * @return: Date 创建时间
	 */
	@Column(name = "create_date", nullable = true, length = 19, scale = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * 设置 createDate
	 * 
	 * @param: createDate
	 *             创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * 获取 updateBy
	 * 
	 * @return: User 更新者
	 */
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_by")
	public User getUpdateBy() {
		return this.updateBy;
	}

	/**
	 * 设置 updateBy
	 * 
	 * @param: updateBy
	 *             更新者
	 */
	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 获取 updateDate
	 * 
	 * @return: Date 更新时间
	 */
	@Column(name = "update_date", nullable = true, length = 19, scale = 0)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 设置 updateDate
	 * 
	 * @param: updateDate
	 *             更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 获取 delFlag
	 * 
	 * @return: String 删除标记（0：正常；1：删除）
	 */
	@Column(name = "del_flag", nullable = false, length = 1, scale = 0)
	public String getDelFlag() {
		return this.delFlag;
	}

	/**
	 * 设置 delFlag
	 * 
	 * @param: delFlag
	 *             删除标记（0：正常；1：删除）
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	/**
	 * 获取 remarks
	 * 
	 * @return: String 备注信息
	 */
	@Column(name = "remarks", nullable = true, length = 255, scale = 0)
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 * 设置 remarks
	 * 
	 * @param: remarks
	 *             备注信息
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", updatable = false, insertable = true)
	@JsonIgnore
	public OrderMainEntity getOrderMain() {
		return orderMain;
	}

	public void setOrderMain(OrderMainEntity orderMain) {
		this.orderMain = orderMain;
	}

}
