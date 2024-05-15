package com.unicauca.maruc.dto.usuarios.request;

import javax.validation.constraints.NotNull;

import com.unicauca.maruc.util.validation.EqualField;

import lombok.Data;

@Data
@EqualField(baseField = "password", matchField = "verificacionPassword", message = "{Usuario.password.EqualField}")
public class ActualizarPassword {
    @NotNull(message = "{Usuario.password.NotNull}")
    private String password;
    @NotNull(message = "{Usuario.password.NotNull}")
    private String verificacionPassword;
    @NotNull(message = "{Usuario.uuid.NotNull}")
    private String uuid;
}
