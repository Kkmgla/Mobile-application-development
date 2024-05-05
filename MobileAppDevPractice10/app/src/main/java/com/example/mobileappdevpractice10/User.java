package com.example.mobileappdevpractice10;

public class User {
    private String name;
    private String id;
    private String number;
    private String height;
    private String weight;

    public User(String name, String id, String number, String height, String weight) {
        this.name = name;
        this.id = id;
        this.number = number;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}