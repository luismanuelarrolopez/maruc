package com.unicauca.maruc.dto;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicauca.maruc.domain.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Clase usada para obtener la información de la <code>Authentication</code> de spring.
 * 
 * @author Sebastián Carabali
 *
 */
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO implements UserDetails {

  /**
   * 
   */
  private static final long serialVersionUID = 5705577818589548974L;
  private Long id;
  private String email;
  private String nombres;
  private String apellidos;
  @JsonIgnore
  private String password;
  private GrantedAuthority authority;

  public static UserDetailsDTO build(Usuario usuario) {
    return new UserDetailsDTO(usuario.getId(), usuario.getEmail(), usuario.getNombres(),
        usuario.getApellidos(), usuario.getPassword(),
        new SimpleGrantedAuthority(usuario.getRol().getCodigo()));
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singletonList(authority);
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public String getNombres() {
    return nombres;
  }

  public String getApellidos() {
    return apellidos;
  }

  public GrantedAuthority getAuthority() {
    return authority;
  }


}
