package com.gen.script.api.menu.domain;

import java.util.List;

import com.gen.script.utils.tree.data.TreeVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuVO extends TreeVO{
	private String menuCd;
	private String upMenuCd;
	private String menuName;
	private String url;
	private String useYn;
	
	private List<MenuVO> addList;
	private List<MenuVO> deleteList;
	private List<MenuVO> updateList;
	
	private String authCd;
	private String createUseCd;
	private String readUseCd;
	private String updateUseCd;
	private String deleteUseCd;
	
	private int sortSeq;
}
