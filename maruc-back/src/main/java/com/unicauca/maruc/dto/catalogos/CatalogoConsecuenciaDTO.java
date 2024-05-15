package com.unicauca.maruc.dto.catalogos;

import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaDTO;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@Setter
public class CatalogoConsecuenciaDTO implements Serializable {

  private Long id;
  @NotNull(message = "{descripcion.NotNull}")
  private String descripcion;
  @NotNull(message = "{tipoCampo.NotNull}")
  private String tipoCampo;
  @NotNull(message = "{idTipoAfectacion.NotNull}")
  private Long idTipoAfectacion;
  @NotNull(message = "{listaOpciones.NotNull}")
  private List<OpcionConsecuenciaDTO> listaOpciones;
  private String tipoAfectacion;

}
