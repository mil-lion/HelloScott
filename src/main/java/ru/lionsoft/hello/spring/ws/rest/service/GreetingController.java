/*
 * File:    GreetingController.java
 * Project: HelloScott
 * Date:    29 июн. 2020 г. 22:39:26
 * Author:  Igor Morenko
 * 
 * Copyright 2005-2020 LionSoft LLC. All rights reserved.
 */
package ru.lionsoft.hello.spring.ws.rest.service;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lionsoft.hello.spring.ws.rest.model.Greeting;

/**
 * Контроллер приветственного сервиса {@code Greeting}
 * 
 * @author Igor Morenko
 */
@RestController
public class GreetingController {
    
    /**
     * Шаблон приветственного сообщения
     */
    private static final String TEMPLATE = "Hello, %s!";
    /**
     * Атомарный счетчик ID
     */
    private final AtomicLong counter = new AtomicLong();
    
    /**
     * Сервис приветственного сообщения для HTTP запроса {@code GET}
     * @param name имя приветсвуемого
     * @return объект с привественным сообщением
     */
    @GetMapping("/api/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
