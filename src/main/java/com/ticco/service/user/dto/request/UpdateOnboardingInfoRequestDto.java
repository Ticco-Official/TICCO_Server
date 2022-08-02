package com.ticco.service.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateOnboardingInfoRequestDto {

    @ApiModelProperty(value = "닉네임", example = "혁돌몬", required = true)
    @Length(min = 1, max = 6, message = "{onboarding.nickname.length}")
    @NotBlank(message = "{onboarding.nickname.notBlank}")
    private String nickname;
}
