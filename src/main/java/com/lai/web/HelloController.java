package com.lai.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lailai on 2017/9/15.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello.html")
    public  String index(ModelMap map){
        map.addAttribute("host","http://blog.didispace.com");
        return "index";
    }
}
