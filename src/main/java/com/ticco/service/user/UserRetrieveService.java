package com.ticco.service.user;

import com.ticco.domain.user.Onboarding;
import com.ticco.domain.user.User;
import com.ticco.domain.user.repository.UserRepository;
import com.ticco.service.user.dto.response.CheckOnboardingInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserRetrieveService {

    private final UserRepository userRepository;

    public CheckOnboardingInfoResponse checkMyOnboardingInfo(Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Onboarding onboarding = user.getOnboarding();
        if (!onboarding.isChecked()) return CheckOnboardingInfoResponse.of(false);
        else return CheckOnboardingInfoResponse.of(true);
    }
}
