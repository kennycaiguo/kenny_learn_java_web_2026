package org.kenny.thymeleaf.entity;

public class User {
    private String username;
    private int age;
    private boolean isAdmin;

    public User(String username, int age, boolean isAdmin) {
        this.username = username;
        this.age = age;
        this.isAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }
    public User() {}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
//    @Override
//    public String toString() {
//        return "User{" + "username=" + username + ", age=" + age + '}';
//    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
