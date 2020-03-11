package incubator.service;

import incubator.dao.UserRepository;
import incubator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleService roleService;

    private static String[] allRoles = {"ROLE_USER", "ROLE_TUTOR", "ROLE_ADMIN"};

    public static String[] getAllRoles() {
        return allRoles;
    }


    /**
     * Method saves in DB new user with current login and role (admin, user, tutor)
     * @param user
     * @param nameRole
     */

    @Transactional
    public void сreateUser(User user, String nameRole) {
        user.setRole(roleService.createRole(nameRole));
        String codedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(codedPassword);
        userRepository.create(user);
    }

}
