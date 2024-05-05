package com.gen.script.api.menu.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gen.script.api.menu.domain.MenuVO;
import com.gen.script.common.dao.CommonDAO;

@Repository("menuDAO")
public class MenuDAO extends CommonDAO{
	public List<MenuVO> selectMenuList(MenuVO vo) throws Exception {
		List<MenuVO> resultList = selectList("menu.selectMenuList", vo);
		return resultList;
	}

	public int insertMenu(List<MenuVO> addList) throws Exception{
		int result = 0;
		
		for(MenuVO vo : addList) {
			result += insert("menu.insertMenu", vo);
		}
		
		return result;
	}

	public int deleteMenu(List<MenuVO> deleteList) {
		int result = 0;
		
		for(MenuVO vo : deleteList) {
			result += delete("menu.deleteMenu", vo);
		}
		
		return result;
	}

	public int updateMenu(List<MenuVO> updateList) {
		int result = 0;
		for(MenuVO vo : updateList) {
			result += update("menu.updateMenu", vo);
		}
		
		return result;
	}
}