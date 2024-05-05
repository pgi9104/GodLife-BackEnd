package com.gen.script.api.menu.service;

import java.util.List;

import com.gen.script.api.menu.domain.MenuVO;

public interface MenuService {
	public MenuVO selectRoot(MenuVO vo) throws Exception;
	public List<MenuVO> selectMenuList(MenuVO vo) throws Exception;
	public int saveMenu(MenuVO vo) throws Exception;
}
