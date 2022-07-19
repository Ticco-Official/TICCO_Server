package com.ticco.service.auth.dto.request;

import com.ticco.domain.user.UserSocialType;
import com.ticco.service.user.dto.request.CreateUserDto;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpDto {

    private UserSocialType socialType;

    private String token;

    public static SignUpDto of(UserSocialType socialType, String token) {
        return new SignUpDto(socialType, token);
    }

    public CreateUserDto toCreateUserDto(String socialId) {
        return CreateUserDto.of(socialId, socialType);
    }
}
