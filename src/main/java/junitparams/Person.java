package junitparams;

/**
 * Created by goonawardanan on 21/03/2016.
 */
public class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    @Override
    public String toString() {
        return "Person of age: " + age;
    }
}