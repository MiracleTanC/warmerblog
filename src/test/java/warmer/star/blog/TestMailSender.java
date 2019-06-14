package warmer.star.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import warmer.star.blog.util.EmailSenderUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestMailSender {
	@Autowired
	JavaMailSender jms;
	@Autowired
	EmailSenderUtil emailSenderUtil;
	@Autowired
	TemplateEngine templateEngine;
	//@Test
	public void send(){
		//建立邮件消息
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		//发送者
		mainMessage.setFrom("warmercloud@126.com");
		//接收者
		mainMessage.setTo("1130196938@qq.com");
		//发送的标题
		mainMessage.setSubject("嗨喽");
		//发送的内容
		mainMessage.setText("hello world");
		jms.send(mainMessage);
	}
	//@Test
	public void sendSimple() {
		emailSenderUtil.sendSimpleMail("1130196938@qq.com", "简单文本消息测试", "fly with me !");
	}
	//@Test
	public void sendHtmlMail() {
		String html="<table  border=\"1\" bordercolor=\"#a0c6e5\" style=\"border-collapse:collapse;\"><tr><td>我</td><td>我</td><td>我</td></tr><tr><td>很</td><td>很</td><td>很</td></tr><tr><td>帅</td><td>帅</td><td>帅</td></tr></table>";
		emailSenderUtil.sendHtmlMail("1130196938@qq.com", "html测试", html);
	}
	//@Test
	public void sendAttachmentsMail() {
	    String filePath="d:\\Python编程：从入门到实践.pdf";
	    //String filePath="d:\\aaa.txt";
		//String filePath="d:\\Python.pdf";
	    emailSenderUtil.sendAttachmentsMail("1130196938@qq.com", "带附件的邮件", "有附件，请查收！", filePath,"gb2312");//中文附件名
	    //emailSenderUtil.sendAttachmentsMail("1130196938@qq.com", "带附件的邮件", "有附件，请查收！", filePath,"");
	}
	//@Test
	public void sendInlineResourceMail() {
	    String rscId = "warmer008";
	    String content="<html><body>你好,测试一下发送图片,这是有图片的邮件：<bg src=\'cid:" + rscId + "\' >,请你注意查收</body></html>";
	    //String imgPath = "F:\\Images\\jpg\\1.jpg";
	    String imgPath = "C:\\Users\\Tan\\Desktop\\images\\miao.png";

	    emailSenderUtil.sendInlineResourceMail("2395953679@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
	}
	//@Test
	public void sendTemplateMail() {
	    //创建邮件正文
	    Context context = new Context();
	    context.setVariable("id", "006");
	   
	    //String emailContent = templateEngine.process("emailTemplate", context);
	    String emailContent = templateEngine.process("email/emailTemplate", context);//这里是根据classpath,是按template根目录来的

	    emailSenderUtil.sendHtmlMail("1130196938@qq.com","主题：这是模板邮件",emailContent);
	}
	@Test
	public void sendTemplateMail2() {
	    //创建邮件正文
	    Context context = new Context();
	    context.setVariable("username", "tc");
	    context.setVariable("methodName", "addMessage");
	    context.setVariable("errorMessage", "空异常");
	    context.setVariable("occurredTime", "2018-08-01");
	    String emailContent = templateEngine.process("email/ServerErrorTemplate", context);//这里是根据classpath,是按template根目录来的

	    emailSenderUtil.sendHtmlMail("1130196938@qq.com","主题：服务器错误",emailContent);
	}
}
