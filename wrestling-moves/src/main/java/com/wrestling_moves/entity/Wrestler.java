package com.wrestling_moves.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;

@Entity
@Table(name = "WRESTLER")
public class Wrestler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Column(nullable = false, unique = true, length = 50)
    @Pattern(regexp="^[a-zA-Z0-9_]+$")
    private String username;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Column(nullable = false)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Le mot de passe doit contenir au moins une lettre majuscule, une lettre minuscule, un chiffre, un caractère spécial (@$!%*?&) et comporter au moins 8 caractères."
    )
    private String password;

    @Email(message = "L'email doit être valide")
    @NotBlank(message = "L'email est obligatoire")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Le prénom est obligatoire")
    @Column(name = "first_name", length = 50)
    @Pattern(regexp="^[a-zA-Z0-9]+$")
    private String firstName;

    @NotBlank(message = "Le nom de famille est obligatoire")
    @Column(name = "last_name", length = 50)
    @Pattern(regexp="^[a-zA-Z0-9]+$")
    private String lastName;

    public Wrestler() {
    }

    public Wrestler(String username, String password, String email, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Wrestler))
            return false;
        Wrestler

                wrestler = (Wrestler) o;
        return Objects.equals(this.id, wrestler.id)
                && Objects.equals(this.firstName, wrestler.firstName)
                && Objects.equals(this.lastName, wrestler.lastName)
                && Objects.equals(this.email, wrestler.email)
                && Objects.equals(this.username, wrestler.username);
    }
    @Override
    public String toString() {
        return "Wrestler{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
