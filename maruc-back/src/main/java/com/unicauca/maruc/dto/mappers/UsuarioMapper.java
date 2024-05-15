package com.unicauca.maruc.dto.mappers;

import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.dto.usuarios.request.UsuarioRequest;
import com.unicauca.maruc.dto.usuarios.response.PerfilUsuarioResponse;

public final class UsuarioMapper {

    public static PerfilUsuarioResponse mapToPerfilUsuario(final Usuario usuario) {
        return PerfilUsuarioResponse.builder()
                .email(usuario.getEmail())
                .primerNombre(findStringInSplitBy(0, usuario.getNombres()))
                .segundoNombre(findStringInSplitBy(1, usuario.getNombres()))
                .primerApellido(findStringInSplitBy(0, usuario.getApellidos()))
                .segundoApellido(findStringInSplitBy(1, usuario.getApellidos()))
                .build();
    }

    private static String findStringInSplitBy(final int position, final String value) {
        final String[] splittedValue = value.split(" ");
        if (splittedValue.length - 1 >= position) {
            return splittedValue[position];
        }
        return "";
    }

    public static UsuarioRequest mapFrom(final Usuario usuario) {
        return UsuarioRequest.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nombres(usuario.getNombres())
                .apellidos(usuario.getApellidos())
                .idRol(usuario.getRol().getId())
                .idDependencia(usuario.getDependencia().getId())
                .build();
    }
}
