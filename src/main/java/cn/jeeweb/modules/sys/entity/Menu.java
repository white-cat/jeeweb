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
import cn.jeeweb.modules.common.entity.TreeEntity;


@Entity
@Table(name = "sys_menu", schema = "")
@DynamicUpdate(false)
@DynamicInsert(false)
@SuppressWarnings("serial")
public class Menu extends TreeEntity {

	// 这里需要设置CascadeType.ALL，否则无法保存
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "parent_id", nullable = true, updatable = false, insertable = false)
	private Menu parent;
	/**
	 * 是否有叶子节点
	 */
	@Formula(value = "(select count(*) from sys_menu f_t where f_t.parent_id = id)")
	private boolean hasChildren;

	/** 图标 */
	@Column(name = "menu_icon", nullable = true, length = 255)
	private String menuIcon;
	/** 资源类型 */
	@Column(name = "type", nullable = true, length = 50)
	private String type;
	/** 点击后前往的地址 */
	@Column(name = "url", nullable = true, length = 200)
	private String url;

	/** 权限字符串 */
	@Column(name = "permission", nullable = true, length = 100)
	private String permission;
	/** 是否显示 */
	@Column(name = "isshow", nullable = true)
	private Short isshow;

	/** 排序 */
	@Column(name = "sort", nullable = true, length = 10, scale = 0)
	private Integer sort;
	/** 备注 */
	@Column(name = "remarks", nullable = true, length = 100)
	private String remarks;


	@Override
	public boolean isHasChildren() {
		return hasChildren;
	}

	@Override
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Short getIsshow() {
		return isshow;
	}

	public void setIsshow(Short isshow) {
		this.isshow = isshow;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
