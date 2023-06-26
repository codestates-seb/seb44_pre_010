package com.rainbow.sof.domain.user.entity;


import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.domain.QuestionVote;
import com.rainbow.sof.global.common.BaseTimeEntity;
import com.rainbow.sof.global.error.BusinessLogicException;
import com.rainbow.sof.global.error.ExceptionCode;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity(name = "USERS")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Builder
    public User(long userId, String password, String email, String name,
                List<Question> questionList, List<Answer> answerList) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.questionList = questionList;
        this.answerList = answerList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false)
    private String password;
    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    //    @Builder.Default
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Status status =Status.USER_ACTIVE;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private oAuthCheck oAuth =oAuthCheck.NO_OAUTH;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Question> questionList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<QuestionVote> questionVotes;

    public enum Status{
        USER_ACTIVE("활성상태"),
        USER_QUIT("임시 탈퇴 상태");

        @Getter
        private final String status;

        Status(String status) {
            this.status = status;
        }
    }

    public enum oAuthCheck{
        GOOGLE("GOOGLE"),
        FACEBOOK("FACEBOOK"),
        NO_OAUTH("No_Oauth_User");

        @Getter
        private final String status;

        oAuthCheck(String status) {
            this.status = status;
        }
    }

    public void updateOAuth(oAuthCheck oAuth) {this.oAuth = oAuth;}

    public void updateStatus(Status status) {
        this.status = status;
    }

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

    public void updateAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void checkIsMyself(long loginUserId){
        if(this.userId != loginUserId){
            throw new BusinessLogicException(ExceptionCode.USER_MISMATCH);
        }
    }
}