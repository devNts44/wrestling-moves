package com.wrestling_moves;

import com.wrestling_moves.entity.Wrestler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class WrestlerValidationTest {

    private Validator validator;

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
}
