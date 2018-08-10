/**    
* @Title: UuidUtil.java  
* @Package warmer.star.blog.util  
* @Description: TODO(用一句话描述该文件做什么)  
* @author tc    
* @date 2018年6月1日 下午2:34:49  
* @version V1.0    
*/
package warmer.star.blog.util;

import java.util.UUID;

public  class UuidUtil {
	public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
      }
}
