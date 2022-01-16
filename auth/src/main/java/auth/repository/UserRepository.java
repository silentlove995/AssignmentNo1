package auth.repository;

import auth.enitty.RoleEntity;
import auth.enitty.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 *
 * @author DatDV
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameAndStatus(String username, int status);
    UserEntity findOneByUsername(String username);
    Long countByRoles(RoleEntity roles);
}