package com.rainbow.sof.user.controller;


import com.rainbow.sof.global.utils.UriCreator;
import com.rainbow.sof.user.dto.singleDto.*;
import com.rainbow.sof.user.repository.UserRepository;
import com.rainbow.sof.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;
    private final UserService service;

    @PostMapping("/signup")
    public ResponseEntity<?> postSignup(@Valid @RequestBody UserDto.SignUpPost signUpPost){

        URI location = UriCreator.createUri("",1);
        return ResponseEntity.created(location).build();
    }


    @PostMapping("/login")
    public ResponseEntity<?> postLogin(@Valid @RequestBody UserDto.LoginPost loginPost){

        URI location = UriCreator.createUri("",1);
        return ResponseEntity.ok().location(location).build();
    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> postLogin(){
//
//        URI location = UriCreator.createUri("",1);
//        return ResponseEntity.ok().location(location).build();
//    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<?> getUserData(Principal principal,
                                         @Valid @PathVariable("user-id") @Positive long id){

        return ResponseEntity.ok("responseBody");
    }

    @PatchMapping("/users/{user-id}")
    public ResponseEntity<?> patchUser(Principal principal,
                                         @Valid @PathVariable("user-id") @Positive long id,
                                       @RequestBody UserDto.Patch patch){

        return ResponseEntity.ok("responseBody");
    }


}
