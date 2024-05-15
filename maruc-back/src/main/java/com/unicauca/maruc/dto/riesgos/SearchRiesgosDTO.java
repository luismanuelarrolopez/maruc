package com.unicauca.maruc.dto.riesgos;
import java.io.Serializable;
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
public class SearchRiesgosDTO implements Serializable{
    
    private static final long serialVersionUID = 2485101973695379795L;
    private Search search;
    private int page;
    private int size;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Search {
        private String key;
        private String proceso;
        private String tipo_riesgo;
        private long riesgo_residual;
        private long riesgo_inherente;
        private Boolean all;
    }
}
