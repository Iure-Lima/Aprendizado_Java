package com.api.parkingcontrol.controllers;

import com.api.parkingcontrol.DTOs.AuthenticationDTO;
import com.api.parkingcontrol.DTOs.RegisterDTO;
import com.api.parkingcontrol.models.UserModel;
import com.api.parkingcontrol.services.TokenService;
import com.api.parkingcontrol.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    TokenService tokenService;

    final AuthenticationManager authenticationManager;
    final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(),data.password());
//        var auth = this.authenticationManager.authenticate(usernamePassword);

        var auth = userService.loadUserByUsername(data.login());
        if (auth.getPassword().equals(data.password())){
            var token = tokenService.generateToken((UserModel) auth);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Invalid Password");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if (userService.existsUser(registerDTO.login())) return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserModel newUser = new UserModel();
        BeanUtils.copyProperties(registerDTO, newUser);
        /*newUser.setPassword(encryptedPassword);*/
        userService.saveUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
