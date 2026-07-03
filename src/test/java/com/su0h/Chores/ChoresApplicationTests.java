package com.su0h.Chores;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@org.springframework.test.context.TestPropertySource(properties = {"spring.datasource.url=jdbc:h2:mem:testdb"})
class ChoresApplicationTests {

	@Test
	void contextLoads() {
	}

}
