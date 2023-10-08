package org.gfg.securitydemo.demo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
public class TestController {

    @GetMapping("demo")
    public String demo(){
        return "demo...";
    }
    @GetMapping("/developCode")
    public String developCode(){
        User myUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  myUser.getUsername() +" is developing the code...";
    }
    @GetMapping("/testCode")
    public String testCode(){
        return "testing the code...";
    }
    @GetMapping("/home")
    public String home(){
        return "home for everyone...";
    }
    @PostMapping("/deployCode")
    public String deployCode(){
        return "deploying the code...";
    }

//    @PostMapping("/signup")
//    public String signUp(@RequestBody createUser request){
//
//    }
}
