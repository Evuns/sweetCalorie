package sweetCalorie.service;

import sweetCalorie.model.service.CommentServiceModel;

import java.util.List;

public interface CommentService {

    void createComment(CommentServiceModel commentServiceModel);

    CommentServiceModel findById(String id);

    List<CommentServiceModel> findAllByAuthor(String userName);

    void deleteComment(String id);

    CommentServiceModel editComment(String id, CommentServiceModel commentServiceModel);
}
