package auth.api.clients;

import auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author datdv
 */
@RestController
@RequestMapping("/client/user/auth")
public class UserAPI {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/check-user/{userId}")
    public UserDTOClients getUser(@PathVariable("userId") Long userId) {
        return userService.checkUser(userId);
    }
}
