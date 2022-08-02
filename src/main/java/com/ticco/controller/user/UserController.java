package com.ticco.controller.user;

import com.ticco.common.dto.ApiResponse;
import com.ticco.config.interceptor.Auth;
import com.ticco.config.resolver.UserId;
import com.ticco.service.user.UserService;
import com.ticco.service.user.dto.request.UpdateOnboardingInfoRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Api(tags = "User")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation("[인증] 나의 온보딩 정보를 업데이트합니다.")
    @Auth
    @PutMapping("/v1/user/onboarding")
    public ApiResponse<String> updateOnboardingInfo(@Valid UpdateOnboardingInfoRequestDto request,
                                                    @RequestPart(required = false) MultipartFile image,
                                                    @ApiIgnore @UserId Long userId) {
        userService.updateOnboardingInfo(request, image, userId);
        return ApiResponse.SUCCESS;
    }
}
