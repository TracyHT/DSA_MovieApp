/*  GROUP 17
    Ngo Le Thien An - ITITDK21030
    Huynh Thanh Thuy - ITITIU21325
    Cao Hoang Khoi Nguyen - ITITDK21048
    Nguyen Dinh Thang - ITITIU21309
    Purpose: This Java class represents a user database, providing a singleton instance and methods for adding users and retrieving the list of all users. It serves as a simple in-memory database for user-related information.
*/
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
