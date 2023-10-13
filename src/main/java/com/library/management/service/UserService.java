package com.library.management.service;

import com.library.management.dto.UserDTO;
import com.library.management.dto.UserDataDTO;
import com.library.management.dto.UserLoanDataDTO;
import com.library.management.model.*;
import com.library.management.repository.IBookRepository;
import com.library.management.repository.ILoanRepository;
import com.library.management.repository.IRoleRepository;
import com.library.management.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private IRoleRepository roleRepo;

    @Autowired
    private ILoanRepository loanRepo;

    @Autowired
    private IBookRepository bookRepo;

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
                .id(newUser.getId())
                .username(newUser.getUsername())
                .password(newUser.getPassword())
                .email(newUser.getEmail())
                .phoneNumber(newUser.getPhoneNumber())
                .role(role.getName().toString())
                .build();
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity userFound = userRepo.findById(id).orElse(null);
        Role role;

        if (userFound == null) {
            return null;
        }

        try {
            role = roleRepo.findByName(ERole.valueOf(userDTO.getRole().toUpperCase())).orElseThrow();
        } catch (Exception e) {
            role = roleRepo.findByName(ERole.USER).orElse(null);
        }

        userFound.setUsername(userDTO.getUsername());
        userFound.setPassword(userDTO.getPassword());
        userFound.setEmail(userDTO.getEmail());
        userFound.setPhoneNumber(userDTO.getPhoneNumber());
        userFound.setRole(role);

        userRepo.save(userFound);

        return UserDTO.builder()
                .id(userFound.getId())
                .username(userFound.getUsername())
                .password(userFound.getPassword())
                .email(userFound.getEmail())
                .phoneNumber(userFound.getPhoneNumber())
                .role(userFound.getRole().getName().toString())
                .build();
    }

    @Override
    public UserDataDTO getUser(Long id) {
        UserEntity userFound = userRepo.findById(id).orElse(null);

        if (userFound == null) {
            return null;
        }

        List<UserLoanDataDTO> loans = new ArrayList<>();

        for (Loan loan : userFound.getLoans()) {
            Book book = bookRepo.findById(loan.getBook().getId()).orElse(null);

            if (book == null) {
                return null;
            }

            loans.add(UserLoanDataDTO.builder()
                    .id_loan(loan.getId())
                    .book_id(book.getId())
                    .book_title(book.getTitle())
                    .startDate(loan.getStartDate())
                    .endDate(loan.getEndDate())
                    .build());
        }

        return UserDataDTO.builder()
                .id(userFound.getId())
                .username(userFound.getUsername())
                .email(userFound.getEmail())
                .password(userFound.getPassword())
                .phoneNumber(userFound.getPhoneNumber())
                .role(userFound.getRole().getName().toString())
                .loans(loans)
                .build();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}