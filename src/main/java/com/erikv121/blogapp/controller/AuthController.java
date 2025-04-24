package com.erikv121.blogapp.controller;

import com.erikv121.blogapp.dto.request.UserRequestDto;
import com.erikv121.blogapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String logout, Model model) {
        if (logout != null) {
            model.addAttribute("logoutMessage", "You have been successfully logged out");
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userRequest", new UserRequestDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userRequest") UserRequestDto userRequestDto,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        // If there are validation errors, return to the registration form
        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.registerUser(userRequestDto);
            redirectAttributes.addFlashAttribute("success", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            result.rejectValue("username", "error.userRequest", e.getMessage());
            return "register";
        }
    }
}