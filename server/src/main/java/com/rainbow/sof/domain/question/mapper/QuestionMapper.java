package com.rainbow.sof.domain.question.mapper;

import com.rainbow.sof.domain.answer.dto.AnswerDto;
import com.rainbow.sof.domain.question.domain.Question;
import com.rainbow.sof.domain.question.dto.QuestionDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface QuestionMapper {

    @Mapping(source="userId",target = "user.userId")
    Question questionDtoPostToQuestion(QuestionDto.Post request);
<<<<<<< HEAD
    QuestionDto.Response questionToQuestionDtoResponse(Question question);
=======
    Question questionDtoPatchToQuestion(QuestionDto.Patch request);

    @Mapping(source = "user.userId", target = "user.userId")
    @Mapping(source = "user.name", target = "user.name")
    List<QuestionDto.ListResponse> questionToQuestionDtoResponseList(List<Question> request);

    default QuestionDto.Response questionToQuestionDtoResponse(Question question){
        if ( question == null ) {
            return null;
        }

        QuestionDto.Response.ResponseBuilder response = QuestionDto.Response.builder();

        response.questionId( question.getQuestionId() );
        response.view( question.getView() );
        response.title( question.getTitle() );
        response.content( question.getContent() );
        response.createdAt( question.getCreatedAt() );
        response.modifiedAt( question.getModifiedAt() );

        // TODO: USER 로그인 완료되면 추가
        /*response.user( UserDto.QuestionResponse.builder()
                .userId(question.getUser().getUserId())
                .name(question.getUser().getName())
                .build() );*/

        response.answers( question.getAnswers().stream().map(o -> {
            AnswerDto.Response commentDto = AnswerDto.Response.builder()
                    .answerId(o.getAnswerId())
                    .content(o.getContent())
                    .createdAt(o.getCreatedAt())
                    .modifiedAt(o.getModifiedAt())
                    .questionId(o.getQuestion().getQuestionId())
                    .build();
            return commentDto;
        }).collect(Collectors.toList()));

        return response.build();
    }
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
}
