package Utilities;

public class User {
    private String name;
    private int age;
    private boolean gender;

    public User(String name, int age, boolean gender)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
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
