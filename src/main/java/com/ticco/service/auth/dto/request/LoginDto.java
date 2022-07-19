package com.ticco.service.auth.dto.request;

import com.ticco.domain.user.UserSocialType;
import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginDto {

    private UserSocialType socialType;

    private String token;

    public static LoginDto of(UserSocialType socialType, String token) {
        return new LoginDto(socialType, token);
    }
}
