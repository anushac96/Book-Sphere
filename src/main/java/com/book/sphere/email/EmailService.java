package com.book.sphere.email;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static java.nio.charset.StandardCharsets.UTF_8;
@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;
	private final SpringTemplateEngine templateEngine;

	@Async
	public void sendEmail(String to, String username, EmailTemplateName emailTemplate, String confirmationUrl,
			String activationCode, String subject) throws MessagingException {
		String templateName;
		if (emailTemplate == null) {
			templateName = "confirm-email";
		} else {
			templateName = emailTemplate.name();
		}
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage
				,MimeMessageHelper.MULTIPART_MODE_MIXED,UTF_8.name());
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("username", username);
		properties.put("confirmationUrl", confirmationUrl);
		properties.put("activation_code", activationCode);

		Context context = new Context();
		context.setVariables(properties);

		helper.setFrom("anushac96@gmail.com");
		helper.setTo(to);
		helper.setSubject(subject);

		String template = templateEngine.process(templateName, context);

		helper.setText(template, true);

		mailSender.send(mimeMessage);
	}
}
