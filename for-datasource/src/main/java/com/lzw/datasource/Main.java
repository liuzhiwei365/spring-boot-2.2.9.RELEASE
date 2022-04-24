package com.lzw.datasource;


import com.lzw.datasource.dto.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.mysql.cj.jdbc.Driver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class Main {
    @Autowired
    private Hello hello;

    @RequestMapping("/")
    public String index() {
        return hello.sayHello();
    }

    public static void main(String[] args) {


        System.setProperty("druid.url",
                "jdbc:mysql://127.0.0.1:3306/hive");

        System.setProperty("druid.username","hive");
        System.setProperty("druid.password","hive");
        System.setProperty("druid.driverClassName","com.mysql.cj.jdbc.Driver");


        SpringApplication springApplication = new SpringApplication(Main.class);

        springApplication.run(args);


    }
}
