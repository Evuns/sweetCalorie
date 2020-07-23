package sweetCalorie.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sweetCalorie.service.FoodService;

@Component
public class DataInitializer implements CommandLineRunner {

    private FoodService foodService;

    @Override
    public void run(String... args) throws Exception {
        if(!foodService.areImported()) {
           foodService.importFoods();
        }

    }
}
