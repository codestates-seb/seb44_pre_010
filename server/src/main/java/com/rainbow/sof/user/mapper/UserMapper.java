package com.rainbow.sof.user.mapper;

import com.rainbow.sof.user.dto.singleDto.UserDto;
import com.rainbow.sof.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userSignupPostToUser(UserDto.SignUpPost signUpPost);
}
