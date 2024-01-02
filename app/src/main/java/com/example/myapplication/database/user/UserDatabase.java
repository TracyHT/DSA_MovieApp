package com.example.myapplication.database.user;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static UserDatabase instance;
    private List<User> allUser = new ArrayList<>();

    public UserDatabase(List<User> allUser) {
        this.allUser = allUser;
    }

    public UserDatabase(){}

    public static synchronized UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase();
        }
        return instance;
    }

    public void addUser(User user){
        allUser.add(user);
    }
    public List<User> getAllUsers() {
        return allUser;
    }

}
