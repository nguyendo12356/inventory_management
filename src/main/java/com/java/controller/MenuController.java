package com.java.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.entity.Menu;
import com.java.model.MenuModel;
import com.java.service.MenuService;

@Controller
public class MenuController {

	@Autowired
	private MenuService menuService;

	@GetMapping("/menu")
	public String getMenu(Model model) {
		// sap xep tiep cho nay
		List<MenuModel> menu = menuService.findAll();
		model.addAttribute("menu", menu);
		return "menu";
	}

	@GetMapping("/showMenu")
	public String showMenu(Model model) {
		model.addAttribute("menu", new Menu());
		return "themmenu";
	}

	@PostMapping("/addMenu")
	public String addMenu(@RequestParam("txtname") String name, @RequestParam("slchucnang") int parent_id,
			@RequestParam("txturl") String url, @RequestParam("txtindex") int index, Model model) {
		menuService.addMenu(new MenuModel(name, parent_id, url, index, true, new Date()));
		return "redirect:menu";
	}
	
	@GetMapping(value = "/deleteMenu/{id}")
	public String deleteMenu(@PathVariable("id") int id, Model model) {
		menuService.deleteMenu(id);
		return "redirect:/menu";
	}

}
