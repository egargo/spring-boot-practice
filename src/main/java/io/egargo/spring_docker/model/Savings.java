package io.egargo.spring_docker.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "Savings")
@Table(name = "savings")
public class Savings implements Serializable {
	private static Savings singleInstance;

	public static synchronized Savings getInstance() {
		if (singleInstance == null) {
			singleInstance = new Savings();
		}
		System.out.println("Singleton: " + singleInstance);
		return singleInstance;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	public Long id;

	@Column(name = "balance", nullable = false, unique = false)
	public Double balance;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, unique = true, referencedColumnName = "id")
	public User user;
}
