package com.wlt.wla;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/", produces = "text/html;charset=UTF-8")
public class WlaController {

    @GetMapping("")
    @ResponseBody
    private String helloPage() {
        return "Hello";
    }

    @GetMapping("/home")
    @ResponseBody
    private String homePage() {
        return "Home";
    }
}