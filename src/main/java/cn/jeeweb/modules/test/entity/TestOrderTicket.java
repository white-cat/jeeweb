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
import cn.jeeweb.core.common.entity.AbstractEntity;
import java.util.Date;
import cn.jeeweb.modules.sys.entity.User;

/**   
 * @Title: 机票信息
 * @Description: 机票信息
 * @author jeeweb
 * @date 2017-07-24 12:10:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "test_order_ticket")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class TestOrderTicket extends AbstractEntity<String> {

    /**id*/
	private String id;
    /**航班号*/
	private String fltno;
    /**航班时间*/
	private Date flytime;
    /**备注信息*/
	private String remarks;
    /**order_id*/
	private TestOrderMain order;
    /**创建者*/
	private User createBy;
    /**创建时间*/
	private Date createDate;
    /**更新者*/
	private User updateBy;
    /**更新时间*/
	private Date updateDate;
    /**删除标记（0：正常；1：删除）*/
	private String delFlag;
	/**
	 * 获取  id
	 *@return: String  id
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=32,scale=0)
	public String getId(){
		return this.id;
	}

	/**
	 * 设置  id
	 *@param: id  id
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 获取  fltno
	 *@return: String  航班号
	 */
	@Column(name ="fltno",nullable=false,length=50,scale=0)
	public String getFltno(){
		return this.fltno;
	}

	/**
	 * 设置  fltno
	 *@param: fltno  航班号
	 */
	public void setFltno(String fltno){
		this.fltno = fltno;
	}
	/**
	 * 获取  flytime
	 *@return: Date  航班时间
	 */
	@Column(name ="flytime",nullable=false,length=19,scale=0)
	public Date getFlytime(){
		return this.flytime;
	}

	/**
	 * 设置  flytime
	 *@param: flytime  航班时间
	 */
	public void setFlytime(Date flytime){
		this.flytime = flytime;
	}
	/**
	 * 获取  remarks
	 *@return: String  备注信息
	 */
	@Column(name ="remarks",nullable=true,length=255,scale=0)
	public String getRemarks(){
		return this.remarks;
	}

	/**
	 * 设置  remarks
	 *@param: remarks  备注信息
	 */
	public void setRemarks(String remarks){
		this.remarks = remarks;
	}
	/**
	 * 获取  order
	 *@return: TestOrderMain  order_id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public TestOrderMain getOrder(){
		return this.order;
	}

	/**
	 * 设置  order
	 *@param: order  order_id
	 */
	public void setOrder(TestOrderMain order){
		this.order = order;
	}
	/**
	 * 获取  createBy
	 *@return: User  创建者
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_by")
	public User getCreateBy(){
		return this.createBy;
	}

	/**
	 * 设置  createBy
	 *@param: createBy  创建者
	 */
	public void setCreateBy(User createBy){
		this.createBy = createBy;
	}
	/**
	 * 获取  createDate
	 *@return: Date  创建时间
	 */
	@Column(name ="create_date",nullable=true,length=19,scale=0)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 * 设置  createDate
	 *@param: createDate  创建时间
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 * 获取  updateBy
	 *@return: User  更新者
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "update_by")
	public User getUpdateBy(){
		return this.updateBy;
	}

	/**
	 * 设置  updateBy
	 *@param: updateBy  更新者
	 */
	public void setUpdateBy(User updateBy){
		this.updateBy = updateBy;
	}
	/**
	 * 获取  updateDate
	 *@return: Date  更新时间
	 */
	@Column(name ="update_date",nullable=true,length=19,scale=0)
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 * 设置  updateDate
	 *@param: updateDate  更新时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 * 获取  delFlag
	 *@return: String  删除标记（0：正常；1：删除）
	 */
	@Column(name ="del_flag",nullable=false,length=1,scale=0)
	public String getDelFlag(){
		return this.delFlag;
	}

	/**
	 * 设置  delFlag
	 *@param: delFlag  删除标记（0：正常；1：删除）
	 */
	public void setDelFlag(String delFlag){
		this.delFlag = delFlag;
	}
	
}
