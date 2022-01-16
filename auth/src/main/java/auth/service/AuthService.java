package auth.service;

import auth.dto.AuthRequest;
import auth.dto.AuthResponse;

/**
 * @author datdv
 */
public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
}
