package com.ticco.service.user;

import com.ticco.domain.user.User;
import com.ticco.domain.user.repository.UserRepository;
import com.ticco.service.user.dto.response.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserRetrieveService {

    private final UserRepository userRepository;

    public UserInfoResponse getUserInfo(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        return UserInfoResponse.of(user);
    }
}
