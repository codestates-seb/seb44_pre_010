package com.rainbow.sof.domain.answer.domain;

import com.rainbow.sof.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class AnswerVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerVoteId;

    @Column(nullable = false)
    private AnswerVoteStatus answerVoteStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID")
    private Answer answer;

    @AllArgsConstructor
    public enum AnswerVoteStatus {
        VOTE_UP("up"),
        VOTE_DOWN("down");

        @Getter
        private String value;
    }

    public static AnswerVote createSelf (Answer answer, User user, String status) {
        return AnswerVote.builder()
                .answer(answer)
                .user(user)
                .answerVoteStatus(status.equals("up")
                        ? AnswerVoteStatus.VOTE_UP
                        : AnswerVoteStatus.VOTE_DOWN)
                .build();
    }
}
