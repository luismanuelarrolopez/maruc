package com.unicauca.maruc.repositories;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unicauca.maruc.domain.model.Usuario;

@Repository
public interface UsuarioDAO extends PagingAndSortingRepository<Usuario, Long> {

  Optional<Usuario> findByEmail(String email);

  Boolean existsByEmail(String email);

  List<Usuario> findByRolId(long rol_id);

  List<Usuario> findByRolCodigo(String rol);

  Optional<Usuario> findByUuid(String uuid);

  Optional<Usuario> findByPassword(String password);
}
