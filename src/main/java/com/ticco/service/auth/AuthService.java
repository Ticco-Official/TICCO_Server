package com.ticco.service.auth;

import com.ticco.service.auth.dto.request.LoginDto;
import com.ticco.service.auth.dto.request.SignUpDto;

public interface AuthService {

    Long signUp(SignUpDto request);

    Long login(LoginDto request);
}
