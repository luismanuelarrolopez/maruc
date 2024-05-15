package com.unicauca.maruc.dto.riesgos;

import java.io.Serializable;
import java.util.List;
import com.unicauca.maruc.domain.model.ControlResidual;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.domain.model.TipoRiesgo;
import com.unicauca.maruc.domain.model.Monitoreo_Evaluacion.RiesgoObservacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiesgoDTO implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 2485101973695379795L;
  private Riesgo riesgo;
  private List<ControlResidual> controles;
  private RiesgoInherenteDTO riesgo_inherente;
  private RiesgoResidualDTO riesgo_residual;
  private Boolean cumplimiento;
  private Boolean cumplimiento_observaciones_evidencia;
  private Boolean cumplimiento_observaciones_riesgo;

  public static RiesgoDTO convert(Riesgo riesgo) {
    return new RiesgoDTO(riesgo,null, null, null, false, false, false);
  }
  
  public String ObservacionesRiesgoString() {
    String observacionesstr = "";
    List<RiesgoObservacion> observaciones = this.getRiesgo().getRiesgoObservacion();
    for (RiesgoObservacion observacion : observaciones) {
      if (observacion.getObservacion().getObservacion() != null) {
        observacionesstr += observacion.getObservacion().getObservacion() + "\n\n";
      }
    }
    if (observacionesstr.length() == 0)
      observacionesstr = "---";
    return observacionesstr;
  }

  public String ControlesString() {
    String controlesstr = "";
    for (ControlResidual control : this.controles) {
      if (control.getNombre() != null) {
        controlesstr += control.getNombre() + "\n\n";
      }
    }
    if (controlesstr.length() == 0)
      controlesstr = "---";
    return controlesstr;
  }

  public String ResponsablesString() {
    String responsablesstr = "";
    for (ControlResidual control : this.controles) {
      if (control.getResponsable() != null) {
        responsablesstr += control.getResponsable() + "\n\n";
      }
    }
    if (responsablesstr.length() == 0)
      responsablesstr = "---";
    return responsablesstr;
  }

  public String PeriodicidadesString() {
    String periodicidadesstr = "";
    for (ControlResidual control : this.controles) {
      if (control.getPeriodicidad() != null) {
        periodicidadesstr += control.getPeriodicidad().getNombre() + "\n\n";
      }
    }
    if (periodicidadesstr.length() == 0)
      periodicidadesstr = "---";
    return periodicidadesstr;
  }

  public String TipoControlString(){
    String tipocontrolstr = "";
    for (ControlResidual control : this.controles) {
      if (control.getTipoControl() != null) {
        tipocontrolstr += control.getTipoControl().getNombre() + "\n\n";
      }
    }
    if (tipocontrolstr.length() == 0)
      tipocontrolstr = "---";
    return tipocontrolstr;
  }

  public String EvidenciaString() {
    String evidenciastr = "";
    for (ControlResidual control : this.controles) {
      if (control.getEvidencia() != null) {
        evidenciastr += control.getEvidencia().getEvidencia() + "\n\n";
      }
    }
    if (evidenciastr.length() == 0)
      evidenciastr = "---";
    return evidenciastr;
  }

  public String TipoProcesoString() {
    TipoProceso tp = this.getRiesgo().getTipoProceso();
    return tp != null && tp.getNombre() != null ? tp.getNombre() : "----";
  }

  public String TipoRiesgoString() {
    TipoRiesgo tp = this.getRiesgo().getTipoRiesgo();
    return tp.getNombre() != null ? tp.getNombre() : "----";
  }

  public String NivelRiesgoInheString() {
    RiesgoInherenteDTO tp = this.getRiesgo_inherente();
    String value = "";
    if (tp.getValoracion() > 0 && tp.getValoracion() <= 3)
      value = "Bajo";
    if (tp.getValoracion() > 3 && tp.getValoracion() <= 9)
      value = "Medio";
    if (tp.getValoracion() > 9 && tp.getValoracion() <= 24)
      value = "Alto";
    if (tp.getValoracion() > 24 && tp.getValoracion() <= 100)
      value = "Extremo";
    return value;
  }

  public String NivelRiesgoResidualString() {
    RiesgoResidualDTO rr = this.getRiesgo_residual();
    return rr.getNivel() != null ? rr.getNivel() : "----";
  }
}
