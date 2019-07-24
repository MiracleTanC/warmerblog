package warmer.star.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import warmer.star.blog.model.ArticleDetail;
import warmer.star.blog.service.ArticleService;
import warmer.star.blog.util.AutoAbstract.TextRank;
import warmer.star.blog.util.StringUtil;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAutoAbstract {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ArticleService articleService;
    @Test
    public void test(){
        TextRank textRank=new TextRank();
        String content="新华社北京7月23日电（记者魏玉坤）交通运输部新闻发言人、政策研究室副主任孙文剑在23日召开的例行新闻发布会上表示，取消高速公路省界收费站工作已取得阶段性成效。\n" +
                "\n" +
                "孙文剑介绍，当前，交通运输部正在积极有序推进取消高速公路省界收费站各项工作。印发了取消高速公路省界收费站总体技术方案、工程建设方案等一系列指导性文件，组织开展了技术培训。同时，全面启动工程建设改造，截至7月18日，已有25个省份启动ETC门架系统建设改造工程，累计建成ETC门架系统476套。\n" +
                "\n" +
                "ETC电子不停车快捷收费系统的推广应用全覆盖是能否如期实现取消高速公路省界收费站目标的关键因素，孙文剑表示，当前，正多措并举，大力推进ETC发行。截至7月18日，全国ETC用户总量达9151万，较去年底净增1495万，增幅达19.5%，日均ETC发行量约42万，是去年日均发行量的7倍。\n" +
                "\n" +
                "孙文剑表示，下一步，将狠抓工程建设，建立工程建设周报机制，确保10月底前高速公路ETC具备联调联试条件，同时，指导各地积极拓宽ETC发行渠道。此外，持续推动政策完善，加快完善印发优化鲜活农产品运输“绿色通道”政策、清理规范地方性通行费减免政策、调整货车计费方式等相关通知，全力推进取消高速公路省界收费站工作。";
       String abstarct= textRank.Summarize(content);
       System.out.println(abstarct);
    }
    @Test
    public void AutoAbstract() {
        logger.info("====开始自动摘要====");
        try {
            TextRank textRank=new TextRank();
            List<Integer> articleIds= articleService.getNoAbstractArticleId();
            if(articleIds!=null&&articleIds.size()>0){
                for (Integer id:articleIds){
                    ArticleDetail detail=articleService.getContentById(id);
                    if(detail!=null&&StringUtil.isNotBlank(detail.getContent())){
                        String noHtmlContent=StringUtil.delHTMLTag(detail.getContent());
                        String abstractContent=textRank.Summarize(noHtmlContent);
                        if(StringUtil.isNotBlank(abstractContent)){
                            articleService.updateAbstractById(id,abstractContent);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("====结束自动摘要====");
    }
}
