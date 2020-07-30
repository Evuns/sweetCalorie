package sweetCalorie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sweetCalorie.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByAuthority(String authority);

}
