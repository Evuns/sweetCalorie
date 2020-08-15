package sweetCalorie.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sweetCalorie.model.entity.User;
import sweetCalorie.model.entity.UserProfile;
import sweetCalorie.repository.UserProfileRepository;
import sweetCalorie.repository.UserRepository;
import sweetCalorie.web.UsersController;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserProfileServiceImplTest  {

    @MockBean
    UserRepository mockUserRepository;

    @MockBean
    UserProfileRepository mockUserProfileRepository;


    @Autowired
    UsersController controller;

    @Test
    void findByUser_shouldReturnUserId() {
        UserProfile userProfile = new UserProfile();
        userProfile.setId("0");

        User user = new User();
        user.setId("1");

        userProfile.setUser(user);
        this.mockUserProfileRepository.saveAndFlush(userProfile);

        assertEquals("1", userProfile.getUser().getId());
    }
}