package com.wrestling_moves.repository;

import com.wrestling_moves.entity.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WrestlerRepository extends JpaRepository<Wrestler, Long> {

    Optional<Wrestler> findByEmail(String email);
}