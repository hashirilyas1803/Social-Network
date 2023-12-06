package Utilities;

import DataStructures.LinkedList;

public class User {
    private String name;
    private int age;
    private boolean gender;
    private LinkedList<User> friends;

    public User(String name, int age, boolean gender)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.friends = new LinkedList<>();
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getGender() {
        return gender;
    }
}
