package com.realistikosu.resources.users;

import java.util.Random;

/**
 * A temporary dummy repository.
 */
public class UserRepository {
    Random random;

    public UserRepository() {
        random = new Random();
    }

    public User fromName(String name) {
        return new User(
                random.nextInt(1, 10000),
                name,
                "",
                "fake@email.com"
        );
    }
}
