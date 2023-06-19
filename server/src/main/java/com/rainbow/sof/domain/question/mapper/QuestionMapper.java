package com.rainbow.sof.domain.question.mapper;

import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {

    @Mapping(source="userId",target = "user.userId")
    Question questionDtoPostToQuestion(QuestionDto.Post request);
    Question questionDtoPatchToQuestion(QuestionDto.Patch request);
    QuestionDto.Response questionToQuestionDtoResponse(Question question);
}
