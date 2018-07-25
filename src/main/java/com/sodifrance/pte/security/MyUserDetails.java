package com.sodifrance.pte.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sodifrance.pte.dao.UtilisateurDao;
import com.sodifrance.pte.model.entity.Utilisateur;


@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UtilisateurDao userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    final Utilisateur user = userRepository.findByLogin(username);

    if (user == null) {
      throw new UsernameNotFoundException("User '" + username + "' not found");
    }

    return org.springframework.security.core.userdetails.User//
        .withDefaultPasswordEncoder()
        .username(username)//
        .password(user.getPassword())//
        .authorities(user.getRole())//
        .accountExpired(false)//
        .accountLocked(false)//
        .credentialsExpired(false)//
        .disabled(false)//
        .build();
  }

}
