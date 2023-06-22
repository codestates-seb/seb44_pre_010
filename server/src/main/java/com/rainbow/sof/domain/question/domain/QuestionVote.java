package com.rainbow.sof.domain.question.domain;

import com.rainbow.sof.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class QuestionVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionVoteId;

    @Column(nullable = false)
    private QuestionVoteStatus questionVoteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private Question question;

    @AllArgsConstructor
    public enum QuestionVoteStatus {
        VOTE_UP("up"),
        VOTE_DOWN("down");
        @Getter
        private String value;
    }

    public static QuestionVote createSelf(Question question, User user, String status){
        return QuestionVote.builder()
                .question(question)
                .user(user)
                .questionVoteStatus(status.equals("up")
                        ? QuestionVote.QuestionVoteStatus.VOTE_UP
                        : QuestionVote.QuestionVoteStatus.VOTE_DOWN)
                .build();
    }
}
