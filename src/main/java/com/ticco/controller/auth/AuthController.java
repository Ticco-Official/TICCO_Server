package com.ticco.controller.auth;

import com.ticco.common.dto.ApiResponse;
import com.ticco.common.success.SuccessCode;
import com.ticco.controller.auth.dto.request.LoginRequestDto;
import com.ticco.controller.auth.dto.response.LoginResponse;
import com.ticco.service.auth.AuthService;
import com.ticco.service.auth.AuthServiceProvider;
import com.ticco.service.auth.CreateTokenService;
import com.ticco.service.auth.dto.request.TokenRequestDto;
import com.ticco.service.auth.dto.response.TokenResponseDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceProvider authServiceProvider;
    private final CreateTokenService createTokenService;

    @ApiOperation("로그인 페이지 - 로그인을 요청합니다")
    @PostMapping("/v1/auth/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequestDto request) {
        AuthService authService = authServiceProvider.getAuthService(request.getSocialType());
        Long userId = authService.login(request.toServiceDto());

        TokenResponseDto tokenInfo = createTokenService.createTokenInfo(userId);
        return ApiResponse.success(SuccessCode.LOGIN_SUCCESS, LoginResponse.of(userId, tokenInfo));
    }

    @ApiOperation("JWT 인증 - Access Token을 갱신합니다.")
    @PostMapping("/v1/auth/refresh")
    public ApiResponse<TokenResponseDto> reissue(@Valid @RequestBody TokenRequestDto request) {
        return ApiResponse.success(SuccessCode.REISSUE_TOKEN_SUCCESS, createTokenService.reissueToken(request));
    }
}
