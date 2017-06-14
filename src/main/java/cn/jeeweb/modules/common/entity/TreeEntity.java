package cn.jeeweb.modules.common.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import cn.jeeweb.core.entity.AbstractEntity;
import cn.jeeweb.core.entity.tree.TreeNode;
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
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name = "id", nullable = false, length = 32)
	private String id; // 编号
	private String name; // 资源名称

	@Column(name = "parent_id", nullable = true, length = 32)
	private String parentId; // 父编号
	@Column(name = "parent_ids", nullable = true, length = 32)
	private String parentIds; // 父编号列表

	@Transient
	private Boolean expanded = Boolean.FALSE;
	@Transient
	private Boolean loaded = Boolean.TRUE;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	@Override
	public boolean isRoot() {
		if (getParentId() == null || getParentId().equals("0") || getParentId().equals("")) {
			return true;
		}
		return false;
	}

	public abstract boolean isHasChildren();

	public abstract void setHasChildren(boolean hasChildren);

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

	@Override
	public String getSeparator() {
		return "/";
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	@Override
	public Boolean getExpanded() {
		return expanded;
	}

	@Override
	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
	}

	public Boolean getLoaded() {
		return loaded;
	}

}
