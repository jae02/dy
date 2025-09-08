package com.company.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about/overview")
    public String overview(Model model) {
        return "about-overview";
    }

    @GetMapping("/about/history")
    public String history(Model model) {
        return "about-history";
    }

    @GetMapping("/about/org")
    public String org(Model model) {
        return "about-org";
    }
}


