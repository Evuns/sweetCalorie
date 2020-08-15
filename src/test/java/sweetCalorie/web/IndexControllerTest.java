package sweetCalorie.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
@SpringBootTest
class IndexControllerTest {

    @Test
    public void indexControllerShouldReturnCorrectView() throws Exception {
//     //   super.mockMvc.perform(get("/"))
//                .andExpect(view().name("index"));
    }
}