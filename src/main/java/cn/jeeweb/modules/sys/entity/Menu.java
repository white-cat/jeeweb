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

@Entity
@Table(name = "sys_menu", schema = "")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class Menu extends TreeEntity {

	private Menu parent;
	/**
	 * 是否有叶子节点
	 */
	private boolean hasChildren;

	/** 图标 */
	private String menuIcon;
	/** 资源类型 */
	private String type;
	/** 点击后前往的地址 */
	private String url;

	/** 权限字符串 */
	private String permission;
	/** 是否显示 */
	private Short isshow;

	/** 排序 */
	private Integer sort;
	/** 备注 */
	private String remarks;

	@Formula(value = "(select count(*) from sys_menu f_t where f_t.parent_id = id)")
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
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	@Column(name = "menu_icon", nullable = true, length = 255)
	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Column(name = "type", nullable = true, length = 50)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "url", nullable = true, length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "permission", nullable = true, length = 100)
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	@Column(name = "isshow", nullable = true)
	public Short getIsshow() {
		return isshow;
	}

	public void setIsshow(Short isshow) {
		this.isshow = isshow;
	}

	@Column(name = "sort", nullable = true, length = 10, scale = 0)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "remarks", nullable = true, length = 100)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
