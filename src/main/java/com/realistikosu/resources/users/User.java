package com.realistikosu.resources.users;

/**
 * A minimal representation of a user.
 */
public class User {
    int id;
    String name;
    String password_brcypt;
    String email;


    public User(int id, String name, String password_bcrypt, String email) {
        this.id = id;
        this.name = name;
        this.password_brcypt = password_bcrypt;
        this.email = email;
    }
}
