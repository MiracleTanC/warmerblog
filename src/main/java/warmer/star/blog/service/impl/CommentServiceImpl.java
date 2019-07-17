package warmer.star.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import warmer.star.blog.mapper.CommentMapper;
import warmer.star.blog.model.Comment;
import warmer.star.blog.service.CommentService;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> getCommentList(String uuid) {
        return commentMapper.getCommentList(uuid);
    }


    @Override
    public int saveComment(Comment submitItem) {
        return commentMapper.saveComment(submitItem);
    }

    @Override
    public boolean updateComment(Comment submitItem) {
        return commentMapper.updateComment(submitItem);
    }

    @Override
    public boolean deleteComment(int CommentId) {
        return commentMapper.deleteComment(CommentId);
    }
}
