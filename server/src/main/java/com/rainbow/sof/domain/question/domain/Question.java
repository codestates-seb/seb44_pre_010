package com.rainbow.sof.domain.question.domain;

import com.rainbow.sof.global.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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

    //TODO: User 추가
    /*@ManyToOne
    @JoinColumn(name = "USER_ID")
    private User suer;*/

    public void updateTitle(String title){
        this.title = title;
    }

    public void updateContent(String content){
        this.title = content;
    }
}
