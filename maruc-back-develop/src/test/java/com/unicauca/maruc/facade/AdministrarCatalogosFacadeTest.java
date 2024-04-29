package com.unicauca.maruc.facade;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import com.unicauca.maruc.domain.model.Actividad;
import com.unicauca.maruc.domain.model.Consecuencia;
import com.unicauca.maruc.domain.model.OpcionConsecuencia;
import com.unicauca.maruc.domain.model.Rol;
import com.unicauca.maruc.domain.model.TipoActor;
import com.unicauca.maruc.domain.model.TipoAfectacion;
import com.unicauca.maruc.domain.model.TipoObservacion;
import com.unicauca.maruc.domain.model.TipoProceso;
import com.unicauca.maruc.domain.model.enums.ERol;
import com.unicauca.maruc.dto.catalogos.CatalogoConsecuenciaDTO;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaDTO;
import com.unicauca.maruc.exceptions.CodigoError;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.facade.impl.AdministrarCatalogosDefaultFacade;
import com.unicauca.maruc.service.ActividadService;
import com.unicauca.maruc.service.ConsecuenciaService;
import com.unicauca.maruc.service.OpcionConsecuenciaService;
import com.unicauca.maruc.service.RolService;
import com.unicauca.maruc.service.TipoActorService;
import com.unicauca.maruc.service.TipoAfectacionService;
import com.unicauca.maruc.service.TipoObservacionService;
import com.unicauca.maruc.service.TipoProcesoService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

@SpringBootTest
class AdministrarCatalogosFacadeTest {

  @Mock
  private ActividadService actividadService;
  @Mock
  private ConsecuenciaService consecuenciaService;
  @Mock
  private OpcionConsecuenciaService opcionConsecuenciaService;
  @Mock
  private TipoAfectacionService tipoAfectacionService;
  @Mock
  private TipoProcesoService tipoProcesoService;
  @Mock
  private TipoActorService tipoActorService;
  @Mock
  private TipoObservacionService tipoObservacionService;
  @Mock
  private RolService rolService;

  private Actividad actividad;
  private Consecuencia consecuencia;
  @InjectMocks
  private AdministrarCatalogosDefaultFacade administrarCatalogosFacade;

  @BeforeEach
  public void initMock() {
    MockitoAnnotations.openMocks(this);
    actividad = Actividad.builder()
        .id(1l)
        .nombre("Actividad")
        .frecuenciaInvertida(false)
        .build();
    consecuencia = Consecuencia.builder()
        .id(1l)
        .descripcion("Descripcion")
        .build();
  }

  @Test
  @DisplayName("Registrar nueva actividad al cátalogo")
  void registrarActividad() {
    // Arrange
    Actividad actividad = new Actividad();
    // Act
    when(actividadService.guardar(actividad)).thenReturn(new Actividad(1l));
    actividad = administrarCatalogosFacade.registrarActividad(actividad);
    // Assert
    MatcherAssert.assertThat("se guardo una nueva actividad con id 1", actividad.getId(),
        equalTo(1l));
  }

  @Test
  void givenEmptyListActividades_WhenListarActividades_ThenReturnActividades() {
    // Arrange
    List<Actividad> actividades = Collections.emptyList();
    // Act
    when(actividadService.fetchActividades(0, 10)).thenReturn(new PageImpl<>(actividades));
    // Assert
    MatcherAssert.assertThat(administrarCatalogosFacade.listarActividades(0, 10).getSize(),
        equalTo(0));
  }

  @Test()
  void givenIdActividadAndActividad_WhenEditarActividad_ThenReturnNonEmptyActividad() {
    // given
    given(actividadService.buscarPorId(actividad.getId()))
        .willReturn(Optional.of(actividad));
    given(actividadService.actualizar(actividad))
        .willReturn(actividad);
    // when
    Actividad actividadGuardada = administrarCatalogosFacade.editarActividad(actividad.getId(),
        actividad);
    // then
    MatcherAssert.assertThat(actividadGuardada, is(notNullValue()));
  }

  @Test
  void givenIdActividad_WhenBuscarActividad_ThenThrowEntityNotFountException()
      throws EntityNotFountException {
    given(actividadService.buscarPorId(actividad.getId()))
        .willReturn(Optional.empty());
    EntityNotFountException exception = assertThrows(EntityNotFountException.class,
        () -> administrarCatalogosFacade.editarActividad(
            actividad.getId(), actividad));
    MatcherAssert.assertThat(exception.getCodigoError(),
        equalTo(CodigoError.ENTIDAD_NO_ENCONTRADA));
  }

  @Test
  void givenIdActividad_WhenEliminaActividad_ThenNothing() {
    // Given
    Long idActividad = 1l;
    given(actividadService.buscarPorId(idActividad))
        .willReturn(Optional.of(actividad));
    willDoNothing().given(actividadService).eliminar(actividad);
    // When
    administrarCatalogosFacade.eliminarActividad(idActividad);
    // Then
    verify(actividadService, times(1)).eliminar(actividad);
  }

  @Test
  void registrarConsecuencia() {
    // Arrange
    final Consecuencia consecuencia = new Consecuencia();
    final Set<OpcionConsecuencia> opciones = Set.of(new OpcionConsecuencia());
    consecuencia.setOpciones(opciones);
    // Act
    when(consecuenciaService.registrarConsecuencia(consecuencia)).thenReturn(
        Consecuencia.builder().id(1l).build());
    doAnswer((args) -> {
      return args.getArguments();
    }).when(opcionConsecuenciaService).guardarTodo(opciones);
    opcionConsecuenciaService.guardarTodo(opciones);
    final Consecuencia resultado = administrarCatalogosFacade.registrarConsecuencia(consecuencia);
    // Assert
    MatcherAssert.assertThat("Guardó la consecuencia", resultado.getId(), equalTo(1l));
  }

  @Test
  void givenIdConsecuencia_WhenBuscarConsecuenia_ThenThrowEntityNotFountException()
      throws EntityNotFountException {
    given(consecuenciaService.buscarConsecuencia(consecuencia.getId()))
        .willReturn(Optional.empty());
    EntityNotFountException exception = assertThrows(EntityNotFountException.class,
        () -> administrarCatalogosFacade.buscarConsecuencia(
            consecuencia.getId()));
    MatcherAssert.assertThat(exception.getCodigoError(),
        equalTo(CodigoError.ENTIDAD_NO_ENCONTRADA));
  }

  @Test
  @Disabled
  void givenConsecuencia_WhenEditarrConsecuencia_ThenReturnConsecuenciaObject() {
    given(consecuenciaService.buscarConsecuencia(consecuencia.getId()))
        .willReturn(Optional.of(consecuencia));
    given(consecuenciaService.actualizarConsecuencia(consecuencia))
        .willReturn(consecuencia);

    Consecuencia result = administrarCatalogosFacade.editarConsecuencia(
        CatalogoConsecuenciaDTO.builder()
            .id(1l)
            .tipoCampo("texto")
            .listaOpciones(List.of(
                OpcionConsecuenciaDTO.builder()
                    .id(1l)
                    .puntaje(2)
                    .descripcion("Opción consecuencia")
                    .build()
            )).build());

    MatcherAssert.assertThat(result, is(notNullValue()));
    MatcherAssert.assertThat(result.getOpciones().size(), equalTo(1));
  }

  @Test
  void givenEmptyListConsecuencias_WhenListarConsecuencias_ThenReturnEmptyList() {
    given(consecuenciaService.listarConsecuencias(0, 10))
        .willReturn(new PageImpl<>(Collections.emptyList()));

    Page<CatalogoConsecuenciaDTO> result = administrarCatalogosFacade.listarConsecuencias(0, 10);

    MatcherAssert.assertThat(result.getSize(), equalTo(0));
  }

  @Test
  void givenIdConsecuencia_WhenEliminarConsecuencia_ThenNothing() {
    // Given
    given(consecuenciaService.buscarConsecuencia(consecuencia.getId()))
        .willReturn(Optional.of(consecuencia));
    given(consecuenciaService.eliminar(consecuencia)).willReturn(consecuencia.getId());
    // When
    Long idConsecuenciaEliminada = administrarCatalogosFacade.eliminarConsecuencia(
        consecuencia.getId());
    // Then
    MatcherAssert.assertThat(idConsecuenciaEliminada, equalTo(consecuencia.getId()));
  }

  @Test
  void givenEmptyListTipoAfectacion_WhenBuscarTiposAfectacion_ThenReturnEmptyList() {
    given(tipoAfectacionService.buscarTodos()).willReturn(Collections.emptyList());
    List<TipoAfectacion> result = administrarCatalogosFacade.buscarTiposAfectacion();
    MatcherAssert.assertThat(result.size(), equalTo(0));
  }

  @Test
  void givenEmptyListTipoProceso_WhenListaTipoProceso_ThenReturnEmptyList() {
    given(tipoProcesoService.findAll()).willReturn(Collections.emptyList());
    List<TipoProceso> result = administrarCatalogosFacade.listarTipoProceso();
    MatcherAssert.assertThat(result.size(), equalTo(0));
  }

  @Test
  void givenCodigoTipoActor_WhenBuscarActorByCodigo_ThenReturnTipoActorObject() {
    TipoActor tipoActor = new TipoActor();
    tipoActor.setCodigo("ACTOR");
    given(tipoActorService.findByCodigo(anyString()))
        .willReturn(tipoActor);

    TipoActor result = administrarCatalogosFacade.buscarTipoActorByCodigo(anyString());

    MatcherAssert.assertThat(result, is(notNullValue()));
  }

  @Test
  void givenCodigoTipObservacion_WhenBuscarTipoObsevacionByCodigo_ThenReturnTipoObservacionObject() {
    TipoObservacion tipoObservacion = new TipoObservacion();
    tipoObservacion.setCodigo("OBSERVACION"
        + "");
    given(tipoObservacionService.findByCodigo(anyString()))
        .willReturn(tipoObservacion);

    TipoObservacion result = administrarCatalogosFacade.buscarTipoObservacionByCodigo(anyString());

    MatcherAssert.assertThat(result, is(notNullValue()));
  }

  @Test
  void givenCodigoRol_WhenSeleccionarRolByCodigo_ThenReturnRolObject() {
    given(rolService.findByCodigo(ERol.OCI)).willReturn(new Rol());

    Rol result = administrarCatalogosFacade.SeleccionarRolByCodigo(ERol.OCI);

    MatcherAssert.assertThat(result, notNullValue());
  }
}