package com.gen.script.api.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.gen.script.api.menu.domain.MenuVO;
import com.gen.script.api.menu.service.MenuService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MenuController {
	
	@Resource(name="menuService")
	private MenuService menuService;
	
	@GetMapping("/program/programM.do")
	public String progM(MenuVO vo, HttpServletRequest request) throws Exception{
		return "/program/programM";
	}
	
	@GetMapping("/menu/menuRoot.ajax")
	public ModelAndView menuRoot(MenuVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("menuRoot", menuService.selectRoot(vo));
		return mav;
	}
	
	@GetMapping("/menu/menuList.ajax")
	public ModelAndView menuList(MenuVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("menuList", menuService.selectMenuList(vo));
		return mav;
	}
	
	@PostMapping(value="/menu/saveMenu.ajax")
	public ModelAndView save(@RequestBody MenuVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		mav.addObject("result",menuService.saveMenu(vo));
		return mav;
	}
}
