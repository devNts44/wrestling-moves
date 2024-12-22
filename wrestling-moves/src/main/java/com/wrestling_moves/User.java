package com.wrestling_moves;

public class User {

    private long id;
    private String firstName;
    private String lastName;
    private int age;
    private double weight;
    private double size;

    public User(long id, String firstName, String lastName, int age, double weight, double size) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getSize() {
        return size;
    }
}
