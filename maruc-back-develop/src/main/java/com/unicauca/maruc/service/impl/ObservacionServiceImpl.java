package com.unicauca.maruc.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.Observacion;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.exceptions.EntidadYaExisteException;
import com.unicauca.maruc.repositories.ObservacionDAO;
import com.unicauca.maruc.service.ObservacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservacionServiceImpl implements ObservacionService {

    @Autowired
    private ObservacionDAO ObservacionDAO; 

    @Override
    public Observacion guardarObservacion(Observacion Observacion) {
        if (Objects.isNull(Observacion.getId())) {
            return ObservacionDAO.save(Observacion);
        }
        throw new EntidadYaExisteException(
                String.format("La  con id %s ya se encuentra registrado.", Observacion.getId()));
    }

    @Override
    public List<Observacion> listarObservacion() {
        return ObservacionDAO.findAll();
    }

    @Override
    public Observacion encontrarObservacion(Long id) {
        Optional<Observacion> Observacion = ObservacionDAO.findById(id);
        if (Observacion.isPresent()) {
            return Observacion.get();
        }else{
            throw new EntidadNoExisteException(
                    String.format("La  con id %s no se encuentra registrado.", id));
        }
    }


    @Override
    public Observacion actualizarObservacion(Observacion Observacion) {
        if (!Objects.isNull(Observacion.getId())) {
            return ObservacionDAO.save(Observacion);
        }
        throw new EntidadNoExisteException(
                String.format("No se proveyó un identificador en el objeto.", Observacion.getId()));
    }


    
}
