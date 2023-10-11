package com.library.management.service;

import com.library.management.dto.UserDTO;
import com.library.management.dto.UserDataDTO;
import com.library.management.model.ERole;
import com.library.management.model.Role;
import com.library.management.model.UserEntity;
import com.library.management.repository.IRoleRepository;
import com.library.management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IRoleRepository roleRepo;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Role role;

        try {
            role = roleRepo.findByName(ERole.valueOf(userDTO.getRole().toUpperCase())).orElseThrow();
        } catch (Exception e) {
            role = roleRepo.findByName(ERole.USER).orElse(null);
        }

        if (role == null) {
            return null;
        }

        UserEntity newUser = UserEntity.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(role)
                .build();

        userRepo.save(newUser);

        return UserDTO.builder()
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .phoneNumber(newUser.getPhoneNumber())
                .role(role.getName().toString())
                .build();
    }

    @Override
    public UserEntity updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDataDTO getUser(Long id) {
        UserEntity userFound = userRepo.findById(id).orElse(null);

        if (userFound == null) {
            return null;
        }

        return UserDataDTO.builder()
                .username(userFound.getUsername())
                .email(userFound.getEmail())
                .password(userFound.getPassword())
                .phoneNumber(userFound.getPhoneNumber())
                .role(userFound.getRole().getName().toString())
                .loans(userFound.getLoans())
                .build();
    }

    @Override
    public void deleteUser(Long id) {

    }
}