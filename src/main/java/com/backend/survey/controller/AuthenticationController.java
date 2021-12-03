package com.backend.survey.controller;


import com.backend.survey.config.security.JwtUtils;
import com.backend.survey.dto.UserDTO;
import com.backend.survey.response.JwtResponse;
import com.backend.survey.response.MessageResponse;
import com.backend.survey.services.IUserService;
import com.backend.survey.services.UserDetailsImpl;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticationController(IUserService userService, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

        if (userDetailsImpl == null) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), "Không tìm thấy người dùng!"));
        }

        return ResponseEntity.ok(new JwtResponse(jwt));
//        return ResponseEntity.ok(new JwtResponse("jwtaaaaaaaaaaaaaaaaa"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            if (userService.existsByUsername(userDTO.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(), "Tài khoản này đã được sử dụng!"));
            }
            userService.register(userDTO);
            return ResponseEntity.ok(new MessageResponse(new Date(), HttpStatus.OK.value(),
                    HttpStatus.OK.name(), "Tài khoản đã được tạo thành công!"));
        } catch (DataException exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }


}
