package com.unicauca.maruc.facade.impl;

import com.unicauca.maruc.domain.model.Causa;
import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;
import com.unicauca.maruc.domain.model.RiesgoResidual;
import com.unicauca.maruc.dto.mappers.ControlResidualMapper;
import com.unicauca.maruc.dto.riesgos.CausaDTO;
import com.unicauca.maruc.dto.riesgos.ControlResidualDTO;
import com.unicauca.maruc.dto.riesgos.GuardarControlResidualRequest;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.exceptions.MarucError;
import com.unicauca.maruc.facade.ControlResidualFacade;
import com.unicauca.maruc.service.CausaService;
import com.unicauca.maruc.service.ControlResidualService;
import com.unicauca.maruc.service.EvidenciaService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ControlResidualFacadeImpl implements ControlResidualFacade {

  @Autowired
  private CausaService causaService;
  @Autowired
  private ControlResidualService controlResidualService;
  @Autowired
  private EvidenciaService evidenciaService;

  @Override
  public List<CausaDTO> obtenerCausasSinControl(final Long riesgoId) {
    return causaService.buscarCausasSinControlResidual(riesgoId);
  }

  @Override
  public List<CausaDTO> obtenerCausasDelControl(Long riesgoId, final Long controlId) {
    return causaService.buscarCausasDeControlResidual(riesgoId, controlId);
  }

  @Override
  @Transactional(readOnly = false)
  public boolean guardarControlResidual(final GuardarControlResidualRequest request) {
    try {
      final ControlResidualDTO controlResidualDTO = request.getControlResidual();
      ControlResidual controlResidual = ControlResidualMapper.mapFromDTO(controlResidualDTO);
      controlResidual.setRiesgoResidual(new RiesgoResidual(request.getIdRiesgoResidual()));
      controlResidual.setCausas(obtenerCausas(request));
      controlResidual = controlResidualService.guardar(controlResidual);
      Evidencia evidencia = crearEvidenciaDeControl(request.getEvidencia());
      evidencia.setControl(controlResidual);
      evidenciaService.guardarEvidencia(evidencia);
      return true;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return false;
    }
  }

  private List<Causa> obtenerCausas(GuardarControlResidualRequest request) {
    log.info("=============> Cantidad de causa: {}", request.getListaIdCausas().size());
    return request.getListaIdCausas().stream()
        .map(id -> causaService.buscarPorId(id).get()).collect(Collectors.toList());
  }

  private Evidencia crearEvidenciaDeControl(final String evidencia) {
    return Evidencia.builder()
        .evidencia(evidencia)
        .cumplimiento_oci(false)
        .cumplimiento_opdi(false)
        .porcentajeAvanceOci(0f)
        .porcentajeAvanceOpdi(0f).build();
  }

  // private List<CausaControlResidual> obtenerListaCausasFrom(
  //     final GuardarControlResidualRequest request, final ControlResidual controlResidual) {
  //   return request.getListaIdCausas().stream()
  //       .map(idCausa -> CausaControlResidual.builder().causa(new Causa(idCausa)).build())
  //       .toList();
  // }

  @Override
  @Transactional(readOnly = false)
  public boolean actualizarControlResidual(final Long id,
      final GuardarControlResidualRequest request) {
    try {
      final ControlResidual controlResidual = controlResidualService.buscarPorId(id)
          .orElseThrow(() -> new EntityNotFountException(
              "No existe el control residual con el id: " + id));
      ControlResidual actualizado = ControlResidualMapper.setEntityValuesFromDTO(
          controlResidual, request.getControlResidual());
      Evidencia evidencia = evidenciaService.findByControlResidual(actualizado);
      actualizado.setCausas(obtenerCausas(request));
      actualizado = controlResidualService.actualizar(actualizado);
      if (evidencia == null) {
        evidencia = crearEvidenciaDeControl(request.getEvidencia());
      }
      evidencia.setEvidencia(request.getEvidencia());
      evidencia.setControl(actualizado);
      evidenciaService.guardarEvidencia(evidencia);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      throw new MarucError(HttpStatus.BAD_REQUEST,
          "ControlResidualFacadeImpl::actualizarControlResidual", e.getMessage());
    }
  }
}
