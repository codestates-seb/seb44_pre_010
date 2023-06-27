package com.rainbow.sof.domain.question.domain;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.global.common.BaseTimeEntity;
import com.rainbow.sof.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

import static com.rainbow.sof.domain.question.domain.QuestionVote.QuestionVoteStatus.VOTE_UP;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Question extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "integer default 0")
    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID")
    private User user;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @Column(columnDefinition = "integer default 0")
    private int vote;
    @OneToMany(mappedBy = "question")
    private List<QuestionVote> questionVotes;

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateContent(String content){
        this.content = content;
    }

    public void insertUser(User user){
        this.user = user;
    }

    public void updateView(){
        this.view++;
    }

    public boolean hasAnswers() {
        return !this.answers.isEmpty();
    }

    public void calculatePostVote(QuestionVote.QuestionVoteStatus status){
        if(status.equals(VOTE_UP)) this.vote++;
        else                       this.vote--;
    }

    public void calculateDeleteVote(QuestionVote.QuestionVoteStatus status){
        if(status.equals(VOTE_UP)) this.vote--;
        else                       this.vote++;
    }
}
