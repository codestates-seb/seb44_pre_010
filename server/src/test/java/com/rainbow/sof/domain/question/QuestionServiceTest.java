package com.rainbow.sof.domain.question;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.repository.QuestionRepository;
import com.rainbow.sof.domain.question.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuestionServiceTest {
    @Autowired
    private QuestionService service;
    @Autowired
    private QuestionRepository repository;

    @Test
    @DisplayName("서비스에서 Quetion이 추가된다.")
    void saveQuestion() throws Exception {
        String title = "새로추가한 제목인데용. 제목제목제목인데여.";
        String content = "내용인데용. 내용내용내용";
        //given
        Question question = Question.builder()
                .title(title)
                .content(content)
                .build();
        //when
        service.createQuestion(question);
        Question saveQuestion = repository.findById(question.getQuestionId()).orElseThrow();
        //then
        assertThat(saveQuestion.getQuestionId()).isEqualTo(question.getQuestionId());
        assertThat(saveQuestion.getContent()).isEqualTo(question.getContent());
        assertThat(saveQuestion.getTitle()).isEqualTo(question.getTitle());

    }

    @Test
    @DisplayName("서비스에서 Question이 수정된다.")
    void updateQuestion() throws Exception {
        String title = "수정할 때 사용할 제목인데용. 제목제목제목인데여.";
        String content = "내용인데용. 내용내용내용";
        //given
        Question question = Question.builder()
                .questionId(1L)
                .title(title)
                .view(0)
                .content(content)
                .build();

        String updateTitle = "수정된 제목이요제목제목제목이요 제목";
        Question updateQuestion = Question.builder()
                .title(updateTitle)
                .build();

        //when
        repository.save(question);
        service.updateQuestion(1L, updateQuestion);

        Question saveQuestion = repository.findById(question.getQuestionId()).orElseThrow();
        //then
        assertThat(saveQuestion.getQuestionId()).isEqualTo(question.getQuestionId());
        assertThat(saveQuestion.getContent()).isEqualTo(question.getContent());
        assertThat(saveQuestion.getTitle()).isEqualTo(updateQuestion.getTitle());
    }

}
