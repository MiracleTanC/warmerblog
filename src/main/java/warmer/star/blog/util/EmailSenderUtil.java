package warmer.star.blog.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.github.pagehelper.util.StringUtil;
@Component
public class EmailSenderUtil {
	private static final Logger logger = LoggerFactory.getLogger(EmailSenderUtil.class);
	@Value("${spring.mail.username}")//发件人
	private String username;
	@Autowired
	JavaMailSender mailSender;

	/**
	 * 发送简单文本
	 * @param toEmail 收件人
	 * @param subject 主题
	 * @param content 正文
	 */
	public void sendSimpleMail(String toEmail, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(username);
		message.setTo(toEmail);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
			logger.info("简单邮件已经发送。");
		} catch (Exception e) {
			logger.error("发送简单邮件时发生异常！", e);
		}

	}
	/**
	 * 发送html格式邮件
	 * @param toEmail 收件人
	 * @param subject 主题
	 * @param content 正文
	 */
	public void sendHtmlMail(String toEmail, String subject, String content) {
	    MimeMessage message = mailSender.createMimeMessage();

	    try {
	        //true表示需要创建一个multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(username);
	        helper.setTo(toEmail);
	        helper.setSubject(subject);
	        helper.setText(content, true);

	        mailSender.send(message);
	        logger.info("html邮件发送成功");
	    } catch (MessagingException e) {
	        logger.error("发送html邮件时发生异常！", e);
	    }
	}
	/**
	 * 发送带附件的邮件
	 * @param toEmail 收件人
	 * @param subject 主题
	 * @param content 正文
	 * @param filePath 附件路径
	 * @param charset 编码,传空默认utf-8,中文gb2312
	 */
	public void sendAttachmentsMail(String toEmail, String subject, String content, String filePath,String charset){
	    MimeMessage message = mailSender.createMimeMessage();
	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(username);
	        helper.setTo(toEmail);
	        helper.setSubject(subject);
	        helper.setText(content, true);
	        FileSystemResource file = new FileSystemResource(new File(filePath));
	        String fileName = filePath.substring(filePath.lastIndexOf(File.separator)+1);
	        if(StringUtil.isEmpty(charset)) {
	        	charset="utf-8";
	        }
	        try {
	        	//fileName= MimeUtility.encodeText(fileName,"utf-8","B");//"gb2312"
				fileName= MimeUtility.encodeText(fileName,charset,"B");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        //String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
	        helper.addAttachment(fileName, file);//添加多个附件可以使用多条 helper.addAttachment(fileName, file)
	        mailSender.send(message);
	        logger.info("带附件的邮件已经发送。");
	    } catch (MessagingException e) {
	        logger.error("发送带附件的邮件时发生异常！", e);
	    }
	}
	/**
	 * 发送带静态资源的邮件
	 * @param toEmail 收件人
	 * @param subject 主题
	 * @param content 正文
	 * @param rscPath 资源路径
	 * @param rscId   资源名称
	 */
	public void sendInlineResourceMail(String toEmail, String subject, String content, String rscPath, String rscId){
	    MimeMessage message = mailSender.createMimeMessage();

	    try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(username);
	        helper.setTo(toEmail);
	        helper.setSubject(subject);
	        helper.setText(content, true);

	        FileSystemResource res = new FileSystemResource(new File(rscPath));
	        helper.addInline(rscId, res);

	        mailSender.send(message);
	        logger.info("嵌入静态资源的邮件已经发送。");
	    } catch (MessagingException e) {
	        logger.error("发送嵌入静态资源的邮件时发生异常！", e);
	    }
	}
}
