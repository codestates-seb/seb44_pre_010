package com.rainbow.sof.user.entity;


import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.global.common.BaseTimeEntity;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity(name = "USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false)
    private String password;
    @Email
    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.USER_ACTIVE;

//    @OneToMany(mappedBy = "USERS")
//    private List<Question> questionList;

    public enum Status{
        USER_ACTIVE("활성상태"),
        USER_QUIT("임시 탈퇴 상태");

        private final String status;

        Status(String status) {
            this.status = status;
        }
    }


}
