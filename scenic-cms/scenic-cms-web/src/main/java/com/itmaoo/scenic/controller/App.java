package com.itmaoo.scenic.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.itmaoo.scenic.entity.dto.User;

@Controller
@EnableAutoConfiguration
public class App extends SpringBootServletInitializer {

    @RequestMapping("action/a")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
    String login(@RequestBody User userModel) {
    	if(userModel.getUsername() =="admin"){
			System.out.println("OK");
		}else{
			System.out.println("Failed");
		}
        return "Hello World!";
    }
    @RequestMapping("/e/test")
    @ResponseBody
    String test(@RequestBody String uname) {
    	if(uname =="admin"){
			System.out.println("OK");
		}else{
			System.out.println("Failed");
		}
        return "Hello World!";
    }
    @RequestMapping(value = "/param", method = RequestMethod.GET)  
    public String loginByGet(@RequestParam(value = "name", required = true) String name,  
            @RequestParam(value = "pwd", required = true) String pwd) {  
    	if(name.equals("admin")){
			System.out.println("OK");
		}else{
			System.out.println("Failed");
		}
        return "Hello World!";
    }  

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
