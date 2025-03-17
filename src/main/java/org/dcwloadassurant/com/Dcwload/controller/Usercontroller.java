package org.dcwloadassurant.com.Dcwload.controller;

//import com.secuirty.service.Data.Cisdata;
//import com.secuirty.service.Interface.Iuserservice;
//import com.secuirty.service.entity.User;
//import com.secuirty.service.exception.Cisprojectnotexcepection;
//import com.secuirty.service.services.Implservices;
import org.dcwloadassurant.com.Dcwload.Entity.User;
import org.dcwloadassurant.com.Dcwload.Interface.Iuserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;


@Controller
//@RequestMapping("/user")
public class Usercontroller {

	@Autowired
	private Iuserservice services;

	@GetMapping("/pregister")
	public String showRegister() {
		
		return "insert";
	}

	@GetMapping("/hello")
	public String showHello(Principal p) {
		
		System.out.println(p.getClass().getName());
		System.out.println(p.getName());
		return "hello";
	}

	@GetMapping("/check-session")
	public String checkSession(HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession(false) == null || request.getSession(false).getAttribute("user") == null) {
			// Session is invalid; redirect to login page
			return "redirect:/login";
		}
		// Session is valid; continue processing
		return "sessionValid";
	}
	
	@GetMapping("/admin")
	public String showAdmin(HttpSession seesion) {
		System.out.println("am admin");
		seesion.setAttribute("admin", true);
		seesion.setMaxInactiveInterval(30);
		
		return "admin";
	}

	@GetMapping("/login")
	public String showLogin() {
		return "login";
	}


	
	@GetMapping("/register")
	public String showReg() {
		return "Register";
	}
	
	@PostMapping("/save")
	public String saveUser(
			@ModelAttribute User user,
			Model model) 
	{
		String id = services.saveuser(user);
		String message = "User '"+id+"' created Successfully " ;
		model.addAttribute("message", message);
		return "Register";
	}
	
}
