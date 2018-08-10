package warmer.star.blog.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

public class GmessageLib {

    protected final String verName = "1.0.0";// SDK版本编号

    protected final String sdkLang = "java";// SD的语言类型

    protected final String apiUrl = "http://onepass.geetest.com"; //Onepass验证API URL

    protected final String baseUrl = "onepass.geetest.com";

    protected final String checkGatewayUrl = "/check_gateway.php"; //check gateway url

    protected final String checkMessageUrl = "/check_message.php"; //check message url
    /**
     * 公钥
     */
    private String customid = "";

    /**
     * 私钥
     */

    private String privateKey = "";

    private String userId = "";

    private String responseStr = "";

    /**
     * 调试开关，是否输出调试日志
     */
    public boolean debugCode = true;

    /**
     * 带参数构造函数
     * 
     * @param CustomId
     * @param privateKey
     */
    public GmessageLib(String CustomId, String privateKey) {
        this.customid = CustomId;
        this.privateKey = privateKey;
    }

    /**
     * @return 初始化结果
     */
    public String getResponseStr() {
        return responseStr;
    }

    public String getVersionInfo() {
        return verName;
    }

    /**
     * 判断一个表单对象值是否为空
     * 
     * @param gtObj
     * @return
     */
    protected boolean objIsEmpty(Object gtObj) {
        if (gtObj == null) {
            return true;
        }

        if (gtObj.toString().trim().length() == 0) {
            return true;
        }

        return false;
    }

    /**
     * 检查客户端的请求是否合法,三个只要有一个为空，则判断不合法
     * 
     * @return
     */
    private boolean resquestIsLegal(String phone, String process_id,
        String accesscode) {

        if (objIsEmpty(phone)) {
            return false;
        }

        if (objIsEmpty(process_id)) {
            return false;
        }

        if (objIsEmpty(accesscode)) {
            return false;
        }

        return true;
    }

    /**
     * gateway的验证接口，向onepass服务器进行验证，获取验证结果
     * 
     * @param phone
     * @param process_id
     * @param accesscode
     * @return 验证结果,1表示验证成功0表示验证失败
     */
    public int checkGateway(String phone, String process_id,
        String accesscode) {

        if (!resquestIsLegal(phone, process_id, accesscode)) {
            return 0;
        }
        gtlog("request legitimate");

        String host = apiUrl;
        String path = checkGatewayUrl;
        String Url = host + path;
        String query = String.format(
            "accesscode=%s&custom=%s&process_id=%s&phone=%s&sdk=%s", accesscode,
            customid, process_id, phone, (this.sdkLang + "_" + this.verName));
        String response = "";

        if (this.userId != "") {
            query = query + "&user_id=" + this.userId;
            this.userId = "";
        }
        gtlog(query);
        try {
            gtlog("checkGatewayResultByPrivate");
            response = readContentFromPost(Url, query);
            gtlog("response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject return_map = new JSONObject(response);
            int result = return_map.getInt("result");
            String recheck_custom_id = return_map.getString("content");
            if (result == 0) {
                if (checkResultByPrivate(process_id, recheck_custom_id)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {

                return 0;

            }

        } catch (JSONException e) {
            e.getStackTrace();
            System.out.println(e);
            gtlog("json load error");
            return 0;
        }

    }

    public int checkGateway(String phone, String process_id,
            String accesscode, boolean testbutton){
        if (!resquestIsLegal(phone, process_id, accesscode)) {
            return 0;
        }
        gtlog("request legitimate");
        
        Long ts = System.currentTimeMillis();
        gtlog(String.valueOf(ts));
        String ts_str = String.valueOf(ts);
        String sign_data = this.customid + "&&" + this.md5Encode(this.privateKey) + "&&" + ts_str;
        String sign = "";
        try{
            sign = RSA.encryptByPublicKey(sign_data);
            }
        catch (Exception e) {
			e.printStackTrace();
			gtlog(e.getMessage());
		}
		String host = apiUrl;
        String path = checkGatewayUrl;
        String Url = host + path;
        String query = String.format(
            "accesscode=%s&custom=%s&process_id=%s&phone=%s&sdk=%s&sign=%s", accesscode,
            customid, process_id, phone, (this.sdkLang + "_" + this.verName),sign);
        String response = "";

        if (this.userId != "") {
            query = query + "&user_id=" + this.userId;
            this.userId = "";
        }
        gtlog(query);
        try {
            gtlog("checkGatewayWithoutTestButtonResultByPrivate");
            response = readContentFromPost(Url, query);
            gtlog("response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject return_map = new JSONObject(response);
            int result = return_map.getInt("result");
            String recheck_custom_id = return_map.getString("content");
            if (result == 0) {
                if (checkResultByPrivate(process_id, recheck_custom_id)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {

                return 0;

            }

        } catch (JSONException e) {
            e.getStackTrace();
            System.out.println(e);
            gtlog("json load error");
            return 0;
        }
    	
    }
    /**
     * message的验证接口，向onepass服务器进行验证，获取验证结果
     * 
     * @param phone
     * @param process_id
     * @param message_number
     * @param message_id
     * @return
     */
    public int checkMessage(String phone, String process_id,
        String message_number, String message_id) {

        if (!resquestIsLegal(phone, process_id, message_id)) {
            return 0;
        }
        gtlog("request legitimate");

        String host = apiUrl;
        String path = checkMessageUrl;
        String Url = host + path;
        String query = String.format(
            "message_number=%s&message_id=%s&custom=%s&process_id=%s&phone=%s&sdk=%s",
            message_number, message_id, customid, process_id, phone,
            (this.sdkLang + "_" + this.verName));
        String response = "";

        if (this.userId != "") {
            query = query + "&user_id=" + this.userId;
            this.userId = "";
        }
        gtlog(query);
        try {
            gtlog("checkMessageResultByPrivate");
            response = readContentFromPost(Url, query);
            gtlog("response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            JSONObject return_map = new JSONObject(response);
            String result = return_map.getString("result");
            String recheck_custom_id = return_map.getString("content");
            if (result.equals("0")) {
                if (checkResultByPrivate(process_id, recheck_custom_id)) {
                    return 1;
                } else {
                    return 0;
                }
            } else {

                return 0;

            }

        } catch (JSONException e) {

            gtlog("json load error");
            return 0;

        }
    }

    /**
     * 输出debug信息，需要开启debugCode
     * 
     * @param message
     */
    public void gtlog(String message) {
        if (debugCode) {
            System.out.println("gtlog: " + message);
        }
    }

    protected boolean checkResultByPrivate(String process_id,
        String recheck_custom_id) {
        String encodeStr = md5Encode(privateKey + "gtmessage" + process_id);
        return recheck_custom_id.equals(encodeStr);
    }

    /**
     * Post方式
     * 
     * @param URL
     * @param data
     * @return
     * @throws IOException
     */
    private String readContentFromPost(String URL, String data)
        throws IOException {

        gtlog(data);
        URL postUrl = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) postUrl
            .openConnection();

        connection.setConnectTimeout(2000);// 设置连接主机超时（单位：毫秒）
        connection.setReadTimeout(2000);// 设置从主机读取数据超时（单位：毫秒）
        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type",
            "application/x-www-form-urlencoded");

        // 建立与服务器的连接，并未发送数据
        connection.connect();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
            connection.getOutputStream(), "utf-8");
        outputStreamWriter.write(data);
        outputStreamWriter.flush();
        outputStreamWriter.close();

        if (connection.getResponseCode() == 200) {
            // 发送数据到服务器并使用Reader读取返回的数据
            StringBuffer sBuffer = new StringBuffer();

            InputStream inStream = null;
            byte[] buf = new byte[1024];
            inStream = connection.getInputStream();
            for (int n; (n = inStream.read(buf)) != -1;) {
                sBuffer.append(new String(buf, 0, n, "UTF-8"));
            }
            inStream.close();
            connection.disconnect();// 断开连接

            return sBuffer.toString();
        } else {

            return "fail";
        }
    }

    /**
     * md5 加密
     * 
     * @param plainText
     * @return
     */
    private static String md5Encode(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }
       public static void main(String[] args){    
   	//测试字符串    
   	   String res = md5Encode("25c6fcdc384a8a99234240a8e30c76dcgtmessageab8879bc39fdc6c126db73de28023eaa");
   	   System.out.println(res);
       String encryptStr= "fd2cf5e6589a7ceccbc1cc57f6b299a4&&a43777b86615e6e3903b60c9c21f275f&&1512008420";  
       String test = md5Encode(encryptStr);
       try {    
           System.out.println("明文："+encryptStr);
           GmessageLib gm = new GmessageLib("fd2cf5e6589a7ceccbc1cc57f6b299a4","a8ee5dddc2122f874d47785971d54726");
           int result = gm.checkGateway("18571472767","a8ee5dddc2122f874d47785971d54726","sadaszxvasddasdascxzcdsasadxcasdqwe",false);
           
            String cipherStr = RSA.encryptByPublicKey(encryptStr);
            System.out.println("公钥加密密文："+cipherStr);
        } catch (Exception e) {    
            System.err.println(e.getMessage());    
        }   

        }
}
