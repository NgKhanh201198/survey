package com.backend.survey.controller;

import com.backend.survey.dto.UserDTO;
import com.backend.survey.response.MessageResponse;
import com.backend.survey.response.DataResponse;
import com.backend.survey.response.UserResponse;
import com.backend.survey.services.IUserService;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") long id) {
        try {
            List<UserResponse> list = new ArrayList<>();
            list.add(userService.getById(id));

            return ResponseEntity.ok(new DataResponse(new Date(), HttpStatus.OK.value(), HttpStatus.OK.name(), list));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        try {
            if (userService.existsByUsername(userDTO.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(), "Tài khoản này đã được sử dụng!"));
            }
            userService.addUser(userDTO);
            return ResponseEntity.ok(new MessageResponse(new Date(), HttpStatus.OK.value(),
                    HttpStatus.OK.name(), "Tài khoản đã được tạo thành công!"));
        } catch (DataException exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }
}
