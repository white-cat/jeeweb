package cn.jeeweb.core.common.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;

import cn.jeeweb.core.common.entity.tree.TreeNode;
import cn.jeeweb.core.utils.StringUtils;

/**
 * 树抽象实体基类
 * 
 * @author 王存见
 *
 * 
 */
@MappedSuperclass
public abstract class TreeEntity extends AbstractEntity<String> implements TreeNode<String> {

	private static final long serialVersionUID = 1L;

	private String id; // 编号
	private String name; // 资源名称

	private String parentId; // 父编号

	private String parentIds; // 父编号列表

	private Boolean expanded = Boolean.FALSE;

	private Boolean loaded = Boolean.TRUE;

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

	@Column(name = "name", nullable = true, length = 250)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "parent_id", nullable = true, length = 32)
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Column(name = "parent_ids", nullable = true, length = 32)
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Transient
	@Override
	public boolean isRoot() {
		if (getParentId() == null || getParentId().equals("0") || getParentId().equals("")) {
			return true;
		}
		return false;
	}

	@Transient
	public abstract boolean isHasChildren();

	public abstract void setHasChildren(boolean hasChildren);

	@Transient
	@Override
	public Long getLevel() {
		if (parentIds == null) {
			return (long) 0;
		}
		String[] parentIdArr = parentIds.split("/");
		List<String> idsList = new ArrayList<String>();
		for (String id : parentIdArr) {
			if (!StringUtils.isEmpty(id)) {
				idsList.add(id);
			}
		}
		return (long) (idsList.size());
	}

	@Transient
	@Override
	public Boolean isLeaf() {
		if (isHasChildren()) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	@Override
	public String makeSelfAsNewParentIds() {
		if (StringUtils.isEmpty(getParentIds())) {
			return getId() + getSeparator();
		}
		return getParentIds() + getId() + getSeparator();
	}

	@Transient
	@Override
	public String getSeparator() {
		return "/";
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	@Transient
	@Override
	public Boolean getExpanded() {
		return expanded;
	}

	@Override
	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}

	@Transient
	public Boolean getLoaded() {
		return loaded;
	}

}
