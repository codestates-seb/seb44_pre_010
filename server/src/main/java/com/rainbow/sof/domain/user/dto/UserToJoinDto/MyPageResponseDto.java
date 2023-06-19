package com.rainbow.sof.domain.user.dto.UserToJoinDto;


import com.rainbow.sof.domain.question.dto.QuestionDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class MyPageResponseDto{
    private String name;
    private List<QuestionDto.Response> questionList;

}
