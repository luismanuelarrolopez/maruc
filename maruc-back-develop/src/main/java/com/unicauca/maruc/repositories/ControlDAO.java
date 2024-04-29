package com.unicauca.maruc.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.unicauca.maruc.domain.model.Control;

public interface ControlDAO extends JpaRepository<Control, Long>{

  List<Control> findByRiesgoId(Long id);
}
