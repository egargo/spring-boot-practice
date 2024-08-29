package io.egargo.spring_docker.mapper;

import org.springframework.stereotype.Service;

import io.egargo.spring_docker.dto.SavingsDTO;
import io.egargo.spring_docker.model.Savings;

@Service
public class SavingsDTOMapper {
	public Savings depositSavings(SavingsDTO savingsDTO) {
		Savings savings = new Savings();

		return savings;
	}
}
