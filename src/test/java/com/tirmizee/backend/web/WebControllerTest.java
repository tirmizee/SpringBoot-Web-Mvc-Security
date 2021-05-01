package com.tirmizee.backend.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;

//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebControllerTest {
	
//	@Autowired
	private MockMvc mockMvc;
	
//	@Test
	public void registrationWorksThroughAllLayers() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk());
	}
	
}
