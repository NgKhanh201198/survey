package com.backend.survey.controller;


import com.backend.survey.config.security.JwtUtils;
import com.backend.survey.dto.UserDTO;
import com.backend.survey.response.JwtResponse;
import com.backend.survey.response.MessageResponse;
import com.backend.survey.services.IUserService;
import com.backend.survey.services.UserDetailsImpl;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateToken(authentication);

            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();

            if (userDetailsImpl == null) {
                return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(), "Kh??ng t??m th???y ng?????i d??ng!"));
            }

            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (BadCredentialsException ex) {
            MessageResponse message = new MessageResponse(new Date(), HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name(),
                    "T??i kho???n ho???c m???t kh???u c???a b???n kh??ng ch??nh x??c!");
            return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            if (userService.existsByUsername(userDTO.getUsername())) {
                return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.name(), "T??i kho???n n??y ???? ???????c s??? d???ng!"));
            }
            userService.register(userDTO);
            return ResponseEntity.ok(new MessageResponse(new Date(), HttpStatus.OK.value(),
                    HttpStatus.OK.name(), "T??i kho???n ???? ???????c t???o th??nh c??ng!"));
        } catch (DataException exception) {
            return ResponseEntity.badRequest().body(new MessageResponse(new Date(), HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.name(), exception.getMessage()));
        }
    }
}
