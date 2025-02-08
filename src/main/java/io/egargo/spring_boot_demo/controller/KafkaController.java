package io.egargo.spring_boot_demo.controller;

import io.egargo.spring_boot_demo.configuration.KafkaMessageProducer;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	@Autowired
	private KafkaMessageProducer kafkaMessageProducer;

	@PostMapping(value = "/send", consumes = "text/plain;charset=UTF-8", produces = "application/json")
	public ResponseEntity<?> send(@RequestBody String message) {
		try {
			kafkaMessageProducer.sendMessage("my-topic", message);
			return new ResponseEntity<>(
					Collections.singletonMap("message", "Successfully sent data"),
					HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(
					Collections.singletonMap("message", "An unexpected error occurred"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
