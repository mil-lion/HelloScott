/*
 * File:    HelloScottApplication.java
 * Project: HelloScott
 * Date:    29 июн. 2020 г. 22:39:26
 * Author:  Igor Morenko
 * 
 * Copyright 2005-2020 LionSoft LLC. All rights reserved.
 */
package ru.lionsoft.hello.spring.ws.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения (для запуска)
 * 
 * @author Igor Morenko
 */
@SpringBootApplication
public class HelloScottApplication {

    /**
     * Главный метод приложения
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(HelloScottApplication.class, args);
    }

}
