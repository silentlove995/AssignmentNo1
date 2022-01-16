package auth.api;

import auth.api.clients.ResponseEntityBuilder;
import auth.dto.AuthRequest;
import auth.dto.AuthResponse;
import auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author datdv
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthAPI {

    @Autowired
    private AuthService authService;

    /**
     * login
     *
     * @param authRequest
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        AuthResponse result = authService.login(authRequest);
        return ResponseEntityBuilder.getBuilder()
                .setDetails(result)
                .setMessage("LOGIN SUCCESSFULLY!!!")
                .setSuccess(true)
                .build();
    }
}
