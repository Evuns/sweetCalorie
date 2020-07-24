package sweetCalorie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sweetCalorie.model.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {

    Recipe findByTitle(String name);
}
