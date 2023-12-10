package Utilities;
import DataStructures.LinkedList;

import java.util.Arrays;

public class User implements Comparable<User> {
    private String name;
    private String username;
    private String password;
    private int age;
    private boolean gender;
    private LinkedList<User> friends;

    public User(String name, String username, String password, int age, boolean gender)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.friends = new LinkedList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void addFreind(User user) {
        this.friends.insert(user);
        user.friends.insert(this);
    }

    public void removeFriend(User user) {
        this.friends.delete(user);
        user.friends.delete(this);
    }

    public String viewFriends() {
        User[] list = new User[friends.size];
        friends.toArray(list);
        String[] names = new String[list.length];
        for (int i = 0; i < list.length; i++) {
            names[i] = list[i].name;
        }
        return Arrays.toString(names);
    }

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.getUsername());
    }

    @Override
    public String toString() {
        User[] list = new User[friends.size];
        friends.toArray(list);
        return "User{" +
                "Name: " + name + '\n' +
                "Username: " + username + '\n' +
                '}';
    }
}
