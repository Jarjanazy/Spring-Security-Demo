package com.example.demo.user;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @Log4j2
public class UserController {
    @GetMapping("user")
    public void userEndPoint(){
        log.info("Hey user !!");
    }

    @GetMapping("admin")
    public void adminEndPoint(){
        log.info("Hey admin !!");
    }


}
