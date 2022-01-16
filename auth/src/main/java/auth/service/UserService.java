package auth.service;


import auth.api.clients.UserDTOClients;

/**
 * @author datdv
 */
public interface UserService {
    UserDTOClients checkUser(Long userId);
}
