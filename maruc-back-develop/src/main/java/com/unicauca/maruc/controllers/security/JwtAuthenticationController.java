package com.unicauca.maruc.controllers.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unicauca.maruc.configuration.security.JwtTokenUtil;
import com.unicauca.maruc.dto.GuardarUsuarioDTO;
import com.unicauca.maruc.dto.JwtRequest;
import com.unicauca.maruc.dto.JwtResponse;
import com.unicauca.maruc.facade.AdministrarUsuariosFacade;
import com.unicauca.maruc.service.impl.JwtUserDetailsService;

@CrossOrigin("*")
@RestController
@RequestMapping("auth")
public class JwtAuthenticationController {

  @Autowired
  private AdministrarUsuariosFacade administrarUsuariosFacade;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JwtTokenUtil jwtTokenUtil;
  @Autowired
  private JwtUserDetailsService jwtUserDetailsService;

  @RequestMapping(value = "signin", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(
      @Valid @RequestBody JwtRequest authenticationRequest) throws Exception {

    authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

    final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

    final String token = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new JwtResponse(token));
  }

  @RequestMapping(value = "/signup", method = RequestMethod.POST)
  public ResponseEntity<?> saveUser(@Valid @RequestBody GuardarUsuarioDTO user) throws Exception {
    return ResponseEntity.ok(administrarUsuariosFacade.guardarUsuario(user));
  }

  private void authenticate(String username, String password) {
    authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }
}
