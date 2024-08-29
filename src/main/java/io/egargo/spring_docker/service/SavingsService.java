package io.egargo.spring_docker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egargo.spring_docker.model.Savings;
import io.egargo.spring_docker.repository.SavingsRepository;

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
