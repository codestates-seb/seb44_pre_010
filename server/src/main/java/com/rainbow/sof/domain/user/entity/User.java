package com.rainbow.sof.domain.user.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

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

<<<<<<< HEAD:server/src/main/java/com/rainbow/sof/user/entity/User.java
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt;
=======
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Question> questionList;
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1:server/src/main/java/com/rainbow/sof/domain/user/entity/User.java

    public enum Status{
        USER_ACTIVE("활성상태"),
        USER_QUIT("임시 탈퇴 상태");

        private final String status;

        Status(String status) {
            this.status = status;
        }
    }



    public User(long userId, String password, String email, String name, Status status, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

<<<<<<< HEAD:server/src/main/java/com/rainbow/sof/user/entity/User.java

=======
    public void updateQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUserId(long userId) {
        this.userId = userId;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateName(String name) {
        this.name = name;
    }
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1:server/src/main/java/com/rainbow/sof/domain/user/entity/User.java
}
