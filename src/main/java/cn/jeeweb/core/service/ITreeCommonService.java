package cn.jeeweb.core.service;

import java.io.Serializable;

import cn.jeeweb.core.entity.tree.TreeNode;

public interface ITreeCommonService<T extends Serializable & TreeNode<ID>, ID extends Serializable>
		extends ICommonService<T> {

}