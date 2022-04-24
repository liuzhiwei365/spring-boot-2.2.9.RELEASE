package com.lzw.base.api;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Trol {

    @RequestMapping("/test")
    public String test(){

        return "hello";
    }
}
