package sweetCalorie.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sweetCalorie.constant.GlobalConstants;
import sweetCalorie.model.entity.Role;
import sweetCalorie.model.entity.User;
import sweetCalorie.model.entity.UserProfile;
import sweetCalorie.model.service.UserServiceModel;
import sweetCalorie.repository.RoleRepository;
import sweetCalorie.repository.UserProfileRepository;
import sweetCalorie.repository.UserRepository;
import sweetCalorie.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserProfileRepository userProfileRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository, UserProfileRepository userProfileRepository,
                           ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userProfileRepository = userProfileRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {
        this.seedRolesInDB();

        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);

        this.arrangeRoles(user);
        try {
            UserProfile userProfile = new UserProfile();
            userProfile.setUser(user);
            this.userProfileRepository.saveAndFlush(userProfile);
            this.modelMapper.map(this.userRepository.saveAndFlush(user), UserServiceModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(userName).orElseThrow(
                () -> new UsernameNotFoundException(GlobalConstants.USERNAME_NOT_FOUND));
    }

    private void seedRolesInDB() {
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

    private void arrangeRoles(User user) {
        if (this.userRepository.count() == 0) {
            this.roleRepository.findAll().forEach(role ->
                    user.getAuthorities().add(role));
        } else {
            user.getAuthorities().add(this.roleRepository.findByAuthority("ROLE_USER"));
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
}