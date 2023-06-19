package com.rainbow.sof.domain.user.controller;


<<<<<<< HEAD
<<<<<<< HEAD:server/src/main/java/com/rainbow/sof/user/controller/UserController.java
import com.rainbow.sof.user.dto.singleDto.*;
import com.rainbow.sof.user.repository.UserRepository;
import com.rainbow.sof.user.service.UserService;
import com.rainbow.sof.utils.UriCreator;
=======
=======
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.mapper.UserMapper;
import com.rainbow.sof.domain.user.service.UserService;
import com.rainbow.sof.global.utils.UriCreator;
import com.rainbow.sof.domain.user.dto.DataDto.UserDataResponse;
import com.rainbow.sof.domain.user.dto.UserToJoinDto.MyPageResponseDto;
<<<<<<< HEAD
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1:server/src/main/java/com/rainbow/sof/domain/user/controller/UserController.java
=======
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

<<<<<<< HEAD
    private final UserRepository repository;
    private final UserService service;

    @PostMapping("/signup")
    public ResponseEntity<?> postSignup(@Valid @RequestBody UserDto.SignUpPost signUpPost){

        URI location = UriCreator.createUri("",1);
=======
    private final UserMapper mapper;
    private final UserService service;
    private final static String USER_DEFAULT_URL = "/api/v1/users";
    private final static String DELETE_ACTION_URL = "/signup";

    @PostMapping("/signup")
    public ResponseEntity<?> postSignup(@Valid @RequestBody UserDto.SignUpPost signUpPost){
        User user = mapper.userSignupPostToUser(signUpPost);
        User createUser=service.createUser(user);
        URI location = UriCreator.createUri(USER_DEFAULT_URL, createUser.getUserId());
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
        return ResponseEntity.created(location).build();
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> postLogin(@Valid @RequestBody UserDto.CreationLoginDto loginPost){
//        User findUser = service.findByUserFromEmail(loginPost.getUsername());
//        URI location = UriCreator.createUri("",1);
//        return ResponseEntity.ok().location(location).build();
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> postLogin(){
//
//        URI location = UriCreator.createUri("",1);
//        return ResponseEntity.ok().location(location).build();
//    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<?> getUserData(@Valid @PathVariable("user-id") @Positive long id){
        User user = service.findVerifiedUser(id);
        MyPageResponseDto myPageDto= mapper.userToMyPageDto(user);
        UserDataResponse response=UserDataResponse.builder().data(myPageDto).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/users/{user-id}")
    public ResponseEntity<?> patchUser(Principal principal,
                                         @Valid @PathVariable("user-id") @Positive long id,
                                       @RequestBody UserDto.Patch patch){

        return ResponseEntity.ok("responseBody");
    }

<<<<<<< HEAD
=======
    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> deleteUser(@Valid @PathVariable("user-id") @Positive long id){
        service.deleteUser(id);
        URI location = UriCreator.createUri(DELETE_ACTION_URL);
        return ResponseEntity.noContent().location(location).build();
    }

>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732

}
