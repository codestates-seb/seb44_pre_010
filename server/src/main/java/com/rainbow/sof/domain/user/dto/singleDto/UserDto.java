package com.rainbow.sof.domain.user.dto.singleDto;

<<<<<<< HEAD
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
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
<<<<<<< HEAD
<<<<<<< HEAD:server/src/main/java/com/rainbow/sof/user/dto/singleDto/UserDto.java
    public static class LoginPost{
=======
    public static class CreationLoginDto{
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1:server/src/main/java/com/rainbow/sof/domain/user/dto/singleDto/UserDto.java
=======
    public static class CreationLoginDto{
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732

        @Email(message = "Is Not email format")
        private String username;
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

    // @author : 윤다영
    @Getter
    @Builder
    @AllArgsConstructor
    public static class QuestionResponse{
        private long userId;
        private String name;
    }


}
