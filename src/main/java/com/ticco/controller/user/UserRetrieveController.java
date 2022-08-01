package com.ticco.controller.user;

import com.ticco.common.dto.ApiResponse;
import com.ticco.common.success.SuccessCode;
import com.ticco.config.interceptor.Auth;
import com.ticco.config.resolver.UserId;
import com.ticco.service.user.UserRetrieveService;
import com.ticco.service.user.dto.response.CheckOnboardingInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "User")
@RequiredArgsConstructor
@RestController
public class UserRetrieveController {

    private final UserRetrieveService userRetrieveService;

    @ApiOperation("[인증] 나의 온보딩 정보 등록여부를 확인합니다.")
    @Auth
    @GetMapping("/v1/user/me/onboarding/check")
    public ApiResponse<CheckOnboardingInfoResponse> checkMyOnboardingInfo(@ApiIgnore @UserId Long userId) {
        return ApiResponse.success(SuccessCode.CHECK_ONBOARDING_SUCCESS, userRetrieveService.checkMyOnboardingInfo(userId));
    }
}
