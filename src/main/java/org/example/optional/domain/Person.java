package org.example.optional.domain;

public class Person {
    private Car car;
    private int age;

    public Person(Car car, int age) {
        this.car = car;
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public int getAge() {
        return age;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
