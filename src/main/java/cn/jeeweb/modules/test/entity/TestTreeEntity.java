package cn.jeeweb.modules.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import cn.jeeweb.modules.common.entity.TreeEntity;
import cn.jeeweb.modules.sys.entity.User;
import java.util.Date;

/**
 * @Title: 测试数
 * @Description: 测试数
 * @author jeeweb
 * @date 2017-06-28 23:30:57
 * @version V1.0
 *
 */
@Entity
@Table(name = "test_tree")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class TestTreeEntity extends TreeEntity {

	private TestTreeEntity parent;
	/**
	 * 是否有叶子节点
	 */
	private boolean hasChildren;

	/**
	 * 创建者
	 */
	private User createBy;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 更新者
	 */
	private User updateBy;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 删除标记（0：正常；1：删除）
	 */
	private String delFlag;
	/**
	 * 备注信息
	 */
	private String remarks;

	@Formula(value = "(select count(*) from test_tree f_t where f_t.parent_id = id)")
	@Override
	public boolean isHasChildren() {
		return hasChildren;
	}

	@Override
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "parent_id", nullable = true, updatable = false, insertable = false)
	public TestTreeEntity getParent() {
		return parent;
	}

	public void setParent(TestTreeEntity parent) {
		this.parent = parent;
	}

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

}
