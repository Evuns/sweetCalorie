package sweetCalorie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sweetCalorie.model.entity.User;
import sweetCalorie.model.entity.UserProfile;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    Optional<UserProfile> findByUser(User user);

    Optional<UserProfile> findById(String id);
}
