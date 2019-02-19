package com.tirmizee.Rest;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tirmizee.ApplicationInitializer;
import com.tirmizee.backend.test.Person;
import com.tirmizee.backend.test.PersonDTO;
import com.tirmizee.core.component.GenericJMapperImpl;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationInitializer.class})
public class RestApiControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void name() throws Exception {
		
//		Person source = new Person();
//		source.setId(22);
//		source.setName("sssssssssss");
//		PersonDTO dto = GenericJMapperImpl.map(source, PersonDTO.class);
//		System.out.println(dto.getName());
//		assertEquals(source.getId(), dto.getId());
	}
	
}
