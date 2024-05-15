package com.unicauca.maruc.dto.riesgos;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder(toBuilder = true)
public class GuardarControlResidualRequest implements Serializable {

    @NotNull(message = "{id.NotNull}")
    private Long idRiesgoResidual;
    @NotNull(message = "{listaCausas.NotNull}")
    private List<Long> listaIdCausas;
    @NotNull(message = "{controlResidual.NotNull}")
    private ControlResidualDTO controlResidual;
    @NotNull(message = "{evidencia.NotNull}")
    private String evidencia;
}
