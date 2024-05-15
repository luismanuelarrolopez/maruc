package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuardarConsecuenciasDTO implements Serializable {

	private static final long serialVersionUID = -8191459903473893635L;
	private Long idRiesgo;
	private Long idOpcionConsecuencia;
}
