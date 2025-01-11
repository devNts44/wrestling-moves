package com.wrestling_moves;

import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.service.WrestlerService;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class WrestlerValidationTest {

    private Validator validator;

    @Autowired
    private WrestlerService wrestlerService;

    @BeforeEach
    void setUp() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void whenWrestlerIsValid_thenNoViolations() {
        Wrestler wrestler = new Wrestler("YusKa", "Passw0rd!", "yus.ka@example.com", "Yus", "Ka");

        Set<ConstraintViolation<Wrestler>> violations = validator.validate(wrestler);

        assertThat(violations).isEmpty();
    }

    @Test
    void whenUsernameIsBlank_thenViolationOccurs() {
        Wrestler wrestler = new Wrestler("", "Passw0rd!", "yus.ka@example.com", "Yus", "Ka");

        Set<ConstraintViolation<Wrestler>> violations = validator.validate(wrestler);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("username"));
    }

    @Test
    void whenPasswordIsInvalid_thenViolationOccurs() {
        Wrestler wrestler = new Wrestler("YusKa", "password", "yus.ka@example.com", "Yus", "Ka");

        Set<ConstraintViolation<Wrestler>> violations = validator.validate(wrestler);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("password"));
    }

    @Test
    void whenEmailIsInvalid_thenViolationOccurs() {
        Wrestler wrestler = new Wrestler("YusKa", "Passw0rd!", "not-an-email", "Yus", "Ka");

        Set<ConstraintViolation<Wrestler>> violations = validator.validate(wrestler);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("email"));
    }

    @Test
    void whenFirstNameIsBlank_thenViolationOccurs() {
        Wrestler wrestler = new Wrestler("YusKa", "Passw0rd!", "yus.ka@example.com", "", "Ka");

        Set<ConstraintViolation<Wrestler>> violations = validator.validate(wrestler);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("firstName"));
    }

    @Test
    void whenLastNameIsBlank_thenViolationOccurs() {
        Wrestler wrestler = new Wrestler("YusKa", "Passw0rd!", "Yus.Ka@example.com", "Yus", "");

        Set<ConstraintViolation<Wrestler>> violations = validator.validate(wrestler);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("lastName"));
    }

    @Test
    void whenUsernameContainsSpecialCharacters_thenViolationOccurs() {
        Wrestler wrestler = new Wrestler("Yus@Ka", "Passw0rd!", "yus.ka@example.com", "Yus", "Ka");

        Set<ConstraintViolation<Wrestler>> violations = validator.validate(wrestler);

        assertThat(violations).isNotEmpty();
        assertThat(violations).anyMatch(v -> v.getPropertyPath().toString().equals("username"));
    }

    @Test
    public void checkIfWrestlerIsReturned() throws Exception {
        Wrestler wrestler = new Wrestler("YusKa", "Passw0rd!", "yus.ka@example.com", "Yus", "Ka");

        
        wrestlerService.saveWrestler(wrestler);

        List<Wrestler> all = wrestlerService.findAll();
        assertThat(all).isNotEmpty();
        assertThat(all.size()).isEqualTo(2);

        System.out.println(all.toString());
    }

}
