package warmer.star.blog.service;

import warmer.star.blog.model.Comment;

import java.util.List;


public interface CommentService {
    List<Comment> getCommentList(String uuid);
    int saveComment(Comment submitItem);
    boolean updateComment(Comment submitItem);
    boolean deleteComment(int CommentId);

}
