package com.ticco.service.user;

import com.ticco.common.type.FileType;
import com.ticco.domain.user.Onboarding;
import com.ticco.domain.user.User;
import com.ticco.domain.user.repository.OnboardingRepository;
import com.ticco.domain.user.repository.UserRepository;
import com.ticco.service.image.provider.S3Provider;
import com.ticco.service.image.provider.dto.request.ImageUploadFileRequest;
import com.ticco.service.user.dto.request.CreateUserDto;
import com.ticco.service.user.dto.request.UpdateOnboardingInfoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final OnboardingRepository onboardingRepository;
    private final S3Provider s3Provider;

    @Transactional
    public Long registerUser(CreateUserDto request) {
        UserServiceUtils.validateNotExistsUser(userRepository, request.getSocialId(), request.getSocialType());
        User user = userRepository.save(User.newInstance(request.getSocialId(), request.getSocialType(), Onboarding.newInstance()));
        return user.getId();
    }

    @Transactional
    public void updateOnboardingInfo(UpdateOnboardingInfoRequestDto request, MultipartFile image, Long userId) {
        User user = UserServiceUtils.findUserById(userRepository, userId);
        Onboarding onboarding = user.getOnboarding();
        String imageUrl;
        if (image == null) {
            imageUrl = onboarding.getProfileImageUrl();
        } else {
            s3Provider.deleteFile(onboarding.getProfileImageUrl());
            imageUrl = s3Provider.uploadFile(ImageUploadFileRequest.of(FileType.USER_PROFILE_IMAGE), image);
        }
        onboarding.updateInfo(request.getNickname(), imageUrl);
        onboardingRepository.save(onboarding);
    }
}
