package com.backend.survey.services;

import com.backend.survey.dto.UserDTO;

public interface IUserService {

    void register(UserDTO userDTO);

    boolean existsByUsername(String username);
}
