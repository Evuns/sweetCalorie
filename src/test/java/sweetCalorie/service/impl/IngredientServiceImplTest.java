package sweetCalorie.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import sweetCalorie.model.entity.Ingredient;
import sweetCalorie.repository.IngredientRepository;
import sweetCalorie.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IngredientServiceImplTest {
    @InjectMocks
    private IngredientServiceImpl ingredientService;

    @Mock
    private IngredientRepository ingredientRepository;

    @Test
    public void deleteByIdShouldDeleteIngredient() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId("1");
        Mockito.when(ingredientRepository.findById("1")).thenReturn(Optional.of(ingredient));
        ingredientService.deleteById("1");
        Mockito.verify(ingredientRepository).deleteById("1");
    }
}
