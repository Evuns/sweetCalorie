package sweetCalorie.service;

import java.io.IOException;

public interface FoodService {

    boolean areImported();

    String readFoodFileContent() throws IOException;

    void importFoods() throws IOException;

}
