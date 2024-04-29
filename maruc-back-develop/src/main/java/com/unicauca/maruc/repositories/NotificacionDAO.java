package com.unicauca.maruc.repositories;

import com.unicauca.maruc.domain.model.Notificacion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotificacionDAO  extends PagingAndSortingRepository<Notificacion, Long> {

    Page<Notificacion> findByUsuarioIdOrderByFechaCreacionDesc(Pageable page,long user_id);

    Integer countByUsuarioIdAndLeida(long user_id, boolean b);
    
}
