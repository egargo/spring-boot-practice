package io.egargo.spring_docker.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email {
	@Value("${spring.mail.username}")
	private String from;

	@Autowired
	JavaMailSender javaMailSender;

	public void sendMail(final String to, final String subject, final String body) {
		final Date now = new Timezone().getDate();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setSentDate(now);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);

		javaMailSender.send(message);

	}
}
