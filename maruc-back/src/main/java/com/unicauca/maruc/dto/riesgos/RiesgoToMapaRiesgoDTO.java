package com.unicauca.maruc.dto.riesgos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiesgoToMapaRiesgoDTO {
	
	public Integer index; 
	public String proceso;
	public String tipo_riesgo;
	public String riesgo;
	public String causa;
	public String consecuencia;
	public String nivel_riesgo_inherente;
	public String control_existente;
	public String nivel_riesgo_residual;
	public String tratamiento;
	public String control;
	public String responsable;
	public String periodicidad;
	public String tipo_control;
	public String evidencia;
}
