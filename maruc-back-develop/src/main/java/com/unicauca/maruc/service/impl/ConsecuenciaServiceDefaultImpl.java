package com.unicauca.maruc.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.unicauca.maruc.domain.model.Consecuencia;
import com.unicauca.maruc.repositories.ConsecuenciaDAO;
import com.unicauca.maruc.service.ConsecuenciaService;

@Service
public class ConsecuenciaServiceDefaultImpl implements ConsecuenciaService {

  @Autowired
  private ConsecuenciaDAO consecuenciaDAO;

  @Override
  public Consecuencia registrarConsecuencia(Consecuencia consecuencia) {
    return consecuenciaDAO.save(consecuencia);
  }

  @Override
  public Consecuencia actualizarConsecuencia(Consecuencia consecuencia) {
    return consecuenciaDAO.save(consecuencia);
  }

  @Override
  public Optional<Consecuencia> buscarConsecuencia(Long id) {
    return consecuenciaDAO.findById(id);
  }

  @Override
  public Long eliminar(Consecuencia consecuencia) {
    consecuenciaDAO.delete(consecuencia);
    return consecuencia.getId();
  }

  @Override
  public Page<Consecuencia> listarConsecuencias(final int page, final int size) {
    return
        consecuenciaDAO.findAll(PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
  }
}
