package sweetCalorie.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.entity.User;
import sweetCalorie.model.entity.UserProfile;
import sweetCalorie.model.service.UserServiceModel;
import sweetCalorie.repository.UserProfileRepository;
import sweetCalorie.repository.UserRepository;
import sweetCalorie.service.RoleService;
import sweetCalorie.service.UserService;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository,
                           UserProfileRepository userProfileRepository,
                           ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        this.roleService.seedRolesInDB();
        this.arrangeRoles(userServiceModel);

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        arrangeUserStatus(user);

        try {
            this.userRepository.saveAndFlush(user);
            UserProfile userProfile = new UserProfile();
            userProfile.setUser(user);
            this.userProfileRepository.saveAndFlush(userProfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(userName).orElseThrow(
                () -> new UsernameNotFoundException(GlobalConstants.USERNAME_NOT_FOUND));
    }

    private void arrangeRoles(UserServiceModel userServiceModel) {
        if (this.userRepository.count() == 0) {
            this.roleService.findAll().forEach(role ->
                    userServiceModel.getAuthorities().add(role));
        } else {
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
        }
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository.findByUsername(username).map(
                user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserServiceModel findByEmail(String email) {
        return this.userRepository.findByEmail(email).map(
                user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        List<UserServiceModel> allUsers = new LinkedList<>();
        this.userRepository.findAll().forEach(user -> {
            UserServiceModel userServiceModel = modelMapper.map(user, UserServiceModel.class);
            allUsers.add(userServiceModel);
        });
        return allUsers;
    }

    @Override
    public void setUserRole(String id, String role) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect id!"));

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        userServiceModel.getAuthorities().clear();

        switch (role) {
            case "user":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                break;
            case "moderator":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                break;
        }

        User userChangeAuthority = this.modelMapper.map(userServiceModel, User.class);
        arrangeUserStatus(userChangeAuthority);
        this.userRepository.saveAndFlush(userChangeAuthority);
    }

    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findById(id).orElse(null);
        UserProfile userProfile = this.userProfileRepository.findByUserId(id);

        user.getAuthorities().remove(user);
        this.userProfileRepository.delete(userProfile);
        this.userRepository.delete(user);
    }

    private void arrangeUserStatus(User user) {
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
    }
}