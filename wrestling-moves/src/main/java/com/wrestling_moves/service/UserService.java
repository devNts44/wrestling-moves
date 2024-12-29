package com.wrestling_moves.service;

import com.wrestling_moves.entity.User;
import com.wrestling_moves.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository  userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findUser(Long id){
        return userRepository.findById(id);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
