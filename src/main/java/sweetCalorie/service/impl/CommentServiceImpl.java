package sweetCalorie.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sweetCalorie.model.entity.Comment;
import sweetCalorie.model.entity.User;
import sweetCalorie.model.service.CommentServiceModel;
import sweetCalorie.model.service.RecipeServiceModel;
import sweetCalorie.repository.CommentRepository;
import sweetCalorie.service.CommentService;
import sweetCalorie.service.UserProfileService;
import sweetCalorie.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final UserProfileService userProfileService;

    public CommentServiceImpl(ModelMapper modelMapper, CommentRepository commentRepository,
                              UserService userService, UserProfileService userProfileService) {
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    @Override
    public void createComment(CommentServiceModel commentServiceModel) {
        Comment comment = this.modelMapper.map(commentServiceModel, Comment.class);
        this.commentRepository.saveAndFlush(comment);
        //this.modelMapper.map(comment, CommentServiceModel.class);
    }

    @Override
    public CommentServiceModel findById(String id) {
        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        RecipeServiceModel recipeServiceModel = modelMapper.map(comment.getRecipe(), RecipeServiceModel.class);
        CommentServiceModel commentServiceModel = this.modelMapper.map(comment, CommentServiceModel.class);
        commentServiceModel.setRecipeServiceModel(recipeServiceModel);
        return commentServiceModel;
    }


    @Override
    public List<CommentServiceModel> findAllByAuthor(String username) {
        User user = this.modelMapper.map(this.userService.findByUsername(username), User.class);
        List<Comment> comment = this.commentRepository.findAllByAuthor(user);
        return comment
                .stream()
                .map(c -> this.modelMapper.map(c, CommentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(String id) {
        this.commentRepository.deleteById(id);
    }

//    @Override
//    public CommentServiceModel editComment(String id, CommentServiceModel commentServiceModel) {
//        Comment comment = this.commentRepository.findById(id)
//                .orElseThrow(IllegalArgumentException::new);
//        if (!commentServiceModel.getText().isEmpty()) {
//            comment.setText(commentServiceModel.getText());
//        }
//        return this.modelMapper.map(this.commentRepository.saveAndFlush(comment),
//                CommentServiceModel.class);
//    }
}
