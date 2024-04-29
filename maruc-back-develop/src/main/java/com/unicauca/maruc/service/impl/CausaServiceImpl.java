package com.unicauca.maruc.service.impl;

import com.unicauca.maruc.domain.model.Causa;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.dto.mappers.CausaMapper;
import com.unicauca.maruc.dto.riesgos.CausaDTO;
import com.unicauca.maruc.repositories.CausaDAO;
import com.unicauca.maruc.service.CausaService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CausaServiceImpl extends DefaultBaseService<Causa> implements CausaService {

    @Autowired
    private CausaDAO causaDAO;
    @Override
    public Causa guardarCausa(final CausaDTO causaDto) {
        return causaDAO.save(getCausaDe(causaDto));
    }

    private Causa getCausaDe(final CausaDTO causaDto) {
        final Riesgo riesgo = new Riesgo();
        riesgo.setId(causaDto.getIdRiesgo());
        final Causa causa = CausaDTO.Dto_To_Entity(causaDto);
        causa.setRiesgo(riesgo);
        causa.setId(causaDto.getId());
        return causa;
    }

    @Override
    public List<CausaDTO> buscarPorRiesgo(final Riesgo riesgo) {
        return causaDAO.findAllByRiesgoOrderBySumatoriaDesc(riesgo).stream().map(CausaMapper::mapFromEntity).collect(Collectors.toList());
    }

    @Override
    public List<CausaDTO> buscarCriticas(final Long idRiesgo) {
        return causaDAO.findTop3ByRiesgoIdOrderBySumatoriaDesc(idRiesgo).stream().map(CausaMapper::mapFromEntity).toList();
    }

    @Override
    public List<CausaDTO> buscarCausasDeControlResidual(Long riesgoId, Long controlId) {
        return causaDAO.buscarCausasDeControlResidual(riesgoId, controlId).stream().map(CausaMapper::mapFromEntity).toList();
    }

    @Override
    public List<CausaDTO> buscarCausasSinControlResidual(final Long riesgoId) {
        return causaDAO.buscarCausasSinControlResidual(riesgoId).stream().map(CausaMapper::mapFromEntity).toList();
    }

    @Override
    public Set<Causa> buscarPorListaIds(List<Long> ids) {
        return buscarPorListaIds(ids);
    }
}
