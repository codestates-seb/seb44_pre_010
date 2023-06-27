package com.rainbow.sof.domain.user.dto.UserToJoinDto;


import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
public class MyPageResponseDto{
    private String name;
    private LocalDateTime createdAt;
    private List<QuestionDto.MyPageQuestionResponse> questionList;

    private List<AnswerDto.MyPageAnswerResponse> AnswerList;

}
