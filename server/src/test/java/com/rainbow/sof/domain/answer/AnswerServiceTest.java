//package com.rainbow.sof.domain.answer;
//
//import com.rainbow.sof.domain.answer.domain.Answer;
//import com.rainbow.sof.domain.answer.repository.AnswerRepository;
//import com.rainbow.sof.domain.answer.service.AnswerService;
//import com.rainbow.sof.domain.question.domain.Question;
//import com.rainbow.sof.domain.question.repository.QuestionRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//public class AnswerServiceTest {
//    @Autowired
//    private AnswerService answerService;
//    @Autowired
//    private QuestionRepository questionRepository;
//    @Autowired
//    private AnswerRepository answerRepository;
//
//    @Test
//    @DisplayName("답변 저장")
//    void createAnswer() throws Exception {
//        Question question = Question.builder()
//                .questionId(1L)
//                .build();
//        //given
//        Answer answer = Answer.builder()
//                .question(question)
//                .content("12345678901234567890123456789012345678901")
//                .build();
//        //when
//        answerService.createAnswer(question.getQuestionId(), answer);
//        Answer createAnswer = answerRepository.findById(answer.getAnswerId()).orElseThrow();
//        //then
//        assertThat(createAnswer.getAnswerId()).isEqualTo(answer.getAnswerId());
//        assertThat(createAnswer.getContent()).isEqualTo(answer.getContent());
//    }
//}
