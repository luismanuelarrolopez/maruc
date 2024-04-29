package com.unicauca.maruc.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.unicauca.maruc.domain.model.Rol;

public interface RolDAO extends JpaRepository<Rol, Long> {

  Optional<Rol> findByCodigo(String codigo);
}
