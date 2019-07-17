package warmer.star.blog.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import warmer.star.blog.model.Comment;

import java.util.List;

@Repository
public interface CommentMapper {
    List<Comment> getCommentList(@Param("uuid")String uuid);
    int saveComment(Comment submitItem);
    boolean updateComment(Comment submitItem);
    boolean deleteComment(@Param("commentId") int commentId);

}
