package auth.service.impl;

import auth.api.clients.UserDTOClients;
import auth.enitty.UserEntity;
import auth.repository.UserRepository;
import auth.service.UserService;
import auth.utils.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author datdv
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * checkUser
     *
     * @param userId
     * @return
     */
    @Override
    public UserDTOClients checkUser(Long userId) {
        UserEntity entity = userRepository.findById(userId).orElse(null);
        return Objects.isNull(entity) ? null : Converter.toModel(entity, UserDTOClients.class);
    }
}
