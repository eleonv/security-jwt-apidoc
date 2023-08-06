package com.example.security04.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public ResponseEntity<?> home(HttpServletRequest request) {


        return new ResponseEntity<String>("Todos pueden ver", HttpStatus.OK);
    }
}