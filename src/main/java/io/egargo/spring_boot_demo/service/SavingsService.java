package io.egargo.spring_boot_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egargo.spring_boot_demo.model.Savings;
import io.egargo.spring_boot_demo.repository.SavingsRepository;

@Service()
public class SavingsService {
	@Autowired
	SavingsRepository savingsRepository;

	public List<Savings> getAll() {
		return savingsRepository.findAll();
	}

	public void deposit(Savings savings) {
		savingsRepository.save(savings);
	}
}
