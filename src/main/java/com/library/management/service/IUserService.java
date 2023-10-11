package com.library.management.service;

import com.library.management.dto.UserDTO;
import com.library.management.model.UserEntity;

public interface IUserService {

    public UserDTO createUser(UserDTO userDTO);

    public UserEntity updateUser(Long id, UserDTO userDTO);

    public UserEntity getUser(Long id);

    public void deleteUser(Long id);
}
