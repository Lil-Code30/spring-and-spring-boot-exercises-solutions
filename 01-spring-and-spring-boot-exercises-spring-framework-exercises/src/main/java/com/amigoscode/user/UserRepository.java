package com.amigoscode.user;

import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: 05 - Add @Repository annotation to this class

@Repository
public class UserRepository {

    public List<String> findAllUsers() {
        return List.of("Alice", "Bob", "Charlie");
    }

}
