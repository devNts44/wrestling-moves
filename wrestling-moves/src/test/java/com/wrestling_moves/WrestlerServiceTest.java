package com.wrestling_moves;

import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.repository.WrestlerRepository;
import com.wrestling_moves.service.WrestlerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class WrestlerServiceTest {

    @Autowired
    private WrestlerService wrestlerService;

    @Autowired
    private WrestlerRepository wrestlerRepository;

    @Test
    public void testFindByUsername() {
        Wrestler wrestler = new Wrestler();
        wrestler.setUsername("islam_massat");
        wrestler.setPassword("P@ssword123!");
        wrestler.setEmail("islam@example.com");
        wrestler.setFirstName("Islam");
        wrestler.setLastName("Massat");

        wrestlerService.saveWrestler(wrestler);

        Optional<Wrestler> foundWrestler = wrestlerService.findByEmail("islam@example.com");

        assertTrue(foundWrestler.isPresent(), "Le wrestler devrait être trouvé");
        assertEquals("islam_massat", foundWrestler.get().getUsername(), "Le username devrait correspondre");
        assertEquals("Islam", foundWrestler.get().getFirstName(), "Le prénom devrait correspondre");
        assertEquals("Massat", foundWrestler.get().getLastName(), "Le nom de famille devrait correspondre");
        assertEquals("islam@example.com", foundWrestler.get().getEmail(), "L'email devrait correspondre");
    }

    @Test
    public void testFindByUsername_NotFound() {
        Optional<Wrestler> foundWrestler = wrestlerService.findByEmail("existe_pas@gmail.com");

        assertFalse(foundWrestler.isPresent(), "Aucun wrestler ne devrait être trouvé");
    }
}