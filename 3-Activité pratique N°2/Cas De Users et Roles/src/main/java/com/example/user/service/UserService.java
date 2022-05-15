package com.example.user.service;


import com.example.user.entities.Role;
import com.example.user.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String username);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username,String roleName);
    void authentication(String username,String password);
}
