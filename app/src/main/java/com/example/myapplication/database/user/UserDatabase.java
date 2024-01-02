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

    public User findUserByUsername(String username) {
        for (User user : allUser) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User with the specified username not found
    }

}
