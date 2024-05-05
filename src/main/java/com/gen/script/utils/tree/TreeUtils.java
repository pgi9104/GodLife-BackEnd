package com.gen.script.utils.tree;

import java.util.ArrayList;
import java.util.List;

import com.gen.script.utils.tree.data.TreeVO;

public class TreeUtils<T extends TreeVO> {
	
	@SuppressWarnings("unchecked")
	public List<T> toList(T root, List<T> list){
		if(list == null) {
			list = new ArrayList<T>();
		}
		
		if(root != null) {
			list.add(root);
			List<T> children = (List<T>) root.getChildren();
			for(T child : children) {
				list = toList(child, list);
			}
		}
		
		return list;
	}
}
