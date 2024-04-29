package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.repositories.ActividadDAO;
import com.unicauca.maruc.service.ActividadService;

@Service
public class ActividadServiceDefaultImpl extends DefaultBaseService<Actividad>
    implements ActividadService {

  @Autowired
  private ActividadDAO actividadDAO;

  @Override
  public Page<Actividad> fetchActividades(final int page, final int size) {
    return actividadDAO.findAll(PageRequest.of(page, size, Sort.by("fechaCreacion").descending()));
  }

  @Override
  public List<Actividad> fetchActividades() {
    return StreamSupport.stream(actividadDAO.findAll().spliterator(), false)
        .collect(Collectors.toList());
  }
}
