package com.weat.weat.data.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBootSwaggerController {
    @RequestMapping(value = "/hello")
    public String Hello() {
        return "Hello World";
    }
}
