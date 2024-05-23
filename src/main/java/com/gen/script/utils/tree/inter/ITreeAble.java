package com.gen.script.utils.tree.inter;

import java.util.List;

import com.gen.script.utils.tree.data.TreeVO;

public interface ITreeAble {
	public String getTreeCd();
	public void setTreeCd(String treeCd);
	public String getParent();
	public void setParent(String parent);
	public List<TreeVO> getChildren();
	public void setChildren(List<TreeVO> children);
	public int getSortSeq();
	public void setSortSeq(int sortSeq);
	public int getLevel();
	public void setLevel(int level);
}
