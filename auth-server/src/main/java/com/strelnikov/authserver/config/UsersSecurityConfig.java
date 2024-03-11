package com.strelnikov.authserver.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.util.Map;

// Configures persistent users
@Configuration
public class UsersSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    ApplicationRunner usersRunner(UserDetailsManager userDetailsManager) {
        return args -> {
            var builder = User.builder().roles("USER");
            var users = Map.of(
                    "user", "{bcrypt}$2a$10$mZA5.KvkhikrYBlrk3z6cOQuJS816rPSqwOepDu/c3JTHmU7UJ4WW",
                    "admin", "{bcrypt}$2a$10$QXBt.Rm00LhIcnJYwzUaBOBfjSQG/.o9g1KE2OIZh7fUIX62Ikstm");
            users.forEach((username, password) -> {
                if (!userDetailsManager.userExists(username)) {
                    var user = builder
                            .username(username)
                            .password(password)
                            .build();
                    userDetailsManager.createUser(user);
                }
            });
        };
    }

}
