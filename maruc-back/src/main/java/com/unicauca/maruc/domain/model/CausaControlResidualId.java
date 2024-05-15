package com.unicauca.maruc.domain.model;

import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CausaControlResidualId implements Serializable {

    @Column(name = "causa_id")
    public Long causaId;

    @Column(name = "control_residual_id")
    public Long controlResidualId;
}
