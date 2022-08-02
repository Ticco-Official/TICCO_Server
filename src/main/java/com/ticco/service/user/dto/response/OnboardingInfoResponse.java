package com.ticco.service.user.dto.response;

import com.ticco.domain.user.Onboarding;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class OnboardingInfoResponse {

    private String nickname;

    private String profileImageUrl;

    public static OnboardingInfoResponse of(@NotNull Onboarding onboarding) {
        return OnboardingInfoResponse.builder()
                .nickname(onboarding.getNickname())
                .profileImageUrl(onboarding.getProfileImageUrl())
                .build();
    }
}
