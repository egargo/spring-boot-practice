package io.egargo.spring_boot_demo.mapper;

import org.springframework.stereotype.Service;

import io.egargo.spring_boot_demo.dto.SavingsDTO;
import io.egargo.spring_boot_demo.model.Savings;

@Service
public class SavingsDTOMapper {
	public Savings depositSavings(SavingsDTO savingsDTO) {
		Savings savings = new Savings();

		return savings;
	}
}
