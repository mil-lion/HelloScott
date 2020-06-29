/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.lionsoft.hello.spring.ws.rest.service;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lionsoft.hello.spring.ws.rest.model.Greeting;

/**
 *
 * @author Igor Morenko
 */
@RestController
public class GreetingController {
    
    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @GetMapping("/api/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
