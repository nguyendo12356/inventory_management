package com.java.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.entity.Menu;
import com.java.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping("/menu")
	public String getMenu(Model model) {
		//sap xep tiep cho nay
		menuService.findAll().stream().sorted( (s1, s2) -> s1.getParent_id() - s2.getParent_id() );
		List<Menu> menu = menuService.findAll();
		model.addAttribute("menu", menu);
		menuService.findAll().stream().filter(n-> n.getParent_id() == 0);
		return "menu";
	}

	@GetMapping("/showMenu")
	public String showMenu(Model model) {
		model.addAttribute("menu" , new Menu());
		return "themmenu";
	}
	
	@PostMapping("/addMenu")
	public String addMenu(@ModelAttribute("menu") Menu menu, Model model) {
		menuService.addMenu(menu);
		return getMenu(model);
	}
	
}