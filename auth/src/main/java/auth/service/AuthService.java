package auth.service;

import auth.dto.AuthRequest;
import auth.dto.AuthResponse;
import com.microservices.auth.dto.AuthRequest;
import com.microservices.auth.dto.AuthResponse;

/**
 * @author datdv
 */
public interface AuthService {
    AuthResponse login(AuthRequest authRequest);

    AuthResponse login(AuthRequest authRequest);
}
