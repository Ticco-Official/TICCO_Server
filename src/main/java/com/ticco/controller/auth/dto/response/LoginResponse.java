package com.ticco.controller.auth.dto.response;

import com.ticco.service.auth.dto.response.TokenResponseDto;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponse {

    private Long userId;
    private TokenResponseDto token;

    public static LoginResponse of(Long userId, TokenResponseDto token) {
        return new LoginResponse(userId, token);
    }
}
