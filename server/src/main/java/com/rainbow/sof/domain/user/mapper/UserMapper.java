package com.rainbow.sof.domain.user.mapper;

import com.rainbow.sof.domain.question.dto.QuestionDto;
import com.rainbow.sof.domain.user.entity.User;
import com.rainbow.sof.domain.user.dto.UserToJoinDto.MyPageResponseDto;
import com.rainbow.sof.domain.user.dto.singleDto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userSignupPostToUser(UserDto.SignUpPost signUpPost);

    User userLoginPostTouser(UserDto.CreationLoginDto creationLoginDto);

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
