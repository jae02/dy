package com.company.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServicesController {

    @GetMapping("/services/equipment")
    public String equipment(Model model) {
        return "services-equipment";
    }

    @GetMapping("/services/product")
    public String product(Model model) {
        return "services-product";
    }

}


