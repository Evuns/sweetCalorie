package sweetCalorie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sweetCalorie.model.entity.Comment;
import sweetCalorie.model.entity.User;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findAllByAuthor(User user);

    Comment findCommentById(String id);

    void deleteById(String id);

}
