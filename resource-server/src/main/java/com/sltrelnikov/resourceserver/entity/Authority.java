package com.sltrelnikov.resourceserver.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
@IdClass(AuthorityId.class)
public class Authority {
    @Id
    private String username;
    @Id
    private String authority;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private User user;

    public Authority() {
    }

    public Authority(String username, User user, String authority) {
        this.username = username;
        this.user = user;
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


