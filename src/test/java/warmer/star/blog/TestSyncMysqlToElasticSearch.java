package warmer.star.blog;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
@WebAppConfiguration
public class TestSyncMysqlToElasticSearch {
	/*@Autowired
	private ArticleService articleService;
	private static String index = "warmer_blog";
	private static String type = "article";

	@Test
	public void sync() {
		deleteIndex();
		createIndex();
		addRecord();
		pageRecord();
	}

	public void createIndex() {
		boolean result = ElasticsearchUtils.createIndex(index);
		System.out.println(result);
	}
	public void deleteIndex() {
		boolean result = ElasticsearchUtils.deleteIndex(index);
		System.out.println(result);
	}
	public void addRecord() {
		ArticleQueryItem queryItem = new ArticleQueryItem();
		queryItem.setPageSize(100);
		List<ArticleItem> articleList = articleService.getArticleList(queryItem);
		for (ArticleItem articleItem : articleList) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", articleItem.getId());
			jsonObject.put("title", articleItem.getTitle());
			jsonObject.put("status", articleItem.getStatus());
			jsonObject.put("showStyle", articleItem.getShowStyle());
			jsonObject.put("coverImageList", articleItem.getCoverImageList());
			jsonObject.put("abstractContent", articleItem.getAbstractContent());
			jsonObject.put("author", articleItem.getAuthor());
			jsonObject.put("categoryName", articleItem.getCategory().getCategoryName());
			jsonObject.put("createTime", articleItem.getCreateTime());
			jsonObject.put("showCount", articleItem.getShowCount());
			jsonObject.put("supportCount", 0);
			String id = ElasticsearchUtils.addData(jsonObject, index, type, jsonObject.getString("id"));
			System.out.println(id);
		}

	}

	
	public void pageRecord() {
		String searchWords="tomcat安装";
		List<String> highLightFields=new ArrayList<String>();
		highLightFields.add("title");
		highLightFields.add("abstractContent");
		List<HashMap<String,String>> marchStrs=new ArrayList<HashMap<String,String>>();
		if(StringUtil.isNotEmpty(searchWords)) {
			HashMap<String,String> title=new HashMap<String,String>();
			title.put("condition", "title");
			title.put("value", searchWords);
			marchStrs.add(title);
			HashMap<String,String> abstractContent=new HashMap<String,String>();
			abstractContent.put("condition", "abstractContent");
			abstractContent.put("value", searchWords);
			marchStrs.add(abstractContent);
		}
		EsPageRecord record = ElasticsearchUtils.searchDataPage(index, type, 1, 100, 0, 0, null, "createTime", false, highLightFields, marchStrs);
		System.out.println(FastJsonUtil.parseToJSON(record));
	}*/
}
