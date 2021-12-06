package com.backend.survey.controller;

import com.backend.survey.response.MessageResponse;
import com.backend.survey.response.DataResponse;
import com.backend.survey.response.UserResponse;
import com.backend.survey.services.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
