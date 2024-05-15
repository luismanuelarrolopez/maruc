package com.unicauca.maruc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.dto.UserDetailsDTO;
import com.unicauca.maruc.exceptions.UsuarioInhabilitadoException;
import com.unicauca.maruc.repositories.UsuarioDAO;
import com.unicauca.maruc.util.MarucSecurityQualifier;

import lombok.extern.slf4j.Slf4j;

@Service
@MarucSecurityQualifier
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired
  private UsuarioDAO userRepository;

  @Override
  public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
    final Usuario user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(
        String.format("No se ha encontrado un usuario con email %s", email)));
    if (!user.isVerificado()) {
      throw new UsuarioInhabilitadoException(email);
    }
    return UserDetailsDTO.build(user);
  }

}
