package com.ahiru.grillecrypt.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class EncryptControllerTest {

	private MockMvc mockMvs;
	
	@BeforeEach
	void init(WebApplicationContext webApplicationContext) {
		this.mockMvs = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	void test() throws Exception {
		this.mockMvs.perform(get("/encrypt"))
		.andExpect(status().isOk())
		.andExpect(view().name("encrypt"));
	}

}
