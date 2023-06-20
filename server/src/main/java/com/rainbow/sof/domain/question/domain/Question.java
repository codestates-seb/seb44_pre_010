package com.rainbow.sof.domain.question.domain;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.global.common.BaseTimeEntity;
import com.rainbow.sof.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

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

    @Column
    private int view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateContent(String content){
        this.title = content;
    }

    public void updateView(){
        this.view++;
    }
}
