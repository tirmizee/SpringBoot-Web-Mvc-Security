package com.tirmizee.core;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HikariIntegrationTest {

	@Autowired
    private DataSource dataSource;
 
    /*@Test
    public void hikariConnectionPoolIsConfigured() {
        assertEquals("com.zaxxer.hikari.HikariDataSource", dataSource.getClass().getName());
    }*/
    
}
