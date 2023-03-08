package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HelloController {

    @RequestMapping("/")
    public String showPage(Model model){
        model.addAttribute("message", "Oh yeah!");
        return "hello_world";
    }

}
