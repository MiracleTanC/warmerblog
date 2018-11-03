package warmer.star.blog;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
@WebAppConfiguration
public class TestElasticSearch {
	/*private static String index="tanchao_blog";
	private static String type="external";
	//@Test
	public void createIndex() {
		boolean result=ElasticsearchUtils.createIndex(index);
        System.out.println(result);
	}
	//@Test
	public void addRecord() {
		JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", UuidUtil.getUUID());
        jsonObject.put("age", 28);
        jsonObject.put("name", "Tan" + new Random(100).nextInt());
        jsonObject.put("date", new Date());
        String id = ElasticsearchUtils.addData(jsonObject, index, type, jsonObject.getString("id"));
        System.out.println(id);
	}
	//@Test
	public void selectbyId() {
		String id="0679bf4016ef4481888f0f2c3756435a";
        Map<String, Object> map= ElasticsearchUtils.searchDataById(index,type,id,null);
        String res= JSONObject.toJSONString(map);
        System.out.println(res);
	}
	//@Test
	public void pageRecord() {
        EsPageRecord record=  ElasticsearchUtils.searchDataPage(index, type, 1,10, 0, 0, null, "", false, new ArrayList<String>(), new ArrayList<HashMap<String,String>>());
        System.out.println(FastJsonUtil.parseToJSON(record));
	}
	@Test
	public void deleteRecord() {
		String id="Fri, 20 Jul 2018 07:44:21 GMT";
        ElasticsearchUtils.deleteDataById(index, type, id);;
	}*/
}
