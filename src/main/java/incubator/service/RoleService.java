package incubator.service;

import incubator.model.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    public Role createRole(String nameRole){
        Role role = new Role(0,0,0);

        if (nameRole.contains("admin") || nameRole.contains("ADMIN")) {
            role.setAdmin(1);
            role.setRoleId(3);
        } else if (nameRole.contains("user") || nameRole.contains("USER")) {
            role.setRoleId(1);
            role.setUser(1);
        } else {
            role.setRoleId(2);
            role.setTutor(1);
        }

        return role;
    }
}
