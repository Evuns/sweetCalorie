package sweetCalorie.service;

import sweetCalorie.model.service.UserServiceModel;

import java.util.Optional;

public interface UserService {
    UserServiceModel findByUsername(String username);

    UserServiceModel findByEmail(String email);

    UserServiceModel register(UserServiceModel userServiceModel);
}
