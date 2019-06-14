package warmer.star.blog.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HttpClientUtil {
    /**
     * 使用HttpClient发送一个Get方式的请求
     * @param url 请求的路径 请求参数拼接到url后面
     * @return 响应的数据
     * @throws Exception
     */
    public static String doGet(String url)throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet); //发送一个http请求
        //如果响应成功,解析响应结果
        if(response.getStatusLine().getStatusCode()==200){
            HttpEntity responseEntity = response.getEntity(); //获取响应的内容
            return EntityUtils.toString(responseEntity);
        }
        return null;
    }

    // 参数的封装
    public static Map<String,String> parseResponseEntity(String responseEntityStr){
        Map<String,String> map = new HashMap<>();
        String[] strs = responseEntityStr.split("\\&");
        for (String str : strs) {
            String[] mapStrs = str.split("=");
            String value = null;
            String key = mapStrs[0];
            if(mapStrs.length>1){
                value = mapStrs[1];
            }
            map.put(key, value);
        }
        return map;
    }

    //json字符串转map
    public static Map<String,String> parseResponseEntityJSON(String responseEntityStr){
        Map<String,String> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(responseEntityStr); //解析json格式的字符串
        Set<Map.Entry<String, Object>> entries = jsonObject.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            map.put(key, value);
        }
        return map;
    }

}
