package sweetCalorie.service.impl;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import sweetCalorie.model.entity.Role;
import sweetCalorie.model.entity.User;
import sweetCalorie.model.entity.UserProfile;
import sweetCalorie.model.service.UserServiceModel;
import sweetCalorie.repository.UserProfileRepository;
import sweetCalorie.repository.UserRepository;
import sweetCalorie.service.RoleService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private RoleService roleService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProfileRepository userProfileRepository;


    @Mock
    private ModelMapper modelMapper;

    private User user;
    private UserServiceModel userServiceModel;
    private Role admin;
    private Role moderator;
    private Role roleUser;

    @BeforeEach
    public void init() {
        admin = new Role("ROLE_ADMIN");
        moderator = new Role("ROLE_MODERATOR");
        roleUser = new Role("ROLE_USER");

        user = new User("admin","admin123","asd@asd");
        user.setId("1");
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);

        user.setAuthorities(new HashSet<>(List.of(moderator, roleUser)));

        userServiceModel = new UserServiceModel();
        userServiceModel.setId("1");
        userServiceModel.setUsername("admin");
        userServiceModel.setPassword("admin123");
        userServiceModel.setEmail("asd@asd");
        userServiceModel.setAuthorities(new HashSet<>(List.of(admin, moderator, roleUser)));
    }

    @Test
    void loadUserByUsername_shouldReturnUserServiceModel() {
        Mockito.when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        Mockito.when(modelMapper.map(user, UserServiceModel.class)).thenReturn(userServiceModel);
        UserServiceModel actual = userService.findByUsername("admin");
        assertEquals(userServiceModel, actual);
    }

    @Test
    public void loadUserByUsernameShouldThrowUserNotFoundException_InvalidUser() {
        Mockito.when(this.userRepository.findByUsername("user")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("user");
        });
    }

    @Test
    public void findByUsername_shouldReturnUser() {
        Mockito.when(userRepository.findByUsername("admin")).thenReturn(Optional.of(user));
        Mockito.when(modelMapper.map(user, UserServiceModel.class)).thenReturn(userServiceModel);
        UserServiceModel actual = userService.findByUsername("admin");
        assertEquals(user.getId(), actual.getId());
        assertEquals(user.getUsername(), actual.getUsername());
    }


    @Test
    void findByEmail_shouldReturnUser() {
        Mockito.when(userRepository.findByEmail("asd@asd")).thenReturn(Optional.of(user));
        Mockito.when(modelMapper.map(user, UserServiceModel.class)).thenReturn(userServiceModel);
        UserServiceModel actual = userService.findByEmail("asd@asd");
        assertEquals(user.getId(), actual.getId());
        assertEquals(user.getUsername(), actual.getUsername());
    }

    @Test
    void findAllUsers_whenThereIsNoImportedUser() {
        List<UserServiceModel> userServiceModels = userService.findAllUsers();
        assertEquals(0, userServiceModels.size());
    }

    @Test
    public void getAllUsersShouldReturnAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(user);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(modelMapper.map(userRepository.findAll(), UserServiceModel[].class))
                .thenReturn(new UserServiceModel[]{userServiceModel});

        List<UserServiceModel> actual = userService.findAllUsers();
        assertEquals(1, actual.size());
    }


    @Test
    void setUserRole_changeRoles() {
        Mockito.when(userRepository.findById("1"))
                .thenReturn(Optional.of(user));
        Mockito.when(roleService.findByAuthority("ROLE_USER")).thenReturn(roleUser);
        userService.setUserRole("1", "user");
        assertFalse(user.getAuthorities().contains(moderator));
        assertEquals(1, user.getAuthorities().size());

    }

//    @Test
//    public void deleteById_ShouldWork(){
//        this.userService.deleteUser("1");
//        Mockito.verify(userRepository).deleteById("1");
//    }
//    @Test
//    public void deleteByIdShouldDeleteUser() {
//        UserProfile userProfile = new UserProfile();
//        userProfile.setUser(user);
//        Mockito.when(userRepository.findById("1")).thenReturn(Optional.of(user));
//        Mockito.when(userProfileRepository.findByUser(user)).thenReturn(Optional.of(userProfile));
//        userService.deleteUser("1");
//        Mockito.verify(userRepository).deleteById("1");
//    }

}