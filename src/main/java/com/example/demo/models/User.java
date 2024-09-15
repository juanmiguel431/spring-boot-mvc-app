package com.example.demo.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {

    private int id;
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private final User user;

        private UserBuilder() {
            this.user = new User();
        }

        public UserBuilder username(String username) {
            this.user.setUsername(username);
            return this;
        }

        public UserBuilder password(String password) {
            this.user.setPassword(password);
            return this;
        }

        public UserBuilder roles(String... roles) {
            this.user.setRoles(Arrays.stream(roles).toList());
            return this;
        }

        public User build() {
            return this.user;
        }
    }
}
