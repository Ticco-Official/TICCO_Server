package com.ticco.service.auth.impl;

import com.ticco.domain.user.repository.UserRepository;
import com.ticco.domain.user.UserSocialType;
import com.ticco.external.client.apple.AppleTokenProvider;
import com.ticco.service.auth.AuthService;
import com.ticco.service.auth.dto.request.LoginDto;
import com.ticco.service.auth.dto.request.SignUpDto;
import com.ticco.service.user.UserService;
import com.ticco.service.user.UserServiceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppleAuthService implements AuthService {

    private static final UserSocialType socialType = UserSocialType.APPLE;

    private final AppleTokenProvider appleTokenDecoder;

    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public Long signUp(SignUpDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        return userService.registerUser(request.toCreateUserDto(socialId));
    }

    @Override
    public Long login(LoginDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        return UserServiceUtils.findUserBySocialIdAndSocialType(userRepository, socialId, socialType).getId();
    }
}
