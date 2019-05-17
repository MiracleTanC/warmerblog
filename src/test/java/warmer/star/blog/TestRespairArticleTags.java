package warmer.star.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import warmer.star.blog.dto.ArticleItem;
import warmer.star.blog.dto.ArticleQueryItem;
import warmer.star.blog.model.Tag;
import warmer.star.blog.service.ArticleService;
import warmer.star.blog.service.TagService;
import warmer.star.blog.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
@WebAppConfiguration
public class TestRespairArticleTags {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    @Test
    public void Do() {
        ArticleQueryItem queryItem = new ArticleQueryItem();
        queryItem.setPageSize(1000);
        List<ArticleItem> articleItemList = articleService.getArticleList(queryItem);
        if (articleItemList != null && articleItemList.size() > 0) {
            for (ArticleItem item : articleItemList) {
                if (StringUtil.isNotBlank(item.getArticleTags())) {
                    String[] tagIds = item.getArticleTags().split(",");
                    List<String> tidList=new ArrayList<>();
                    for (String tname : tagIds) {
                        List<Tag> tagItem = tagService.getTagByName(tname);
                        if (tagItem != null&&tagItem.size()>0) {
                            Integer tid=tagItem.get(0).getId();
                            System.out.println(tname+":"+tid);
                            tidList.add(tid.toString());
                        }
                    }
                    String articleTagIds=String.join(",",tidList);
                    if(StringUtil.isNotBlank(articleTagIds)){
                        articleService.updateArticleTags(item.getId(),articleTagIds);
                    }
                }

            }
        }

    }

}
