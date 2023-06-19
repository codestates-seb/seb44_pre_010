package com.rainbow.sof.domain.answer.domain;

import com.rainbow.sof.domain.question.domain.Question;
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

    @ManyToOne
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

//    TODO: 추후 Users 추가
//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private Users users;

    public void updateContent(String content) {
        this.content = content;
    }
}
