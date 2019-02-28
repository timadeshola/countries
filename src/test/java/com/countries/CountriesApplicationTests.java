package com.countries;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ConfigurationProperties(value = "test")
public class CountriesApplicationTests {

	@Test
	public void contextLoads() {
	}

}
