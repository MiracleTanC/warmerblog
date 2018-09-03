package warmer.star.blog.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;



public class PasswordEncoderFactories {
	public static PasswordEncoder createDelegatingPasswordEncoder() {
  	  String encodingId = "bcrypt";
  	  Map<String, PasswordEncoder> encoders = new HashMap();
  	  encoders.put(encodingId, new BCryptPasswordEncoder());
  	  encoders.put("ldap", new LdapShaPasswordEncoder());
  	  encoders.put("MD4", new Md4PasswordEncoder());
  	  encoders.put("MD5", new MessageDigestPasswordEncoder("MD5"));
  	  encoders.put("noop", NoOpPasswordEncoder.getInstance());
  	  encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
  	  encoders.put("scrypt", new SCryptPasswordEncoder());
  	  encoders.put("SHA-1", new MessageDigestPasswordEncoder("SHA-1"));
  	  encoders.put("SHA-256", new MessageDigestPasswordEncoder("SHA-256"));
  	  encoders.put("sha256", new StandardPasswordEncoder());
  	  return new DelegatingPasswordEncoder(encodingId, encoders);
  	 }
}
