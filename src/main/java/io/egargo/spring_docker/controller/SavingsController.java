package io.egargo.spring_docker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.egargo.spring_docker.model.Savings;
import io.egargo.spring_docker.service.SavingsService;

@RestController
@RequestMapping("/api/savings")
public class SavingsController {
	@Autowired
	SavingsService savingsService;

	@GetMapping(value = "balance", produces = "application/json")
	public ResponseEntity<?> getBalance() {
		return new ResponseEntity<>(savingsService.getAll(), HttpStatus.OK);
	}

	@PostMapping(value = "deposit", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> deposit(@RequestParam Long id, @RequestBody Savings savings) {
		savingsService.deposit(savings);
		return new ResponseEntity<>("Deposit", HttpStatus.OK);
	}
}
