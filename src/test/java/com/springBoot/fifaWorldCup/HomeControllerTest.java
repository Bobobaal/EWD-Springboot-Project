package com.springBoot.fifaWorldCup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
class HomeControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mMvc;
	
	@BeforeEach
	public void before() {
		mMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testRedirect_Success() throws Exception {
		mMvc.perform(get("/")).andExpect(status().isFound()).andExpect(view().name("redirect:/fifa"));
	}

}
