package com.gen.script.utils.tree.builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gen.script.utils.tree.data.TreeVO;

public class TreeBuilder<T extends TreeVO> {
	private T root;
	private Map<String, List<T>> childMap;
	
	public TreeBuilder() {
		init();
	}

	private void init() {
		this.childMap = new HashMap<String, List<T>>();
	}
	
	public void setInitTree(T tree) {
		setInitRoot(tree);
		setChildMap(tree);
	}
	
	public TreeBuilder<T> setTreeList(T[] treeList) {
		for(T tree : treeList) {
			setInitTree(tree);
		}
		return this;
	}

	public TreeBuilder<T> setTreeList(List<T> treeList) {
		for(T tree : treeList) {
			setInitTree(tree);
		}
		return this;
	}
	
	private T getRoot() {
		return root;
	}
	
	public TreeBuilder<T> setRoot(T root) {
		this.root = root;
		return this;
	}
	
	private void setChildMap(T tree){
		if(!childMap.containsKey(tree.getParent())) {
			childMap.put(tree.getParent(), new ArrayList<T>());
		}
		
		childMap.get(tree.getParent()).add(tree);
	}
	
	private void setInitRoot(T tree) {
		if("".equals(tree.getTreeCd()) || null == tree.getTreeCd() || "ROOT".equalsIgnoreCase(tree.getTreeCd())) {
			if("".equals(tree.getParent()) || null == tree.getParent()) {
				setRoot(tree);
			}
		}
	}
	@SuppressWarnings("unchecked")
	private void setChildren(T tree, int depth) {
		T base = tree;
		int level = depth;
		if(base == null) {
			base = this.root;
			level = 0;
		}
		
		base.setDepth(level);
		
		if(this.childMap.containsKey(base.getTreeCd())) {
			base.setChildren((List<TreeVO>)this.childMap.get(base.getTreeCd()));
			for(T child : (List<T>)base.getChildren()) {
				setChildren(child, level+1);
			}
		}else {
			base.setChildren(new ArrayList<TreeVO>());
		}
	}
	
	public T build() {
		setChildren(getRoot(),0);
		return root;
	}
}
