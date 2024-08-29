package io.egargo.spring_docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.egargo.spring_docker.model.Savings;

public interface SavingsRepository extends JpaRepository<Savings, Long> {
}
