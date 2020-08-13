package sweetCalorie.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sweetCalorie.model.entity.Role;
import sweetCalorie.model.service.RoleServiceModel;
import sweetCalorie.repository.RoleRepository;
import sweetCalorie.service.RoleService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> findAll() {
        return new HashSet<>(this.roleRepository.findAll());
    }

    @Override
    public Role findByAuthority(String authority) {
        return this.roleRepository.findByAuthority(authority);
    }

    @Override
    public void seedRolesInDB() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setAuthority("ROLE_ADMIN");
            Role moderator = new Role();
            moderator.setAuthority("ROLE_MODERATOR");
            Role user = new Role();
            user.setAuthority("ROLE_USER");

            this.roleRepository.saveAndFlush(admin);
            this.roleRepository.saveAndFlush(moderator);
            this.roleRepository.saveAndFlush(user);
        }
    }
}
