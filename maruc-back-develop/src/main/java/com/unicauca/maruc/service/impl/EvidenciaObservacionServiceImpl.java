package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.EvidenciaObservacion;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.exceptions.EntidadYaExisteException;
import com.unicauca.maruc.repositories.EvidenciaObservacionDAO;
import com.unicauca.maruc.service.EvidenciaObservacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvidenciaObservacionServiceImpl implements EvidenciaObservacionService {

    @Autowired
    private EvidenciaObservacionDAO evidenciaObservacionDAO; 

    @Override
    public EvidenciaObservacion guardarEvidenciaObservacion(EvidenciaObservacion EvidenciaObservacion) {
        if (Objects.isNull(EvidenciaObservacion.getId())) {
            return evidenciaObservacionDAO.save(EvidenciaObservacion);
        }
        throw new EntidadYaExisteException(
                String.format("La evidencia con id %s ya se encuentra registrado.", EvidenciaObservacion.getId()));
    }

    @Override
    public List<EvidenciaObservacion> listarEvidenciaObservacion() {
        return evidenciaObservacionDAO.findAll();
    }

    @Override
    public EvidenciaObservacion encontrarEvidenciaObservacion(Long id) {
        Optional<EvidenciaObservacion> evidenciaObservacion = evidenciaObservacionDAO.findById(id);
        if (evidenciaObservacion.isPresent()) {
            return evidenciaObservacion.get();
        }else{
            return new EvidenciaObservacion();
        }
    }

    @Override
    public EvidenciaObservacion encontrarEvidenciaObservacionByEvidencia(Long id_evidencia) {
        Optional<EvidenciaObservacion> evidenciaObservacion = evidenciaObservacionDAO.findByEvidenciaId(id_evidencia);
        if (evidenciaObservacion.isPresent()) {
            return evidenciaObservacion.get();
        }else{
            return new EvidenciaObservacion();
        }
    }

    @Override
    public EvidenciaObservacion actualizarEvidenciaObservacion(EvidenciaObservacion EvidenciaObservacion) {
        if (!Objects.isNull(EvidenciaObservacion.getId())) {
            return evidenciaObservacionDAO.save(EvidenciaObservacion);
        }
        throw new EntidadNoExisteException(
                String.format("No se proveyó un identificador en el objeto.", EvidenciaObservacion.getId()));
    }

    @Override
    public EvidenciaObservacion encontrarEvidenciaObservacionByEvidenciaAndTipoActor(Long id_evidencia,
            Long id_tipo_actor) {
        Optional<EvidenciaObservacion> evidenciaObservacion = evidenciaObservacionDAO.findTopByEvidenciaIdAndObservacionTipoActorIdOrderByFechaCreacionDesc(id_evidencia, id_tipo_actor);
        if (evidenciaObservacion.isPresent()) {
            return evidenciaObservacion.get();
        }else{
            return new EvidenciaObservacion();
        }
    }

    @Override
    public List<EvidenciaObservacion> ListarEvidenciaObservacionByEvidencia(Long id_evidencia, Long id_tipo_actor) {
        return evidenciaObservacionDAO.findByEvidenciaIdAndObservacionTipoActorIdOrderByFechaCreacionDesc(id_evidencia, id_tipo_actor);
    }


    
}
