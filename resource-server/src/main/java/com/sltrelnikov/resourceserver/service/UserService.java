package com.sltrelnikov.resourceserver.service;

import com.sltrelnikov.resourceserver.entity.User;

public interface UserService {

    User save(User user);

    boolean usernameExists(User user);
}
