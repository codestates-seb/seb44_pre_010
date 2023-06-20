package com.rainbow.sof.domain.user.dto.singleDto;

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
    public static class CreationLoginDto{

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
