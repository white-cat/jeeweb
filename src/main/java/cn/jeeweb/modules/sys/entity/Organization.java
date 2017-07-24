package cn.jeeweb.modules.sys.entity;

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

import cn.jeeweb.core.common.entity.TreeEntity;

/**
 * @Title:
 * @Description:
 * @author 王存见
 * @date 2017-02-07 21:06:09
 * @version V1.0
 *
 */
@Entity
@Table(name = "sys_organization", schema = "")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class Organization extends TreeEntity {

	private Organization parent;

	private String remarks;

	private boolean hasChildren;

	/**
	 * 是否有叶子节点
	 */
	@Formula(value = "(select count(*) from sys_organization f_t where f_t.parent_id = id)")
	@Override
	public boolean isHasChildren() {
		return hasChildren;
	}

	@Override
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	// 这里需要设置CascadeType.ALL，否则无法保存
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "parent_id", nullable = true, updatable = false, insertable = false)
	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}

	@Column(name = "remarks", nullable = true, length = 1000)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
