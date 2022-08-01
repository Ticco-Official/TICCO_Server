package com.ticco.service.auth;

import com.ticco.service.auth.dto.request.LoginDto;

public interface AuthService {

    Long login(LoginDto request);
}
