package com.walkway.account_service;

import com.walkway.account_service.entity.Role;
import com.walkway.account_service.feign.keycloak.KeycloakRoleClient;
import com.walkway.account_service.security.AccountClientAccessTokenManager;
import com.walkway.account_service.security.RealmRoleMapping;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

@EnableFeignClients
@SpringBootApplication(
		exclude = {org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class}
)
public class AccountServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner( ){
		return runner->{
		};
	}
}