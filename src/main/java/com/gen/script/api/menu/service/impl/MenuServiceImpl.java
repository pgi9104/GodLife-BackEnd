package com.gen.script.api.menu.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gen.script.api.menu.dao.MenuDAO;
import com.gen.script.api.menu.domain.MenuVO;
import com.gen.script.api.menu.service.MenuService;
import com.gen.script.utils.tree.builder.TreeBuilder;

import jakarta.annotation.Resource;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	
	@Resource(name = "menuDAO")
	private MenuDAO menuDAO;
	
	@Override
	public MenuVO selectRoot(MenuVO vo) throws Exception {
		List<MenuVO> list = menuDAO.selectMenuList(vo);
		
		MenuVO root = (MenuVO)new TreeBuilder<MenuVO>()
								.setTreeList(list)
								.build();
		return root;
	}
	
	@Override
	public List<MenuVO> selectMenuList(MenuVO vo) throws Exception {
		return menuDAO.selectMenuList(vo);
	}
	
	@Override
	public int saveMenu(MenuVO vo) throws Exception {
		int result = 0;
		List<MenuVO> addList = vo.getAddList();
		List<MenuVO> deleteList = vo.getDeleteList();
		List<MenuVO> updateList = vo.getUpdateList();
		if(addList != null && !addList.isEmpty()) {
			result += menuDAO.insertMenu(addList);
		}
		
		if(deleteList != null && !deleteList.isEmpty()) {
			result += menuDAO.deleteMenu(deleteList);
		}
		
		if(updateList != null && !updateList.isEmpty()) {
			result += menuDAO.updateMenu(updateList);
		}
		
		return result;
	}
}