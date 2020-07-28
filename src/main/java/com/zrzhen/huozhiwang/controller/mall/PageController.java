package com.zrzhen.huozhiwang.controller.mall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "mall/index";
    }
}
