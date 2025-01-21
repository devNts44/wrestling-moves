package com.wrestling_moves.service;

import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.repository.WrestlerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WrestlerService {

    private final WrestlerRepository wrestlerRepository;

    public WrestlerService(WrestlerRepository wrestlerRepository) {
        this.wrestlerRepository = wrestlerRepository;
    }

    public List<Wrestler> findAll(){
        return wrestlerRepository.findAll();
    }

    public Optional<Wrestler> findWrestler(Long id){
        return wrestlerRepository.findById(id);
    }
    public Optional<Wrestler> findByEmail(String email) {
        return wrestlerRepository.findByEmail(email);
    }

    public void delete(Long id){
        wrestlerRepository.deleteById(id);
    }

    public Wrestler saveWrestler(Wrestler wrestler){
        return wrestlerRepository.save(wrestler);
    }

    public void delete(Wrestler wrestler) {
        wrestlerRepository.delete(wrestler);
    }

    public void deleteAll() {
        wrestlerRepository.deleteAll();
    }   
}
