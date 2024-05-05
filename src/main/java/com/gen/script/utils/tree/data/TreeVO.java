package com.gen.script.utils.tree.data;

import java.util.List;

import com.gen.script.common.domain.CommonVO;
import com.gen.script.utils.tree.inter.ITreeAble;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public abstract class TreeVO extends CommonVO implements ITreeAble{
	private String treeCd;
	private String parent;
	private List<TreeVO> children;
	private int depth;
}