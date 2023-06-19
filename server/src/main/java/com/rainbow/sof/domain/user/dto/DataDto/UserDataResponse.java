package com.rainbow.sof.domain.user.dto.DataDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class UserDataResponse<T> {
    private T data;
}
