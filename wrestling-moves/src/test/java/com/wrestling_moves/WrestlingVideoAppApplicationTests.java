package com.wrestling_moves;

import com.wrestling_moves.controller.WrestlerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class WrestlingVideoAppApplicationTests {

	@Autowired
	private WrestlerController wrestlerController;

	@Autowired
	private MockMvc mvc;

	@Test
	void shouldLoadWrestlerControllerIntoContext() throws Exception {
		assertThat(wrestlerController).isNotNull();
	}
	@Test
	public void shouldReturnOkForRootEndpoint() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	/*@Test
	public void getMappingWrestlersIsOk() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/wrestlers").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}*/
}
