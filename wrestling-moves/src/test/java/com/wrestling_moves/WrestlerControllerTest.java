package com.wrestling_moves;

import com.wrestling_moves.controller.WrestlerController;
import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.service.WrestlerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class WrestlerControllerTest {

	@Autowired
	private WrestlerService wrestlerService;

	@InjectMocks
	private WrestlerController wrestlerController;

	@Autowired
	private MockMvc mvc;

	@Transactional
	@Test
	void  shouldLoadWrestlerControllerIntoContext() {
		assertThat(wrestlerController).isNotNull();
	}

	@Transactional
	@Test
	public void shouldReturnOkForRootEndpoint() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	//@Transactional
	@Test
    public void shouldGetAllWrestlers() throws Exception {
        mvc.perform(get("/wrestlers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

	@Transactional
	@Test
	public void shouldCreateNewWrestler() throws Exception {
    mvc.perform(post("/wrestlers")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{" +
                    "\"username\":\"johncena\"," +
                    "\"password\":\"Passw0rd!\"," +
                    "\"email\":\"john.cena@wwe.com\"," +
                    "\"firstName\":\"John\"," +
                    "\"lastName\":\"Cena\"" +
                    "}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("johncena"))
            .andExpect(jsonPath("$.email").value("john.cena@wwe.com"))
            .andExpect(jsonPath("$.firstName").value("John"))
            .andExpect(jsonPath("$.lastName").value("Cena"));
}
	@Transactional
	@Test
	public void shouldGetWrestlerById() throws Exception {

		Wrestler wrestler = new Wrestler();
		wrestler.setUsername("testUser");
		wrestler.setPassword("Passw0rd!");
		wrestler.setEmail("test@example.com");
		wrestler.setFirstName("Test");
		wrestler.setLastName("User");
		Wrestler savedWrestler = wrestlerService.saveWrestler(wrestler);

		Long id = savedWrestler.getId();

		mvc.perform(get("/wrestlers/" + id)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id.intValue()));
	}


	//@Transactional
    @Test
    public void shouldReturn404WhenWrestlerNotFound() throws Exception {
        mvc.perform(get("/wrestlers/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

	@Transactional
    @Test
    public void shouldDeleteWrestler() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/wrestlers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	@Transactional
	@Test
	public void checkIfWrestlerIsReturned() {
		// Ajouter un lutteur pour garantir des données dans la base de données
		Wrestler wrestler = new Wrestler();
		wrestler.setUsername("testUser3");
		wrestler.setPassword("Passw0rd!");
		wrestler.setEmail("test3@example.com");
		wrestler.setFirstName("Test3");
		wrestler.setLastName("User3");
		wrestlerService.saveWrestler(wrestler);

		// Récupérer tous les lutteurs
		List<Wrestler> all = wrestlerService.findAll();

		// Vérifier que la liste n'est pas vide et contient bien un élément
		assertThat(all).isNotEmpty();
		assertThat(all.size()).isEqualTo(1); // Vérifie qu'il y a exactement 1 lutteur

		System.out.println(all);
	}

	//@Transactional
	@Test
	public void whenPostRequestToWrestlerAndValidWrestler_thenCorrectResponse() throws Exception {
		MediaType textPlainUtf8 = new MediaType(MediaType.APPLICATION_JSON);
		String wrestler = """
        {
            "username": "johndoe",
            "password": "Passw0rd!",
            "email": "john.doe@domain.com",
            "firstName": "John",
            "lastName": "Doe" \s
        }
    \t""";
		mvc.perform(MockMvcRequestBuilders.post("/wrestlers")
						.content(wrestler)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content()
						.contentType(textPlainUtf8));
	}
}
