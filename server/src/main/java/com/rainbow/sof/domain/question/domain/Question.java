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
@DynamicUpdate
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

<<<<<<< HEAD
    //TODO: User 추가
<<<<<<< HEAD
    /*@ManyToOne
    @JoinColumn(name = "USER_ID")
    private User suer;*/
=======
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
=======
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateContent(String content){
        this.title = content;
    }
<<<<<<< HEAD
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1
=======

    public void updateView(){
        this.view++;
    }
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
}
