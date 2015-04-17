package org.sample.hello;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorld {

    @RequestMapping("/hello")
    public String hello() {
        return "{\"greeting\": \"Hello World\"}";
    }
}
