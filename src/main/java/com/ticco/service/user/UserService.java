package com.ticco.service.user;

import com.ticco.domain.user.Onboarding;
import com.ticco.domain.user.User;
import com.ticco.domain.user.repository.UserRepository;
import com.ticco.service.user.dto.request.CreateUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long registerUser(CreateUserDto request) {
        UserServiceUtils.validateNotExistsUser(userRepository, request.getSocialId(), request.getSocialType());
        User user = userRepository.save(User.newInstance(request.getSocialId(), request.getSocialType(), Onboarding.newInstance()));
        return user.getId();
    }
}
