package com.rainbow.sof.domain.answer.domain;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Column(columnDefinition = "integer default 0")
    private int vote;

    @OneToMany(mappedBy = "answer")
    private List<AnswerVote> answerVotes;

    public void insertUser(User user) {
        this.user = user;
    }

    public void insertQuestion(Question question) {
        this.question = question;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void calculatePostVote (AnswerVote.AnswerVoteStatus status) {
        if (status.equals(AnswerVote.AnswerVoteStatus.VOTE_UP)) this.vote++;
        else                                                    this.vote--;
    }
    public void calculateDeleteVote(AnswerVote.AnswerVoteStatus status) {
        if (status.equals(AnswerVote.AnswerVoteStatus.VOTE_UP)) this.vote--;
        else                                                    this.vote++;
    }
}
