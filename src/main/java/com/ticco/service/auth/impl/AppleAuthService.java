package com.ticco.service.auth.impl;

import com.ticco.domain.user.User;
import com.ticco.domain.user.UserSocialType;
import com.ticco.domain.user.repository.UserRepository;
import com.ticco.external.client.apple.AppleTokenProvider;
import com.ticco.service.auth.AuthService;
import com.ticco.service.auth.dto.request.LoginDto;
import com.ticco.service.user.UserService;
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
    public Long login(LoginDto request) {
        String socialId = appleTokenDecoder.getSocialIdFromIdToken(request.getToken());
        User user = userRepository.findUserBySocialIdAndSocialType(socialId, socialType);
        if (user == null)
            return userService.registerUser(request.toCreateUserDto(socialId));
        return user.getId();
    }
}
