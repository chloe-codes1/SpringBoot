package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController{

    @RequestMapping("/{name}.html")
    public String page(@PathVariable String name, Model model){

        model.addAttribute("pageName", name);

        return "page";
    }

}