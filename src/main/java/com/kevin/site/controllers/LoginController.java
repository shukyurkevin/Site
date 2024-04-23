package com.kevin.site.controllers;

import com.kevin.site.models.UserModel;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
  @GetMapping("/")
  public String displayLoginForm(Model model){
    model.addAttribute("loginModel", new UserModel());
    return "loginForm";
  }
  @PostMapping("/processLogin")
  public String processLogin(@Valid UserModel userModel, BindingResult bindingResult, Model model){
    if (bindingResult.hasErrors()){
      model.addAttribute("loginModel", userModel);
      return "loginForm";
    }
    model.addAttribute("loginModel", userModel);
    return "loginResults";
  }
}
