package org.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
     public class LoginController {

         @GetMapping("/login")
         public String login() {
             return "forward:/login.html"; // 转发到 static 目录下的 login.html
         }
     }
     