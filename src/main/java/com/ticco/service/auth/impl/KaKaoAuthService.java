package com.ticco.service.auth.impl;

import com.ticco.common.util.HttpHeaderUtils;
import com.ticco.domain.user.repository.UserRepository;
import com.ticco.domain.user.UserSocialType;
import com.ticco.external.client.kakao.KakaoApiClient;
import com.ticco.external.client.kakao.dto.response.KakaoProfileResponse;
import com.ticco.service.auth.AuthService;
import com.ticco.service.auth.dto.request.LoginDto;
import com.ticco.service.auth.dto.request.SignUpDto;
import com.ticco.service.user.UserService;
import com.ticco.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KaKaoAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.KAKAO;

    private final KakaoApiClient kaKaoApiCaller;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Long signUp(SignUpDto request) {
        KakaoProfileResponse response = kaKaoApiCaller.getProfileInfo(HttpHeaderUtils.withBearerToken(request.getToken()));
        return userService.registerUser(request.toCreateUserDto(response.getId()));
    }

    @Override
    public Long login(LoginDto request) {
        KakaoProfileResponse response = kaKaoApiCaller.getProfileInfo(HttpHeaderUtils.withBearerToken(request.getToken()));
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, response.getId(), socialType).getId();
    }
}
