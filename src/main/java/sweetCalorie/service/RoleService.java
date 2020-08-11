package sweetCalorie.service;


import sweetCalorie.model.entity.Role;
import sweetCalorie.model.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {
    void seedRolesInDB();

    Set<Role> findAll();

    Role findByAuthority(String authority);
}
