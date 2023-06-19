package com.rainbow.sof.domain.user.dto.singleDto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserDto {

    @Getter
    @Builder
    public static class SignUpPost{

        @NotBlank
        private String name;

        @NotBlank
        @Size(min = 8,message = "비밀번호는 최수 8자리 이상이어야합니다.")
        @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
        message = "비밀번호는 하나이상의 숫자와 문자가 조합 되어야합니다.")
        private String password;

        @NotBlank
        @Email
        private String email;
    }

    @Getter
    @Builder
    public static class LoginPost{

        @NotBlank
        private String username;

        @NotBlank
        @Size(min = 8)
        private String password;
    }

    @Getter
    @Builder
    public static class Patch{
        @NotBlank
        private String name;
    }

    @Getter
    @Builder
    public static class Response{
        private long memberId;
        private String name;
        private String email;
        private LocalDateTime createdAt;

        private LocalDateTime modifiedAt;

    }


}