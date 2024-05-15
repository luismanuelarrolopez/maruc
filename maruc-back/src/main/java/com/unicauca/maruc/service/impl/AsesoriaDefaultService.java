package com.unicauca.maruc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.comun.ConstantesGlogables;
import com.unicauca.maruc.domain.model.Asesoria;
import com.unicauca.maruc.repositories.AsesoriaDAO;
import com.unicauca.maruc.service.AsesoriaService;

@Service
public class AsesoriaDefaultService extends DefaultBaseService<Asesoria>
    implements AsesoriaService {

  private final AsesoriaDAO asesoriaDAO;

  @Autowired
  public AsesoriaDefaultService(final AsesoriaDAO asesoriaDAO) {
    this.asesoriaDAO = asesoriaDAO;
  }

  @Override
  public Page<Asesoria> listarAsesorias(final int pagina, final int cantidad,
      final String oficinaAsesora) {
    return asesoriaDAO.findAllByOficinaAsesora(PageRequest.of(pagina, cantidad,
        Sort.by(ConstantesGlogables.CAMPO_FECHA_CREACION).descending()), oficinaAsesora);
  }
}
