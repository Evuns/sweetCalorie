package sweetCalorie.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sweetCalorie.model.service.UserServiceModel;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    UserServiceModel findByEmail(String email);
}