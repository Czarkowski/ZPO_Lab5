package com.example.zpo_lab5;

public class Person {
    public Person(){};

    @MyPattern(regex="[A-Z].*", message = "Mała litera na początku!")
    @MyPattern(regex="[A-Za-z]{2,}", message = "Za mało znaków!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
