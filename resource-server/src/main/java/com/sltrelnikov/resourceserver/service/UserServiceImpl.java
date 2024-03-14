package com.sltrelnikov.resourceserver.service;

import com.sltrelnikov.resourceserver.entity.Authority;
import com.sltrelnikov.resourceserver.entity.User;
import com.sltrelnikov.resourceserver.repository.AuthorityRepository;
import com.sltrelnikov.resourceserver.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword("{bcrypt}" + encoder.encode(user.getPassword()));
        user.setEnabled(true);
        User saved = userRepository.save(user);
        authorityRepository.save(new Authority(user.getUsername(), user, "ROLE_USER"));
        return user;
    }

    @Override
    public boolean usernameExists(User user) {
        return userRepository.existsByUsername(user.getUsername());
    }
}
