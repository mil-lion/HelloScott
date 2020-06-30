/*
 * File:    Greeting.java
 * Project: HelloScott
 * Date:    29 июн. 2020 г. 22:39:26
 * Author:  Igor Morenko
 * 
 * Copyright 2005-2020 LionSoft LLC. All rights reserved.
 */
package ru.lionsoft.hello.spring.ws.rest.model;

/**
 * Данные для приветсвенного сервиса
 * 
 * @author Igor Morenko
 */
public class Greeting {
    
    // ******************* Fields ********************
    
    private final long id;
    private final String content;

    // ******************* Constructors ********************

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    // ******************* Getters & Setters ********************

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
 
    // ******************* Cast to String ********************

    @Override
    public String toString() {
        return "Greeting{" 
                + "id=" + id 
                + ", content=" + content 
                + '}';
    }

}
