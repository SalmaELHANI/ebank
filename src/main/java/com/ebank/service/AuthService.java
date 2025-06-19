package com.ebank.service;

import com.ebank.dto.AuthRequest;
import com.ebank.dto.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
    void changePassword(String oldPassword, String newPassword);
}