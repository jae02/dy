package com.company.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    @GetMapping("/public/services")
    public String services(Model model) {
        return "public-services";
    }

    @GetMapping("/public/support")
    public String support(Model model) {
        return "public-support";
    }

    @GetMapping("/public/news")
    public String news(Model model) {
        return "public-news";
    }
}


