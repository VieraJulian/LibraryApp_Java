package com.library.management.service;

import com.library.management.dto.UserDTO;
import com.library.management.dto.UserDataDTO;
import com.library.management.model.UserEntity;

public interface IUserService {

    public UserDTO createUser(UserDTO userDTO);

    public UserDTO updateUser(Long id, UserDTO userDTO);

    public UserDataDTO getUser(Long id);

    public void deleteUser(Long id);
}
