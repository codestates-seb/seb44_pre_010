package com.rainbow.sof.domain.question.mapper;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {
    Question questionDtoPostToQuestion(QuestionDto.Post request);
    QuestionDto.Response questionToQuestionDtoResponse(Question question);
}
