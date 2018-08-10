package warmer.star.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import warmer.star.blog.util.GeetestLib;

@Configuration
public class GeetestConfig {
	@Value("${geetest.geetest_id}")
	private String geetest_id;
	@Value("${geetest.geetest_key}")
	private String geetest_key;
	@Value("${geetest.newfailback}")
	private boolean newfailback;

	public String getGeetest_id() {
		return geetest_id;
	}

	public String getGeetest_key() {
		return geetest_key;
	}

	public boolean isnewfailback() {
		return newfailback;
	}
	//初始化
	public GeetestLib initGeetest() {
		GeetestLib gtSdk = new GeetestLib(this.geetest_id, this.geetest_key,this.newfailback);
		return gtSdk;
	}
}
