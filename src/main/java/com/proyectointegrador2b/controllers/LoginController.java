package com.proyectointegrador2b.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public ModelAndView login(@RequestParam(value="error", required = false) String error,
			@RequestParam(value="logout", required = false) String logout,
			ModelAndView mv,Principal principal, RedirectAttributes flash) {
		if(principal!= null) {
			flash.addFlashAttribute("info", "Ya ha iniciado sesion anteriormente");
			mv.setViewName("redirect:/");
			return mv;
		}
		if(error!= null) {
			mv.addObject("error", "Error en el login: El usuario o contraseña no son correctos. Favor de Volver a Intentar");
		}
		if(logout !=null) {
			mv.addObject("success", "ha cerrado  sesion Correctamente!");
		}
		mv.setViewName("vistas/login");
		
		return mv;
	}
	@GetMapping(value= {"/index","/"})
	public ModelAndView index(ModelAndView mv) {
		mv.setViewName("vistas/index");
		return mv;
	}
}
