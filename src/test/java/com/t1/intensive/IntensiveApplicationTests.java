package com.t1.intensive;

import com.t1.intensive.controller.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ActiveProfiles("test")
class IntensiveApplicationTests {

	@Autowired
	private AccountController accountController;

	@Test
	void contextLoads() {
		assertThat(accountController).isNotNull();
	}

}
