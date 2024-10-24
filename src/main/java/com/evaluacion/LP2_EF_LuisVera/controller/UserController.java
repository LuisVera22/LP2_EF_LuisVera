package com.evaluacion.LP2_EF_LuisVera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.evaluacion.LP2_EF_LuisVera.model.entity.UserEntity;
import com.evaluacion.LP2_EF_LuisVera.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
	public String getLogin(Model model) {
		model.addAttribute("user", new UserEntity());
		return "login";
	}
	
	@PostMapping("/login")
	public String viewlogin(@ModelAttribute("user") UserEntity userEntity,
			Model model, HttpSession session) {
		
		boolean usuarioValidado = userService.validateUser(userEntity);
		if(usuarioValidado) {
			session.setAttribute("user", userEntity.getEmail());
			return "redirect:/menu";
		}
		
		model.addAttribute("invalidLogin","No existe el usuario");
		model.addAttribute("usuario", new UserEntity());
		return "login";
	}
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/";
	}

    @GetMapping("/user_register")
    public String getUserRegister(Model model) {
        model.addAttribute("user", new UserEntity());
        return "user_register";
    }
    
    @PostMapping("/user_register")
    public String viewUserRegister(@ModelAttribute("user") UserEntity newUser, Model model,
            @RequestParam("photo") MultipartFile photo) {
        userService.userRegister(newUser, photo);
        model.addAttribute("successfullyRegistered","Usuario registrado exitosamente");
        return "/user_register";
    }
    
}
