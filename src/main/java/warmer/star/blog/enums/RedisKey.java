/**    
* @Title: ArticleStatus.java  
* @Package warmer.miao.blog.enums  
* @Description: TODO(用一句话描述该文件做什么)  
* @author tc    
* @date 2018年5月25日 上午9:44:39  
* @version V1.0    
*/
package warmer.star.blog.enums;

/**  
* @ClassName: ArticleStatus  
* @Description: TODO(这里用一句话描述这个类的作用)  
* @author tc  
* @date 2018年5月25日 上午9:44:39  
*    
*/
public  class RedisKey {
	/*线上Redis禁止使用Keys正则匹配操作，容易引起缓存雪崩，最终数据库宕机,如
	Set<String> keySet = redisUtil.keys("posts_views::posts_views_id_*");
	所以此处用KEY标识浏览数和点赞数的键，然后存一个集合CODE表示文章ID,点赞数，也就是在外边包一层，里面取集合*/
	/** 浏览数量 的 key**/
	public static final String ARTICLE_VIEWCOUNT_KEY = "article_view";

	/**点赞数量 key**/
	public static final String ARTICLE_APPROVECOUNT_KEY = "article_approve";
	/** 浏览数量每篇 的 key**/
	public static final String ARTICLE_VIEWCOUNT_CODE = "viewcount_";

	/**点赞数每篇的 key**/
	public static final String ARTICLE_APPROVECOUNT_CODE = "approvecount_";


}
