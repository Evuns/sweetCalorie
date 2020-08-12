package sweetCalorie.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import sweetCalorie.model.entity.User;
import sweetCalorie.model.service.UserServiceModel;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    UserServiceModel findByEmail(String email);

    List<UserServiceModel> findAllUsers();

    void setUserRole(String id, String role);

    void deleteUser(String id);
}