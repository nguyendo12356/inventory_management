package com.java.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.axis.encoding.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.entity.UserRole;
import com.java.model.MenuModel;
import com.java.model.User;
import com.java.service.RoleService;
import com.java.service.UserService;
import com.java.util.ConvertObject;
import com.java.util.Util;

@Controller
@PropertySource("classpath:error.properties")
@Transactional
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private Environment env;

	@Autowired
	private RoleService roleService;
	
	
	@GetMapping(value = "/signupForm")
	private String loadSignupForm(ModelMap model) {
		model.addAttribute("user", new User());
		model.addAttribute("roles",roleService.findAll());
		return "signup";
	}

	@PostMapping(value = "/addUser")
	private String addUser(@ModelAttribute("user") User user, ModelMap model, HttpServletRequest request)
			throws IOException {
		if (!Util.validateEmail(user.getEmail())) {
			model.addAttribute("errorEmail", env.getProperty("error.email"));
		} else if (!Util.validatePassword(user.getPassword()) &&  !(user.getId() > 0)) {
			model.addAttribute("errorPassword", env.getProperty("error.password"));
		} else if (!Util.saveImage(new File(request.getServletContext().getRealPath("static\\images")),
				user.getImage())) {
			model.addAttribute("errorImage", env.getProperty("error.image.extension"));
		} else if (user.getImage().getSize() > 600 * 1024) {
			model.addAttribute("errorImage", env.getProperty("error.image.size"));
		}else if (user.getId() != 0){
			userService.updateUser(user);
			model.addAttribute("success", env.getProperty("update.success"));
			request.getSession().invalidate();
		} else if (userService.getUserByUsername(user.getUsername()) == null) {
			model.addAttribute("success", env.getProperty("signup.success"));
			User userLogin = new User();
			userLogin.setUsername(user.getUsername());
			model.addAttribute("user", userLogin);
			userService.addUser(user, user.getRoleId());
			return "login";
		} else {
			model.addAttribute("error", env.getProperty("error.username"));
		}
		return loadSignupForm(model);
	}

	@GetMapping(value = { "/loginForm" })
	private String loadLoginForm(ModelMap model, HttpServletRequest request) {
		if (request.getSession().getAttribute("session") != null) {
			return "redirect:/home";
		}
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping(value = "/loginUser")
	private String login(@ModelAttribute("user") User user, ModelMap model, HttpServletRequest request) {
		User user1 = userService.checkLogin(user.getUsername(), user.getPassword());
		if (user1 == null) {
			model.addAttribute("error",env.getProperty("error.login"));
			return "login";
		}else if (!user1.isActive()) {
			model.addAttribute("error",env.getProperty("error.active"));
			return "login";
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("session", user1);
			session.setAttribute("numMessage", userService.countNotification());
			session.setMaxInactiveInterval(60*60*24);
			UserRole userRole = user1.getRole().iterator().next();
			List<MenuModel> menus = new ArrayList<MenuModel>();
			userRole.getRole().getAuth().forEach(auth ->{
				MenuModel item = ConvertObject.convertMenu(auth.getMenu());
				if (item.getParent_id() == 0 && auth.isPermission() && auth.isActive() && item.isActive()) {
					item.setIdMenu(item.getUrl().replace("/", "")+"Id");
					menus.add(item);
				}else if (item.getParent_id() != 0 && auth.isPermission() && auth.isActive() && item.isActive()) {
					item.setIdMenu(item.getUrl().replace("/", "")+"Id");
					menus.forEach(i -> {
						if (i.getId() == item.getParent_id()) {
							i.getChildMenu().add(item);
						}
					});
				}
			});
			session.setAttribute("menus", menus);
			
			return "redirect:/home";
			//return "/home";
		}
	}

	@GetMapping(value = "/logout")
	private String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		request.setAttribute("user", new User());
		return "redirect:/loginForm";
	}
	
	@GetMapping(value = "/user/list")
	private String getUserList(ModelMap model) {
		model.addAttribute("userList", userService.getUsers());
		return "userlist";
	}
	
	@GetMapping(value = "/user/delete/{id}")
	private String deleteUser(@PathVariable("id") int id,ModelMap model) {
		userService.deleteUser(id);
		return "redirect:/user/list";
	}
	
	@GetMapping(value = "/user/update/{id}")
	private String updateUser(@PathVariable("id") int id,ModelMap model) {
		model.addAttribute("user", userService.getUserById(id));
		model.addAttribute("roles",roleService.findAll());
		return "signup";
	}
	
	@GetMapping(value = "/changePassword/{usernameEncode}")
	private String changePassword(@PathVariable("usernameEncode") String usernameEncode, ModelMap model) {
		model.addAttribute("username", new String(Base64.decode(usernameEncode)));
		return "changePassword";
	}
	
	@PostMapping(value = "/updatePassword")
	private String updatePassword(@RequestParam("username") String username,@RequestParam("password") String password) {
		userService.updatePassword(username, password);
		return "redirect:/loginForm";
	}
	
}
