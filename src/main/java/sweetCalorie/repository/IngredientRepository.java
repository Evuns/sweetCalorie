package sweetCalorie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sweetCalorie.model.entity.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
}
