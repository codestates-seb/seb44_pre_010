package com.rainbow.sof.domain.user.controller;


import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.mapper.UserMapper;
import com.rainbow.sof.domain.user.service.UserService;
import com.rainbow.sof.global.common.AuthenticationName;
import com.rainbow.sof.global.utils.UriCreator;
import com.rainbow.sof.domain.user.dto.DataDto.UserDataResponse;
import com.rainbow.sof.domain.user.dto.UserToJoinDto.MyPageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.security.Principal;

import static com.rainbow.sof.domain.user.util.CustomEnumUri.*;

@RestController
@RequestMapping("/api/v1")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserMapper mapper;
    private final UserService service;

    @PostMapping("/signup")
    public ResponseEntity<?> postSignup(@Valid @RequestBody UserDto.SignUpPost signUpPost){
        User user = mapper.userSignupPostToUser(signUpPost);
        User createUser=service.createUser(user);
        URI location = UriCreator.createUri(USER_DEFAULT_URL.getUri(), createUser.getUserId());
        return ResponseEntity.created(location).build();
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> postLogin(@AuthenticationName String email){
//        User user = service.findByUserFromEmail(email);
//        URI location = UriCreator.createUri(USER_DEFAULT_URL,user.getUserId());
//        response.setHeader("Location", String.valueOf(location));
//        return ResponseEntity.ok().body(user.getUserId());
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> postLogin(){
//
//        URI location = UriCreator.createUri("",1);
//        return ResponseEntity.ok().location(location).build();
//    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<?> getUserData(@AuthenticationName String email,
                                         @Valid @PathVariable("user-id") @Positive long id){
        User user = service.checkToFindByUserFromEmail(email,id);
        MyPageResponseDto myPageDto= mapper.userToMyPageDto(user);
        return new ResponseEntity<>(new UserDataResponse<>(myPageDto), HttpStatus.OK);
    }

//    @Valid @PathVariable("user-id") @Positive long id

    @PatchMapping("/users/{user-id}")
    public ResponseEntity<?> patchUser(@AuthenticationName String email,
                                       @Valid @PathVariable("user-id") @Positive long id,
                                       @RequestBody UserDto.Patch patch){
        User updateUser = service.updateUser(email,id,patch);
        URI location = UriCreator.createUri(USER_DEFAULT_URL.getUri(), updateUser.getUserId());
        return ResponseEntity.ok().location(location).build();
    }

    @DeleteMapping("/users/{user-id}")
    public ResponseEntity<?> deleteUser(@AuthenticationName String email,
                                        @Valid @PathVariable("user-id") @Positive long id){
        service.deleteUser(email,id);
        URI location = UriCreator.createUri(DELETE_ACTION_URL.getUri());
        return ResponseEntity.noContent().location(location).build();
    }


}