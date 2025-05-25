// src/main/java/com/example/demo/HelloWorld.java
package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @GetMapping("/")
    public String hw() {
        return "Hello world";
    }
}
