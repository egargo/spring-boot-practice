package io.egargo.spring_boot_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.egargo.spring_boot_demo.model.Savings;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
