package com.rainbow.sof.domain.answer.mapper;

import com.rainbow.sof.domain.answer.domain.Answer;
import com.rainbow.sof.domain.answer.dto.AnswerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AnswerMapper {

    @Mapping(source = "questionId", target = "question.questionId")
    Answer answerDtoPostToAnswer(AnswerDto.Post request);


    Answer answerDtoPatchToAnswer(AnswerDto.Patch request);

    AnswerDto.Response answerToAnswerDtoResponse(Answer answer);
}
