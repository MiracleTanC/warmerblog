package warmer.star.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import warmer.star.blog.service.KnowledgegraphService;
import warmer.star.blog.util.Neo4jUtil;
import warmer.star.blog.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BlogApplication.class)
@WebAppConfiguration
public class TestSyncDomainNodes {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Neo4jUtil neo4jUtil;
	@Autowired
	private KnowledgegraphService kgservice;
	@Test
	public void dosync(){
		logger.info("开始同步信息:同步节点和关系数量");
		try {
			String domaincql = "call db.labels";
			List<HashMap<String, Object>> domainList = neo4jUtil.GetEntityList(domaincql);
			for (HashMap<String, Object> domainItem : domainList) {
				Object domain = domainItem.get("label");
				// 更新节点数量和节点关系
				if (domain != null && !StringUtil.isBlank(domain.toString())) {
					String domainName = domain.toString().replace("\"", "");
					List<Map<String, Object>> domainListMysql = kgservice.getDomainByName(domainName);
					// 查询领域的关系数
					Map<String, Object> maps = new HashMap<String, Object>();
					String nodecountcql = String.format("match (n:`%s`) return count(n)", domainName);
					Integer nodecount = neo4jUtil.executeScalar(nodecountcql);
					String shipcountcql = String.format("match (n:`%s`)-[r]-() return count(r)", domainName);
					Integer shipcount = neo4jUtil.executeScalar(shipcountcql);
					maps.put("name", domainName);
					maps.put("nodecount", nodecount);
					maps.put("shipcount", shipcount);

					if(shipcount<=0){
						String deleteDomaincql = String.format("match (n:`%s`) delete n", domainName);
						neo4jUtil.excuteCypherSql(deleteDomaincql);
						String removeDomaincql = String.format("MATCH (n:`%s`) remove n:`%s`", domainName,domainName);
						neo4jUtil.excuteCypherSql(removeDomaincql);
						if(domainListMysql.size() > 0){
							Integer domainId=Integer.parseInt(domainListMysql.get(0).get("id").toString());
							kgservice.deleteDomain(domainId);
						}

					}else {
						if (domainListMysql.size() > 0) {
							maps.put("id", domainListMysql.get(0).get("id"));
							kgservice.updateDomain(maps);// 更新到mysql
						}else {
							maps.put("id", 0);
							maps.put("status", 0);
							kgservice.saveDomain(maps);// 保存到mysql
						}
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("结束同步信息:同步节点和关系数量");
	}

}
