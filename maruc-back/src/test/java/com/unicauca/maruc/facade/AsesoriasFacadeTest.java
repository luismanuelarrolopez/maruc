package com.unicauca.maruc.facade;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;

import com.unicauca.maruc.domain.model.Asesoria;
import com.unicauca.maruc.dto.GuardarAsesoriaDTO;
import com.unicauca.maruc.exceptions.CodigoError;
import com.unicauca.maruc.exceptions.EntidadNoExisteException;
import com.unicauca.maruc.facade.impl.AsesoriasFacadeImpl;
import com.unicauca.maruc.service.AsesoriaService;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@SpringBootTest
class AsesoriasFacadeTest {

  @Mock
  private AsesoriaService asesoriaService;
  @InjectMocks
  private AsesoriasFacadeImpl asesoriasFacade;

  private Asesoria asesoria;

  @BeforeEach
  public void initialize() {
    MockitoAnnotations.openMocks(this);
    asesoria = new Asesoria();
    asesoria.setId(1l);
  }

  @Test
  void givenOficinaAsesora_whenListarAsesoria_ThenReuturnEmptyList() {
    given(asesoriaService.listarAsesorias(0, 10, "Asesoria"))
        .willReturn(new PageImpl<>(Collections.emptyList()));

    Page<Asesoria> result = asesoriasFacade.listarAsesoria(0, 10, "Asesoria");

    MatcherAssert.assertThat(result.isEmpty(), equalTo(Boolean.TRUE));
  }

  @Test
  void givenGuardarAsesoriaDTO_WhenGuardarAsesoria_ThenReturnAsesoriaObject() {
    given(asesoriaService.guardar(asesoria))
        .willReturn(asesoria);

    asesoriasFacade.guardarAsesoria(new GuardarAsesoriaDTO());

    MatcherAssert.assertThat(asesoria, notNullValue());
  }

  @Test
  void givenFechaAgendaIdAsesoria_WhenAgendarAsesoria_ThenNothing() {
    Long idAsesoria = 1l;
    Date fechaAgenda = Date.from(Instant.now());

    given(asesoriaService.buscarPorId(idAsesoria))
        .willReturn(Optional.of(asesoria));
    given(asesoriaService.actualizar(asesoria))
        .willReturn(asesoria);

    asesoriasFacade.agendarAsesoria(fechaAgenda, idAsesoria);

    verify(asesoriaService, times(1)).buscarPorId(idAsesoria);
    verify(asesoriaService, times(1)).actualizar(asesoria);
  }

  @Test
  void givenFechaAgendaIdAsesoriaNoValido_WhenAgendarAsesoria_ThenThowEntidadNoExisteException() {
    Long idAsesoria = 0l;
    Date fechaAgenda = Date.from(Instant.now());

    given(asesoriaService.buscarPorId(idAsesoria))
        .willReturn(Optional.empty());

    EntidadNoExisteException exception = assertThrows(EntidadNoExisteException.class,
        () -> asesoriasFacade.agendarAsesoria(fechaAgenda, idAsesoria));

    MatcherAssert.assertThat(exception.getCodigo(),
        equalTo(CodigoError.ENTIDAD_NO_ENCONTRADA.getCodigo()));
  }
}