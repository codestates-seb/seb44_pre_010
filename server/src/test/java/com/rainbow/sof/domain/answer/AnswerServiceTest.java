package com.rainbow.sof.domain.answer;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.repository.AnswerRepository;
import com.rainbow.sof.domain.answer.service.AnswerService;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AnswerServiceTest {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    @DisplayName("답변 저장")
    void createAnswer() throws Exception {
        //given
        Question question = Question.builder()
                .title("새로추가한 제목인데용. 제목제목제목인데여.")
                .content("내용인데용. 내용내용내용")
                .build();
        Answer answer = Answer.builder()
                .content("12345678901234567890123456789012345678901")
                .build();
        //when
        questionRepository.save(question);
        answerService.createAnswer(question.getQuestionId(), answer);
        Answer createdAnswer = answerRepository.findById(answer.getAnswerId()).orElseThrow();
        //then
        assertEquals(createdAnswer.getAnswerId(), answer.getAnswerId());
        assertEquals(createdAnswer.getContent(), answer.getContent());
    }

    @Test
    @DisplayName("답변 수정")
    void updateAnswer() throws Exception {
        //given
        Question question = Question.builder()
                .title("새로추가한 제목인데용. 제목제목제목인데여.")
                .content("내용인데용. 내용내용내용")
                .build();

        Answer answer = Answer.builder()
                .content("12345678901234567890123456789012345678901")
                .build();

        Answer updateAnswer = Answer.builder()
                .content("12345678901234567890123456789012345678902")
                .build();
        //when
        questionRepository.save(question);
        answerService.createAnswer(question.getQuestionId(), answer);
        answerService.updateAnswer(question.getQuestionId(), answer.getAnswerId(), updateAnswer);

        Answer updatedAnswer = answerRepository.findById(answer.getAnswerId()).orElseThrow();
        //then
        assertEquals(updatedAnswer.getAnswerId(), answer.getAnswerId());
        assertEquals(updatedAnswer.getContent(), updateAnswer.getContent());
    }
}
