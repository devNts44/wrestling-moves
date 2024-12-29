package com.wrestling_moves.service;

import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.repository.WrestlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WrestlerService {

    @Autowired
    WrestlerRepository wrestlerRepository;

    public List<Wrestler> findAll(){
        return wrestlerRepository.findAll();
    }

    public Optional<Wrestler> findUser(Long id){
        return wrestlerRepository.findById(id);
    }

    public void deleteUser(Long id){
        wrestlerRepository.deleteById(id);
    }

    public Wrestler saveUser(Wrestler wrestler){
        return wrestlerRepository.save(wrestler);
    }
}
