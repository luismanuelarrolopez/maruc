package com.unicauca.maruc.dto.riesgos;

public class ConsecuenciaDTO {

  private String descripcion;
  public String getDescripcion() {
    return descripcion;
  }
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  private TipoAfectacionDTO tipoAfectacionDTO;
  public TipoAfectacionDTO getTipoAfectacionDTO() {
    return tipoAfectacionDTO;
  }
  public void setTipoAfectacionDTO(TipoAfectacionDTO tipoAfectacionDTO) {
    this.tipoAfectacionDTO = tipoAfectacionDTO;
  }
}
