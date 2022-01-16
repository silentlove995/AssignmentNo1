package auth.enitty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author datdv
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Integer status;

    @Column(name = "email")
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role" , joinColumns = @JoinColumn(name = "userid") ,
            inverseJoinColumns = @JoinColumn(name = "roleid"))
    private List<RoleEntity> roles = new ArrayList<>();

}
