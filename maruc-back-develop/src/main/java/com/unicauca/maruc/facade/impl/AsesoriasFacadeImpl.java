package com.unicauca.maruc.facade.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.unicauca.maruc.domain.model.Asesoria;
import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.dto.GuardarAsesoriaDTO;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.facade.AsesoriasFacade;
import com.unicauca.maruc.service.AsesoriaService;

@Component
@Transactional
@Slf4j
public class AsesoriasFacadeImpl implements AsesoriasFacade {

  @Autowired
  private AsesoriaService asesoriaService;

  @Override
  @Transactional(readOnly = true)
  public Page<Asesoria> listarAsesoria(final int pagina, final int cantidad,
      final String oficinaAsesora) {
    return asesoriaService.listarAsesorias(pagina, cantidad, oficinaAsesora);
  }

  @Override
  public Asesoria guardarAsesoria(final GuardarAsesoriaDTO asesoriaDTO) {
    final Asesoria asesoria = new Asesoria();
    asesoria.setTema(asesoriaDTO.getTema());
    asesoria.setOficinaAsesora(asesoriaDTO.getOficinaAsesora());
    asesoria.setSolicitante(obtenerSolicitante(asesoriaDTO.getUserId()));
    Asesoria result = asesoriaService.guardar(asesoria);
    return result;
  }

  @Override
  public void agendarAsesoria(final Date fechaAgenda, final Long idAsesoria) {
    Asesoria asesoria = asesoriaService.buscarPorId(idAsesoria).orElseThrow(() -> new EntidadNoExisteException(
        "No se ha encontrado una asesoria con id: " + idAsesoria));
    asesoria
        .setFechaAgendada(LocalDateTime.ofInstant(fechaAgenda.toInstant(), ZoneId.systemDefault()));
    asesoriaService.actualizar(asesoria);
  }

  /*
   * Métodos privados
   */

  private Usuario obtenerSolicitante(final Long userId) {
    final Usuario usuario = new Usuario();
    usuario.setId(userId);
    return usuario;
  }

  // private OficinaAsesora obtenerOficinaAsesora(final String oficinaAsesora)
  //     throws IllegalArgumentException {
  //   if (oficinaAsesora.equals(ConstantesGlogables.OFICINA_CONTROL_INTERNO)) {
  //     return OficinaAsesora.OCI;
  //   } else if (oficinaAsesora
  //       .equals(ConstantesGlogables.OFICINA_PLANEACION_DIRECCION_INSTITUCIONAL)) {
  //     return OficinaAsesora.OPDI;
  //   }
  //   throw new IllegalArgumentException(
  //       "No existe una oficina asesora con nombre: " + oficinaAsesora);
  // }
}
