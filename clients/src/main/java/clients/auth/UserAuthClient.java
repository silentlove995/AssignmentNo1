package clients.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author datdv
 */
@FeignClient(
        name = "auth",
        url = "${clients.auth.url}"
)
public interface UserAuthClient {

    @GetMapping(value = "/client/user/auth/check-user/{userId}")
    UserDTOClients getUser(@PathVariable("userId") Long userId);

}
