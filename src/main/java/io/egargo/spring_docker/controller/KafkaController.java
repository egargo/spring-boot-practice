package io.egargo.spring_docker.controller;

import io.egargo.spring_docker.configuration.KafkaMessageProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
	@Autowired
	private KafkaMessageProducer kafkaMessageProducer;

	@PostMapping("/send")
	public String sendMessage(@RequestParam("message") String message) {
		kafkaMessageProducer.sendMessage("my-topic", message);
		return "Message sent: " + message;
	}
}
