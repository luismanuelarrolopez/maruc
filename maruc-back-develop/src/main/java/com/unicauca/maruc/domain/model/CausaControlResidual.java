package com.unicauca.maruc.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "causa_controlresidual")
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
public class CausaControlResidual implements Serializable {

    @EmbeddedId
    private CausaControlResidualId causaControlResidualId;

    @ManyToOne
    @MapsId("causaId")
    @JoinColumn(name = "causa_id")
    private Causa causa;

    @ManyToOne
    @MapsId("controlResidualId")
    @JoinColumn(name = "control_residual_id")
    private ControlResidual controlResidual;
}
