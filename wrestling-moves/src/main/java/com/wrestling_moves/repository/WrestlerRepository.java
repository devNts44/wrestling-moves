package com.wrestling_moves.repository;

import com.wrestling_moves.entity.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WrestlerRepository extends JpaRepository<Wrestler, Long> {

    Wrestler findByEmail(String email);
}