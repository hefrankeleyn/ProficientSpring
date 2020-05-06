package com.hef.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Date 2020/5/4
 * @Author lifei
 */
@RestController
//@EnableAutoConfiguration
public class BbsDaemon {

    @RequestMapping(value = "/")
    public String index(){
        return "欢迎光临";
    }

//    public static void main(String[] args) {
//        SpringApplication.run(BbsDaemon.class, args);
//    }
}
