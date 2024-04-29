package com.unicauca.maruc.util;

import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.domain.model.TipoRiesgo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("singleton")
@Component
@Slf4j
public class CacheCatalogo implements Serializable {

  private final Map<String, Class> mapaCatalogos;

  public CacheCatalogo() {
    log.info("Inicializando cache de catalogos");
    mapaCatalogos = new HashMap<>();
    mapaCatalogos.put("tipoProceso", TipoProceso.class);
    mapaCatalogos.put("tipoRiesgo", TipoRiesgo.class);
  }

  public Class buscarCatalogoClass(final String key) {
    return mapaCatalogos.get(key);
  }
}
