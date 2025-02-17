package com.wrestling_moves;

import com.wrestling_moves.controller.WrestlerController;
import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.service.WrestlerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class WrestlerControllerTest {

	@Autowired
	private WrestlerService wrestlerService;

	@InjectMocks
	private WrestlerController wrestlerController;

	@Autowired
	private MockMvc mvc;

	@Transactional
	@Test
	void shouldLoadWrestlerControllerIntoContext() {
		assertThat(wrestlerController).isNotNull();
	}

	@Test
	public void shouldGetAllWrestlers() throws Exception {
		mvc.perform(get("/wrestlers"))
				.andExpect(status().isOk())
				.andExpect(view().name("wrestlersPage"))
				.andExpect(model().attributeExists("wrestlers"));
	}

	@Transactional
	@Test
	public void shouldDeleteWrestler() throws Exception {
		mvc.perform(delete("/delete/wrestlers/1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/wrestlers"));
	}

	@Transactional
	@Test
	public void checkIfWrestlerIsReturned() {
		Wrestler wrestler = new Wrestler();
		wrestler.setUsername("testUser3");
		wrestler.setPassword("Passw0rd!");
		wrestler.setEmail("test3@example.com");
		wrestler.setFirstName("Test3");
		wrestler.setLastName("User3");
		wrestlerService.saveWrestler(wrestler);

		List<Wrestler> all = wrestlerService.findAll();

		assertThat(all).isNotEmpty();
		assertThat(all.size()).isEqualTo(1);
	}
}
