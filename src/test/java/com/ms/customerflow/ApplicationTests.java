package com.ms.customerflow;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(() -> Application.main(new String[] {"args"}));
	}

}
