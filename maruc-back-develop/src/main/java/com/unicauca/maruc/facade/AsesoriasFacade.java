package com.unicauca.maruc.facade;

import java.util.Date;
import org.springframework.data.domain.Page;
import com.unicauca.maruc.domain.model.Asesoria;
import com.unicauca.maruc.dto.GuardarAsesoriaDTO;

public interface AsesoriasFacade {

  Page<Asesoria> listarAsesoria(int pagina, int cantidad, String oficinaAsesora);

  Asesoria guardarAsesoria(GuardarAsesoriaDTO asesoriaDTO);

  void agendarAsesoria(Date fechaAgenda, Long idAsesoria);
}
