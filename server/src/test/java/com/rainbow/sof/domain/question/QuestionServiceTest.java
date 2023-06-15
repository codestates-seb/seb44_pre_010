package com.rainbow.sof.domain.question;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.repository.QuestionRepository;
import com.rainbow.sof.domain.question.service.QuestionService;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void clean(){
        repository.deleteAll();
    }

    @Test
    @DisplayName("서비스에서 Quetion이 추가된다.")
    void saveQuestion() throws Exception {
        String title = "제목인데용. 제목제목제목인데여.";
        String content = "내용인데용. 내용내용내용";
        //given
        Question question = Question.builder()
                .questionId(1L)
                .title(title)
                .content(content)
                .view(0)
                .build();
        //when
        service.createQuestion(question);
        Question saveQuestion = repository.findById(question.getQuestionId()).orElseThrow();
        //then
        assertThat(saveQuestion.getQuestionId()).isEqualTo(question.getQuestionId());
        assertThat(saveQuestion.getContent()).isEqualTo(question.getContent());
        assertThat(saveQuestion.getTitle()).isEqualTo(question.getTitle());

    }

}
