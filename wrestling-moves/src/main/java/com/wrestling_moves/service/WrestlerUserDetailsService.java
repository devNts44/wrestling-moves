package com.wrestling_moves.service;

import com.wrestling_moves.entity.Wrestler;
import com.wrestling_moves.repository.WrestlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WrestlerUserDetailsService implements UserDetailsService {

    @Autowired
    private WrestlerRepository wrestlerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Wrestler wrestler = wrestlerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + email));

        return new User(wrestler.getEmail(), wrestler.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
 