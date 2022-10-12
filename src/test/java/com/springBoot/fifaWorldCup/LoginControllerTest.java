package com.springBoot.fifaWorldCup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
class LoginControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mMvc;
	
	@BeforeEach
	public void before() {
		mMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void toonLoginPagina_Success() throws Exception {
		mMvc.perform(get("/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("login"))
		.andExpect(model().attributeDoesNotExist("error", "logout"));
	}
	
	@Test
	public void toonLoginPagina_Error_ToonErrorNotif() throws Exception {
		mMvc.perform(get("/login").param("error", ""))
		.andExpect(status().isOk())
		.andExpect(view().name("login"))
		.andExpect(model().attributeExists("error"))
		.andExpect(model().attributeDoesNotExist("logout"));
	}
	
	@Test
	public void toonLoginPagina_Logout_ToonLogoutNotif() throws Exception {
		mMvc.perform(get("/login").param("logout", ""))
		.andExpect(status().isOk())
		.andExpect(view().name("login"))
		.andExpect(model().attributeExists("logout"))
		.andExpect(model().attributeDoesNotExist("error"));
	}

}
