package sweetCalorie.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import sweetCalorie.model.entity.Comment;
import sweetCalorie.model.entity.Recipe;
import sweetCalorie.model.entity.User;
import sweetCalorie.model.service.CommentServiceModel;
import sweetCalorie.model.service.RecipeServiceModel;
import sweetCalorie.model.service.UserServiceModel;
import sweetCalorie.repository.CommentRepository;
import sweetCalorie.service.CommentService;
import sweetCalorie.service.RecipeService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceImplTest {


    @MockBean
    CommentService commentService;

    @MockBean
    RecipeService recipeService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CommentRepository commentRepository;

    private Comment comment;
    private CommentServiceModel commentServiceModel;
    private User user;
    private Recipe recipe;
    private RecipeServiceModel recipeServiceModel;

    @BeforeEach
    public void init() {
        comment = new Comment();
        recipe = new Recipe();
        recipe.setId("2");
        user = new User("admin", "admin123", "sdhadh@djkf");
        user.setId("0");
        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);

        comment.setId("1");
        comment.setAuthor(user);
        comment.setRecipe(recipe);

        commentServiceModel = new CommentServiceModel();
        commentServiceModel.setId("1");
        recipeServiceModel = new RecipeServiceModel();
        recipeServiceModel.setId("2");
        commentServiceModel.setRecipeServiceModel(recipeServiceModel);
        commentServiceModel.setAuthor(modelMapper.map(user, UserServiceModel.class));



    }


//    @Test
//    void findById_shouldReturnComment() {
//        Mockito.when(commentRepository.findById("1")).thenReturn(Optional.of(comment));
//        Mockito.when(modelMapper.map(comment, CommentServiceModel.class))
//                .thenReturn(commentServiceModel);
//        CommentServiceModel actual=commentService.findById("1");
//        assertEquals(commentServiceModel,actual);
//    }


    @Test
    void findAllByAuthor_whenNoComment_shouldReturnEmptyList() {
        List<CommentServiceModel> commentServiceModels = commentService.findAllByAuthor("Gosho");
        assertEquals(0, commentServiceModels.size());
    }

    @Test
    void deleteComment_shouldDeleteComment() {
        Mockito.when(commentRepository.findById("1")).thenReturn(Optional.of(comment));
        commentRepository.deleteById(comment.getId());
        assertThat(commentRepository.count()).isEqualTo(0);
    }
}

