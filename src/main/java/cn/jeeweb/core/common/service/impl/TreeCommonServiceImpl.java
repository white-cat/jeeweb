package cn.jeeweb.core.common.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jeeweb.core.common.entity.tree.TreeNode;
import cn.jeeweb.core.common.service.ITreeCommonService;
import cn.jeeweb.core.utils.ObjectUtils;
import cn.jeeweb.core.utils.ReflectionUtils;

@Transactional
//@Service("treeCommonService")
@SuppressWarnings("unused")
public class TreeCommonServiceImpl<T extends Serializable & TreeNode<ID>, ID extends Serializable>
		extends CommonServiceImpl<T> implements ITreeCommonService<T, ID> {
	private final String DELETE_CHILDREN_QL;
	private final String UPDATE_CHILDREN_PARENT_IDS_QL;
	private final String FIND_SELF_AND_NEXT_SIBLINGS_QL;
	private final String FIND_NEXT_WEIGHT_QL;

	protected TreeCommonServiceImpl() {
		Class<T> entityClass = ReflectionUtils.getSuperGenericType(getClass());
		String entityName = entityClass.getName();

		DELETE_CHILDREN_QL = String.format("delete from %s where id=?1 or parentIds like concat(?2, %s)", entityName,
				"'%'");

		UPDATE_CHILDREN_PARENT_IDS_QL = String.format(
				"update %s set parentIds=(?1 || substring(parentIds, length(?2)+1)) where parentIds like concat(?2, %s)",
				entityName, "'%'");

		FIND_SELF_AND_NEXT_SIBLINGS_QL = String
				.format("from %s where parentIds = ?1 and weight>=?2 order by weight asc", entityName);

		FIND_NEXT_WEIGHT_QL = String.format(
				"select case when max(weight) is null then 1 else (max(weight) + 1) end from %s where parentId = ?1",
				entityName);

	}

	@Override
	public void save(T entity) {
		if (!ObjectUtils.isNullOrEmpty(entity.getParentId())) {
			T parent = get(entity.getParentId());
			entity.setParentId(parent.getId());
			entity.setParentIds(parent.makeSelfAsNewParentIds());
		} else {
			entity.setParentId(null);
		}
		super.save(entity);
	}

	@Override
	public void update(T entity) {
		if (!ObjectUtils.isNullOrEmpty(entity.getParentId())) {
			T parent = get(entity.getParentId());
			entity.setParentId(parent.getId());
			entity.setParentIds(parent.makeSelfAsNewParentIds());
			updateSelftAndChild(entity, parent.getId(), parent.makeSelfAsNewParentIds());
		} else {
			entity.setParentId(null);
			updateSelftAndChild(entity, null, null);
		}

	}

	@Override
	public void delete(T entity) {
		updateByHql(DELETE_CHILDREN_QL, entity.getId(), entity.makeSelfAsNewParentIds());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteById(Serializable id) {
		ID treeId = (ID) id;
		T entity = get(treeId);
		delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void batchDeleteById(List<?> ids) {
		for (Object id : ids) {
			ID treeId = (ID) id;
			T entity = get(treeId);
			delete(entity);
		}
	}

	/**
	 * 把源节点全部变更为目标节点
	 *
	 * @param source
	 * @param newParentIds
	 */
	private void updateSelftAndChild(T source, ID newParentId, String newParentIds) {
		String oldSourceChildrenParentIds = source.makeSelfAsNewParentIds();
		source.setParentId(newParentId);
		source.setParentIds(newParentIds);
		super.update(source);
		String newSourceChildrenParentIds = source.makeSelfAsNewParentIds();
		updateByHql(UPDATE_CHILDREN_PARENT_IDS_QL, newSourceChildrenParentIds, oldSourceChildrenParentIds);
	}

}