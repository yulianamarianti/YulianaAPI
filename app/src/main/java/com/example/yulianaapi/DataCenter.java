package com.example.yulianaapi;

import java.io.Serializable;

public class DataCenter implements Serializable {
    String name, official_web,  description, image, hello_world;

    public DataCenter(String name, String official_web, String description, String image, String hello_world) {
        this.name = name;
        this.official_web = official_web;
        this.description = description;
        this.image = image;
        this.hello_world = hello_world;
    }

    public String getName() {
        return name;
    }

    public String getOfficial_web() {
        return official_web;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getHello_world() {
        return hello_world;
    }
}
