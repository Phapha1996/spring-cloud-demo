package org.fage.springbootjavamail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJavamailApplicationTests {

	@Autowired
	private JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
    private String mailUsername;
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 测试简单邮件的收发
     */
	@Test
	public void testSimpleSender(){
	    //构造一个简单邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送方邮箱，若与username值相同将抛出from与username不符的异常
        message.setFrom(mailUsername);
        //收件人邮箱
        message.setTo("1115054416@qq.com");
        //邮件标题
        message.setSubject("主题：这是一封JavaMail测试邮件");
        //邮件内容
        message.setText("这里是JavaMail发送的内容，请查收!");
        javaMailSender.send(message);
	}


    /**
     *
     * 附件发送file1
     * 嵌入html的file2
     *
     */
	@Test
    public void testAttachmentsMail() throws MessagingException, FileNotFoundException {

        String fileUrl = ResourceUtils.getURL("classpath:").getPath().toString() + "/static/";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(mailUsername);
        helper.setTo("1115054416@qq.com");
        helper.setSubject("主题：有附件");
        //嵌入图片
        helper.setText("有附件的邮件,<html><body><img src=\"cid:fafa\" ></body></html>", true);
        FileSystemResource file1 = new FileSystemResource(new File(fileUrl + "zhuzhu.png"));
        FileSystemResource file2 = new FileSystemResource(new File(fileUrl + "fafa.png"));
        //附件发送
        helper.addAttachment("附件-1.jpg", file1);
        //增加一个id在标签中显示
        helper.addInline("fafa", file2);
        javaMailSender.send(mimeMessage);

    }


    /**
     * 模板邮件
     * @throws MessagingException
     */
	@Test
    public void testTemplateMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        //为thymeleaf中的变量设置参数，准备发送
        Context thymeleafCtx = new Context();
        thymeleafCtx.setVariable("username","接收方UserName");
        thymeleafCtx.setVariable("message","内容体Message");
        //设置邮件发送体
        helper.setFrom(mailUsername);
        helper.setTo("1115054416@qq.com");
        helper.setSubject("主题：模板邮件");
        //获取thymeleaf的html模板
        String emailContent= templateEngine.process("mail_template", thymeleafCtx);
        helper.setText(emailContent, true);
        //发送
        javaMailSender.send(mimeMessage);
    }


}