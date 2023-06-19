package com.rainbow.sof.domain.question.user.mapper;

import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.question.user.entity.User;
import com.rainbow.sof.domain.question.user.dto.UserToJoinDto.MyPageResponseDto;
import com.rainbow.sof.domain.question.user.dto.singleDto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userSignupPostToUser(UserDto.SignUpPost signUpPost);

    default MyPageResponseDto userToMyPageDto(User user){
        List<QuestionDto.Response> questionList = user.getQuestionList().stream()
                .map(question -> QuestionDto.Response.builder()
                        .questionId(question.getQuestionId())
                        .content(question.getContent())
                        .title(question.getTitle())
                        .build())
                .collect(Collectors.toList());

        return MyPageResponseDto.builder()
                .name(user.getName())
                .questionList(questionList)
                .build();
    }
}
