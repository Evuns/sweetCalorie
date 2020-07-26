package sweetCalorie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sweetCalorie.model.entity.Food;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository <Food, String>{
    Food findByName(String name);
    List<Food> findAll();
}
