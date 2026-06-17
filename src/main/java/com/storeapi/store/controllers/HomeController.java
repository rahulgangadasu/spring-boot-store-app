package com.storeapi.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home")
    public String index(Model model){
        model.addAttribute("name", "Rahul");
        return "home";
    }
}
