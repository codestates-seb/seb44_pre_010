package com.rainbow.sof.user.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long memberId;

    @Column
    private String password;
    @Email
    @Column
    private String email;

    @Column
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Status status = Status.USER_ACTIVE;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt;

    public enum Status{
        USER_ACTIVE("활성상태"),
        USER_QUIT("임시 탈퇴 상태");

        private final String status;

        Status(String status) {
            this.status = status;
        }
    }



    public Member(long memberId, String password, String email, String name, Status status, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


}
