package auth.service.impl;

import auth.dto.AuthRequest;
import auth.dto.AuthResponse;
import auth.enitty.UserEntity;
import auth.exception.CommonUtils;
import auth.exception.CustomException;
import auth.repository.UserRepository;
import auth.service.AuthService;
import auth.utils.JwtUtil;
import com.microservice.gateway.constants.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author datdv
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * login
     *
     * @param authRequest
     * @return
     */
    @Override
    public AuthResponse login(AuthRequest authRequest) {
        UserEntity user = userRepository.findByUsernameAndStatus(authRequest.getUsername(), AppConstant.ACTIVE.ACTIVE_STATUS);
        if (Objects.isNull(user)) {
            throw new CustomException("username or password incorrect", CommonUtils.putError("username or password", "ERR_005"));
        }
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new CustomException("password incorrect", CommonUtils.putError("password", "ERR_005"));
        }
        String accessToken = jwtUtil.generate(user, "ACCESS");
        String refreshToken = jwtUtil.generate(user, "REFRESH");
        return new AuthResponse(user.getFullName(), accessToken, refreshToken);
    }
}
