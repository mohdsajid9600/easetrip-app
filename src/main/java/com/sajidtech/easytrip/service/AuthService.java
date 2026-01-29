package com.sajidtech.easytrip.service;

import com.sajidtech.easytrip.dto.request.LoginRequest;
import com.sajidtech.easytrip.dto.request.SignupRequest;

public interface AuthService {

    String register(SignupRequest request);

    String login(LoginRequest request);
}
