package com.example.user.service;

import com.example.user.entities.Role;
import com.example.user.entities.User;
import com.example.user.repositories.RoleRepository;
import com.example.user.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private HashPassword hashPassword;

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        String pw=user.getPassword();
        user.setPassword(hashPassword.passwordHasher(pw));
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user=findUserByUserName(username);
        Role role=findRoleByRoleName(roleName);

        if(user.getRoles()!=null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }

    }

    @Override
    public void authentication(String username,String password){
        User user=findUserByUserName(username);
        if(user.getPassword().equals(hashPassword.passwordHasher(password))){
            System.out.println("ID : "+ user.getUserId());
            System.out.println("username : "+ user.getUsername());
            System.out.println(" role ==> "+ user.getRoles());
        }
        else
            System.out.println("###[ERROR:]==> password not correct");
    }

}
