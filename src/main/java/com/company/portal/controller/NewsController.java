package com.company.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NewsController {

    @GetMapping("/news/press")
    public String press(Model model) {
        return "news-press";
    }

    @GetMapping("/news/notices")
    public String notices(Model model) {
        return "news-notices";
    }

}


