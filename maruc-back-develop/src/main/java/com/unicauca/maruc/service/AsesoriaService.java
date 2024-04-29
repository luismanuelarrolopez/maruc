package com.unicauca.maruc.service;

import org.springframework.data.domain.Page;

import com.unicauca.maruc.domain.model.Asesoria;

public interface AsesoriaService extends BaseService<Asesoria> {

  Page<Asesoria> listarAsesorias(int pagina, int cantidad, String oficinaAsesora);
}
