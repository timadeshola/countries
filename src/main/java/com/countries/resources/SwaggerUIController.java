package com.countries.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerUIController {

    @RequestMapping(value = "/swagger-ui")
    public String index() {
        return "redirect:swagger-ui.html";
    }
}
