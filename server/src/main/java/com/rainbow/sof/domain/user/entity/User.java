package com.rainbow.sof.domain.user.entity;


<<<<<<< HEAD
import lombok.*;
=======
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.global.common.BaseTimeEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
<<<<<<< HEAD

@Getter
@Entity
@Setter
@NoArgsConstructor
public class User {
=======
import java.util.List;

@Getter
@Entity(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Builder
    public User(long userId, String password, String email, String name, List<Question> questionList) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.questionList = questionList;
    }

>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

<<<<<<< HEAD
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
=======
    @Column(nullable = false)
    private String password;
    @Email
    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

//    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status =Status.USER_ACTIVE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Question> questionList;
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732

    public enum Status{
        USER_ACTIVE("활성상태"),
        USER_QUIT("임시 탈퇴 상태");

<<<<<<< HEAD
=======
        @Getter
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
        private final String status;

        Status(String status) {
            this.status = status;
        }
    }

<<<<<<< HEAD


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
=======
    public void updateStatus(Status status) {
        this.status = status;
    }

>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
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
<<<<<<< HEAD
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1:server/src/main/java/com/rainbow/sof/domain/user/entity/User.java
=======
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
}
