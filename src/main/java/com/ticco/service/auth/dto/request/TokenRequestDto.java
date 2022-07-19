package com.ticco.service.auth.dto.request;

import lombok.*;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenRequestDto {

    private String accessToken;
    private String refreshToken;
}
