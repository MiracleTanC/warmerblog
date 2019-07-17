package warmer.star.blog.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import warmer.star.blog.model.Comment;
import warmer.star.blog.service.CommentService;
import warmer.star.blog.util.IpUtil;
import warmer.star.blog.util.R;
import warmer.star.blog.util.RandomNameUtil;
import warmer.star.blog.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("/getCommentlist")
    @ResponseBody
    public R getCommentlist(String uuid) {
        List<Comment> commentList = commentService.getCommentList(uuid);
        List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
        for (Comment item : commentList) {
            HashMap<String, Object> res = new HashMap<String, Object>();
            res.put("id", item.getId().toString());
            res.put("useruuid", item.getUseruuid());
            res.put("touseruuid", item.getTouseruuid());
            res.put("content", item.getContent());
            res.put("usernickname", item.getUsernickname());
            res.put("tousernickname", item.getTousernickname());
            res.put("useravatar", "");
            res.put("touseravatar", "");
            maps.add(res);
        }
        return R.success().put("data", maps);
    }
    @RequestMapping("/addComment")
    @ResponseBody
    public R addComment(HttpServletRequest request, Comment submitItem) {
       try{
           submitItem.setUserip(IpUtil.getIpAddr(request));
           if(StringUtil.isBlank(submitItem.getUseruuid())){
               String name=RandomNameUtil.getRandomName();
               Date date=new Date();
               long uuid=date.getTime();
               submitItem.setUsernickname(name);
               submitItem.setUseruuid(String.valueOf(uuid));
           }
           commentService.saveComment(submitItem);
           int commentid = submitItem.getId();
           if (commentid > 0) {
               return R.success().put("msg", "操作成功");
           }
       }
       catch (Exception ex){
           return R.error().put("msg",ex.getMessage());
       }
        return R.error().put("msg","服务器错误");
    }
    @RequestMapping("/deleteComment")
    @ResponseBody
    public R deleteComment(Integer commentId) {
       try {
           commentService.deleteComment(commentId);
           return R.success().put("msg", "操作成功");
       }
       catch (Exception ex){
           return R.error().put("msg", ex.getMessage());
       }
    }
}
