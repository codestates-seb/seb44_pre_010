package com.rainbow.sof.user.dto.singleDto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserDto {

    @Getter
    public static class SignUpPost{

        @NotBlank
        private String name;

        @NotBlank
        @Size(min = 8)
        private String password;

        @NotBlank
        @Email
        private String email;
    }

    @Getter
    public static class LoginPost{

        @NotBlank
        private String username;

        @NotBlank
        @Size(min = 8)
        private String password;
    }

    @Getter
    public static class Patch{
        @NotBlank
        private String name;
    }

    @Getter
    public static class Response{
        private long memberId;
        private String name;
        private String email;
        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

    }


}
