package com.springapp.controller;

import com.springapp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {
	@Autowired
	UserService  userService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(Model model) {
		return "index";
	}

	@RequestMapping(value = "/user")
	public String userApp(Model model) {
		return "app";
	}

	@RequestMapping(value = "/angular")
	public String angular(Model model) {
		model.addAttribute("users", userService.list());
		return "angular";
	}
	@RequestMapping(value = "/page1")
	public String stringpage1(Model model) {
		model.addAttribute("users", userService.list());
		return "page1";
	}
}