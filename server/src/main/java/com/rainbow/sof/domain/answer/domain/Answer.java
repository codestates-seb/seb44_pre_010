package com.rainbow.sof.domain.answer.domain;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Answer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    private User user;

    public void insertUser(User user) {
        this.user = user;
    }

    public void insertQuestion(Question question) {
        this.question = question;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
