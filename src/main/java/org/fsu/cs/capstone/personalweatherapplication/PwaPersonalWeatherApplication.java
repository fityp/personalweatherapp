package org.fsu.cs.capstone.personalweatherapplication;

import org.fsu.cs.capstone.personalweatherapplication.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class PwaPersonalWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(PwaPersonalWeatherApplication.class, args);
	}

}
