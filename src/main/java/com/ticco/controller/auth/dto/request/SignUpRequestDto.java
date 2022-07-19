package com.ticco.controller.auth.dto.request;

import com.ticco.domain.user.UserSocialType;
import com.ticco.service.auth.dto.request.SignUpDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequestDto {

    @NotNull(message = "{user.socialType.notNull}")
    private UserSocialType socialType;

    @NotBlank(message = "{auth.token.notBlank}")
    private String token;

    public SignUpDto toServiceDto() {
        return SignUpDto.of(socialType, token);
    }
}
