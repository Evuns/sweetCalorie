package sweetCalorie.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sweetCalorie.model.entity.Role;
import sweetCalorie.repository.RoleRepository;
import sweetCalorie.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleServiceImplTest{

    @MockBean
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Test
    void findAllRoles_whenThereAreRoles_shouldReturnThem() {
        Role user = new Role("ROLE_ADMINISTRATOR");
        Role admin = new Role("ROLE_MODERATOR");
        Role root = new Role("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(user);
        roles.add(admin);
        roles.add(root);

        Mockito.when(roleRepository.findAll()).thenReturn(roles);

        Set<Role> actualRoles = roleService.findAll();

        assertEquals(roles.size(), actualRoles.size());
        assertEquals(Role.class,
                actualRoles.toArray()[0].getClass());
    }


    @Test
    void findByAuthority_whenThereIsInsertedRole() {
        Role admin = new Role("ROLE_ADMINISTRATOR");
        Mockito.when(roleRepository.findByAuthority("ROLE_ADMINISTRATOR"))
                .thenReturn(admin);
        Role actualRole = roleService.findByAuthority("ROLE_ADMINISTRATOR");
        assertEquals(admin,actualRole);
    }

}