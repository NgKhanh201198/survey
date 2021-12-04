package com.backend.survey.services;

import com.backend.survey.dto.UserDTO;
import com.backend.survey.response.UserResponse;

public interface IUserService {
    void addUser(UserDTO userDTO);

    UserResponse getById(long id);

    void register(UserDTO userDTO);

    boolean existsByUsername(String username);
}
