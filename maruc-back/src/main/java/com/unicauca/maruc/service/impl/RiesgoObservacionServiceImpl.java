package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.RiesgoObservacion;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.exceptions.EntidadYaExisteException;
import com.unicauca.maruc.repositories.RiesgoObservacionDAO;
import com.unicauca.maruc.service.RiesgoObservacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiesgoObservacionServiceImpl implements RiesgoObservacionService {

    @Autowired
    private RiesgoObservacionDAO RiesgoObservacionDAO; 

    @Override
    public RiesgoObservacion guardarRiesgoObservacion(RiesgoObservacion RiesgoObservacion) {
        if (Objects.isNull(RiesgoObservacion.getId())) {
            return RiesgoObservacionDAO.save(RiesgoObservacion);
        }
        throw new EntidadYaExisteException(
                String.format("La Riesgo con id %s ya se encuentra registrado.", RiesgoObservacion.getId()));
    }

    @Override
    public List<RiesgoObservacion> listarRiesgoObservacion() {
        return RiesgoObservacionDAO.findAll();
    }

    @Override
    public RiesgoObservacion encontrarRiesgoObservacion(Long id) {
        Optional<RiesgoObservacion> RiesgoObservacion = RiesgoObservacionDAO.findById(id);
        if (RiesgoObservacion.isPresent()) {
            return RiesgoObservacion.get();
        }else{
            return new RiesgoObservacion();
        }
    }

    @Override
    public RiesgoObservacion encontrarRiesgoObservacionByRiesgo(Long id_Riesgo) {
        Optional<RiesgoObservacion> RiesgoObservacion = RiesgoObservacionDAO.findByRiesgoId(id_Riesgo);
        if (RiesgoObservacion.isPresent()) {
            return RiesgoObservacion.get();
        }else{
            return new RiesgoObservacion();
        }
    }

    @Override
    public RiesgoObservacion actualizarRiesgoObservacion(RiesgoObservacion RiesgoObservacion) {
        if (!Objects.isNull(RiesgoObservacion.getId())) {
            return RiesgoObservacionDAO.save(RiesgoObservacion);
        }
        throw new EntidadNoExisteException(
                String.format("No se proveyó un identificador en el objeto.", RiesgoObservacion.getId()));
    }

    @Override
    public RiesgoObservacion encontrarRiesgoObservacionByRiesgoAndTipoActor(Long id_Riesgo,
            Long id_tipo_actor) {
        Optional<RiesgoObservacion> RiesgoObservacion = RiesgoObservacionDAO.findTopByRiesgoIdAndObservacionTipoActorIdOrderByFechaCreacionDesc(id_Riesgo, id_tipo_actor);
        if (RiesgoObservacion.isPresent()) {
            return RiesgoObservacion.get();
        }else{
            return new RiesgoObservacion();
        }
    }

    @Override
    public List<RiesgoObservacion> ListarRiesgoObservacionByRiesgo(Long id_Riesgo, Long id_tipo_actor) {
        return RiesgoObservacionDAO.findByRiesgoIdAndObservacionTipoActorIdOrderByFechaCreacionDesc(id_Riesgo, id_tipo_actor);
    }


    
}
