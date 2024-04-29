package com.unicauca.maruc.service.impl;

import com.unicauca.maruc.domain.model.ControlResidual;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Evidencia;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.exceptions.EntidadYaExisteException;
import com.unicauca.maruc.repositories.EvidenciaDAO;
import com.unicauca.maruc.service.EvidenciaService;

@Service
public class EvidenciaServiceImpl implements EvidenciaService {

    @Autowired
    private EvidenciaDAO evidenciaDAO;

    @Override
    public Evidencia guardarEvidencia(Evidencia evidencia) {
        if (Objects.isNull(evidencia.getId())) {
            return evidenciaDAO.save(evidencia);
        }
        throw new EntidadYaExisteException(
                String.format("La evidencia con id %s ya se encuentra registrado.", evidencia.getId()));
    }

    @Override
    public List<Evidencia> listarEvidencias() {
        return evidenciaDAO.findAll();
    }

    @Override
    public Evidencia encontrarEvidencia(Long id) {
        Optional<Evidencia> evidencia = evidenciaDAO.findById(id);
        if (evidencia.isPresent()) {
            return evidencia.get();
        }else{
            throw new EntidadNoExisteException(
                    String.format("La evidencia con id %s no se encuentra registrado.", id));
        }
    }

    @Override
    public Evidencia actualizarEvidencia(Evidencia evidenciap) {
        Optional<Evidencia> evidencia = evidenciaDAO.findById(evidenciap.getId());
        if (evidencia.isPresent()) {
            Evidencia evi = evidencia.get();
            evi.setEvidencia(evidenciap.getEvidencia());
            evi.setPorcentajeAvanceOci(evidenciap.getPorcentajeAvanceOci());
            evi.setPorcentajeAvanceOpdi(evidenciap.getPorcentajeAvanceOpdi());
            evi.setCumplimiento_oci(evidenciap.getCumplimiento_oci());
            evi.setCumplimiento_opdi(evidenciap.getCumplimiento_opdi());
            evidenciaDAO.save(evi);
            return evi;
        }else{
            throw new EntidadNoExisteException(
                    String.format("La evidencia con id %s no se encuentra registrado.", evidenciap.getId()));
        }
    }

    @Override
    public Evidencia findByControlResidual(ControlResidual controlResidual) {
        return evidenciaDAO.findByControl(controlResidual);
    }
}
