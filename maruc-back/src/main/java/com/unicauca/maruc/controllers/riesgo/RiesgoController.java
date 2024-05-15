package com.unicauca.maruc.controllers.riesgo;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.unicauca.maruc.domain.model.Causa;
import com.unicauca.maruc.domain.model.Control;
import com.unicauca.maruc.domain.model.Riesgo;
import com.unicauca.maruc.domain.model.TipoAfectacion;
import com.unicauca.maruc.domain.model.TipoControl;
import com.unicauca.maruc.domain.model.Usuario;
import com.unicauca.maruc.domain.model.enums.ERol;
import com.unicauca.maruc.dto.riesgos.CausaDTO;
import com.unicauca.maruc.dto.riesgos.IndicadorDTO;
import com.unicauca.maruc.dto.riesgos.InformacionBasicaRiesgoDTO;
import com.unicauca.maruc.dto.riesgos.NotificarMaterializacionDTO;
import com.unicauca.maruc.dto.riesgos.OpcionConsecuenciaRiesgoDTO;
import com.unicauca.maruc.dto.riesgos.ReporteRiesgosDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoToFileDTO;
import com.unicauca.maruc.dto.riesgos.RiesgoToMapaRiesgoDTO;
import com.unicauca.maruc.dto.riesgos.SearchRiesgosDTO;
import com.unicauca.maruc.exceptions.EntityNotFountException;
import com.unicauca.maruc.facade.RiesgoManagerFacade;
import com.unicauca.maruc.facade.impl.AdministrarUsuarioDefaultFacade;
import com.unicauca.maruc.service.EmailService;
import com.unicauca.maruc.service.impl.report.ReportReader;
import com.unicauca.maruc.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
@RestController
@RequestMapping("/riesgos")
@CrossOrigin(origins = "*")
public class RiesgoController {

  @Autowired
  private RiesgoManagerFacade riesgoManagerFacade;
  @Autowired
  private AdministrarUsuarioDefaultFacade usuarioFacade;
  @Autowired
  private EmailService emailService;

  @GetMapping("tipos-afectacion")
  public List<TipoAfectacion> buscarConConsecuencias() {
    return riesgoManagerFacade.buscarConConsecuencias();
  }

  @GetMapping("/{idRiesgo}")
  public RiesgoDTO cargarRiesgo(@PathVariable(name = "idRiesgo") final Long idRiesgo) {
    final RiesgoDTO dto = riesgoManagerFacade.cargarRiesgo(idRiesgo);
    return dto;
  }

  @PostMapping("filter")
  public Page<RiesgoDTO> filter(@RequestBody final SearchRiesgosDTO search) {
    System.out.println(search);
    Page<RiesgoDTO> result = riesgoManagerFacade.filter(search.getSearch(), search.getPage(),
        search.getSize());
    return result;
  }

  @GetMapping(params = {"id_proceso", "page", "size"})
  public Page<Riesgo> filterByProceso(@RequestParam(name = "id_proceso") final long id_proceso,
      @RequestParam(name = "page") final int page, @RequestParam(name = "size") final int size) {
    return riesgoManagerFacade.filterByProceso(id_proceso, page, size);
  }

  // @GetMapping(params = {"riesgo_inherente", "page", "size"})
  // public Page<Riesgo> filterByRiesgoInherente(
  // @RequestParam(name = "riesgo_inherente") final Integer riesgo_inherente,
  // @RequestParam(name = "page") final int page, @RequestParam(name = "size") final int size) {
  // return riesgoManagerFacade.filterByRiesgoInherente(riesgo_inherente, page, size);
  // }

  // @GetMapping(params = {"riesgo_residual", "page", "size"})
  // public Page<Riesgo> filterByRiesgoResidual(
  // @RequestParam(name = "riesgo_residual") final Integer riesgo_residual,
  // @RequestParam(name = "page") final int page, @RequestParam(name = "size") final int size) {
  // return riesgoManagerFacade.filterByRiesgoResidual(riesgo_residual, page, size);
  // }

  @GetMapping(params = {"id_tipo_riesgo", "page", "size"})
  public Page<Riesgo> filterByTipoRiesgo(
      @RequestParam(name = "id_tipo_riesgo") final long id_tipo_riesgo,
      @RequestParam(name = "page") final int page, @RequestParam(name = "size") final int size) {
    return riesgoManagerFacade.filterByTipoRiesgo(id_tipo_riesgo, page, size);
  }

  @PostMapping("/info-inicial")
  @Operation(summary = "Guarda la información inicial del registro de un nuevo riesgo")
  public Riesgo guardarInformacionInicialRiesgo(
      @RequestBody final InformacionBasicaRiesgoDTO informacionInicialDTO) {
    return riesgoManagerFacade.guardarInformacionInicial(informacionInicialDTO);
  }

  @PutMapping("/info-inicial")
  public Riesgo actualiarInformacionInicialRiesgo(
      @RequestBody final InformacionBasicaRiesgoDTO informacionBasicaRiesgoDTO)
      throws EntityNotFountException {
    return riesgoManagerFacade.actualizarInformacionBasica(informacionBasicaRiesgoDTO);
  }

  @PostMapping("/causas")
  @Operation(summary = "Guarda las causas asosiadas al riesgo")
  public void guardarCausas(@RequestBody final List<CausaDTO> causas) {
    riesgoManagerFacade.guardarCausas(causas);
  }

  @GetMapping("/causas/{idRiesgo}/criticas")
  public ResponseEntity<List<CausaDTO>> buscarCausasCriticas(
      @PathVariable("idRiesgo") final Long idRiesgo) {
    return ResponseUtils.ok(riesgoManagerFacade.buscarCriticas(idRiesgo));
  }

  @GetMapping("/causas/{idRiesgo}")
  public ResponseEntity<Object> listarCausas(
      @Valid @Positive @PathVariable("idRiesgo") final Long id) {
    return ResponseUtils.ok(riesgoManagerFacade.listarCausas(id));
  }

  @PostMapping("/causa")
  public Causa agregarCausa(@Valid @RequestBody final CausaDTO causaDTO) {
    return riesgoManagerFacade.guardarCausa(causaDTO);
  }

  @PutMapping("/causa")
  public Causa actualizarCausa(@Valid @RequestBody final CausaDTO causa)
      throws EntityNotFountException {
    return riesgoManagerFacade.actualizarCausa(causa);
  }

  @DeleteMapping("/causa/{idCausa}")
  public ResponseEntity<Object> eliminarCausa(
      @Valid @Positive @PathVariable("idCausa") final Long idCausa) {
    return ResponseUtils.ok(riesgoManagerFacade.eliminarCausa(idCausa));
  }

  @PutMapping("/estado/{idRiesgo}/{codEstado}")
  public void actualizarEstado(@PathVariable("idRiesgo") final Long idRiesgo,
      @PathVariable("codEstado") final String estado) {
    this.riesgoManagerFacade.actualizarEstadoRiesgo(idRiesgo, estado);
  }

  @GetMapping("/listarRiesgoDTO")
  public Page<RiesgoDTO> listarRiesgosDTO(@RequestParam(name = "page") final int page,
      @RequestParam(name = "size") final int size, @RequestParam(name = "all") final Boolean all) {
    Page<RiesgoDTO> ret = riesgoManagerFacade.listarRiesgosDTO(page, size, all);
    return ret;
  }

  @GetMapping("/listarRiesgoLiderDTO")
  public Page<RiesgoDTO> listarRiesgoLiderDTO(@RequestParam(name = "page") final int page,
      @RequestParam(name = "size") final int size, @RequestParam(name = "all") final Boolean all, @RequestParam(name = "id_usuario") final Long id_usuario) {
    Page<RiesgoDTO> ret = riesgoManagerFacade.listarRiesgosLiderDTO(page, size, all, id_usuario);
    return ret;
  }

  @GetMapping(params = {"page", "size"})
  public Page<Riesgo> listarRiesgos(@RequestParam(name = "page") final int page,
      @RequestParam(name = "size") final int size) {
    return riesgoManagerFacade.listarRiesgos(page, size);
  }

  @GetMapping(params = {"key_search", "page", "size"})
  public Page<Riesgo> listarRiesgos(@RequestParam(name = "key_search") final String key_search,
      @RequestParam(name = "page") final int page, @RequestParam(name = "size") final int size) {
    return riesgoManagerFacade.listarByNombre(key_search, page, size);
  }

  @GetMapping("/tipos-control")
  @Operation(summary = "Busca los tipos de control configurados en el catálogo de tipos de control")
  public List<TipoControl> listarTiposControl() {
    return riesgoManagerFacade.listarTiposDeControl();
  }

  /*
   * Consecuencias
   */
  @GetMapping("/consecuencias/{idRiesgo}")
  public ResponseEntity<Object> listarConsecuencias(
      @Valid @Positive @PathVariable("idRiesgo") final Long idRiesgo) {
    return ResponseUtils.ok(riesgoManagerFacade.listarConsecuencias(idRiesgo));
  }

  @PostMapping("/consecuencia")
  public ResponseEntity<Object> guardarConsecuencia(
      @Valid @RequestBody final OpcionConsecuenciaRiesgoDTO consecuenciaRiesgoDTO) {
    return ResponseUtils.ok(riesgoManagerFacade.guardarConsecuencia(consecuenciaRiesgoDTO));
  }

  @DeleteMapping("/consecuencia/{idConsecuencia}")
  public ResponseEntity<Object> eliminarConsecuencia(
      @PathVariable("idConsecuencia") final Long id) {
    return ResponseUtils.ok(riesgoManagerFacade.eliminarConsecuencia(id));
  }

  @GetMapping(path = "ListarRiesgos/{id_lider_proceso}")
  @Operation(summary = "Obtiene una lista de todos los riesgos de un lider de proceso", parameters =

  @Parameter(name = "id_lider_proceso", description = "Identificador del lider de proceso"))
  public ResponseEntity<List<Riesgo>> ListarRiesgos(
      @PathVariable(name = "id_lider_proceso") final Long id_lider_proceso)
      throws EntityNotFountException {
    return ResponseUtils.ok(riesgoManagerFacade.listarRiesgosByLiderProceso(id_lider_proceso));
  }

  @PostMapping(value = "/notificar-materializacion")
  @Operation(summary = "Notifica la materialización de un riesgo")
  public void notificar_materializacion(@RequestBody final NotificarMaterializacionDTO data) {
    try {
      // Enviamos el correo al lider del riesgo
      final List<Usuario> usuarios = usuarioFacade.listarUsuariosByRolCodigo(ERol.OCI.toString());
      final List<Usuario> usuariosOPDI = usuarioFacade.listarUsuariosByRolCodigo(ERol.OPDI.toString());
      new Thread(() -> {        
        Riesgo riesgo = new Riesgo();
        if (data.getId_riesgo() > 0) {
          riesgo = riesgoManagerFacade.SeleccionarRiesgoPorId(data.getId_riesgo());
        }
        for (final Usuario usuario : usuarios) {
          if (!(data.getId_riesgo() > 0)) {
            emailService.sendEmail(usuario.getEmail(),
                "Notificación de materialización de un riesgo sin identificar",
                "El lider de proceso " + usuario.getNombres()
                    + " ha notificado la materialización de un riesgo sin identificar. Descripción: '"
                    + data.getDescripcion());
          } else {
            emailService.sendEmail(usuario.getEmail(),
                "Notificación de materialización de un riesgo identificado",
                "El lider de proceso " + usuario.getNombres()
                    + " ha notificado la materialización de un riesgo identificado. Riesgo: "
                    + riesgo.getNombre() + ". Descripción: '" + data.getDescripcion());
          }
        }
    }).start();

    new Thread(() -> {        
      Riesgo riesgo = new Riesgo();
      if (data.getId_riesgo() > 0) {
        riesgo = riesgoManagerFacade.SeleccionarRiesgoPorId(data.getId_riesgo());
      }
      for (final Usuario usuario : usuariosOPDI) {
        if (!(data.getId_riesgo() > 0)) {
          emailService.sendEmail(usuario.getEmail(),
              "Notificación de materialización de un riesgo sin identificar",
              "El lider de proceso " + usuario.getNombres()
                  + " ha notificado la materialización de un riesgo sin identificar. Descripción: '"
                  + data.getDescripcion());
        } else {
          emailService.sendEmail(usuario.getEmail(),
              "Notificación de materialización de un riesgo identificado",
              "El lider de proceso " + usuario.getNombres()
                  + " ha notificado la materialización de un riesgo identificado. Riesgo: "
                  + riesgo.getNombre() + ". Descripción: '" + data.getDescripcion());
        }
      }
  }).start();
    } catch (final Exception e) {
      System.out.println("Error al notificar observación");
    }
  }

  /*
   * Controles
   */
  @PutMapping("/control/{idControl}")
  public ResponseEntity<Boolean> editarControl(@PathVariable("idControl") final Long id,
      @RequestBody final Control control) {
    return ResponseUtils.ok(riesgoManagerFacade.editarControl(id, control));
  }

  @GetMapping(path = "Indicadores")
  @Operation(summary = "Obtiene los indicadores de todos los riesgos en la aplicación hasta el momento")
  public ResponseEntity<List<IndicadorDTO>> Indicadores() {
    return ResponseUtils.ok(riesgoManagerFacade.generarIndicadores());
  }

  @Operation(summary = "Obtiene el reporte de observaciones")
  @GetMapping( path = "Descargar_Reporte_Observaciones")
  public ResponseEntity<?> Descargar_Reporte_Observaciones() {
    Map<String, Object> response = new HashMap<>();
    try {

      File logo = ReportReader.getImage("logo", "observaciones");
      InputStream logoUnicauca = new FileInputStream(logo);

      File reporte = ReportReader.getReport("ReporteObservaciones", "observaciones");
      InputStream reporteInput = new FileInputStream(reporte);

      if(logoUnicauca != null && reporteInput != null){
        List<RiesgoDTO> riesgos = riesgoManagerFacade.listarRiesgos();
        List<RiesgoToFileDTO> list = new ArrayList<>();
        for (RiesgoDTO riesgo : riesgos) {
          String causas = riesgoManagerFacade.listarCausas(riesgo.getRiesgo().getId()).stream().map(c -> c.getCausa()).collect(Collectors.joining("\n\n"));
          list.add(new RiesgoToFileDTO(
              riesgo.TipoProcesoString(),
              causas,
              riesgo.getRiesgo().getNombre(),
              riesgo.ControlesString(),
              riesgo.ObservacionesRiesgoString()));
        }

        JasperReport report = (JasperReport) JRLoader.loadObject(reporte);
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(list.toArray());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ds", ds);
        parameters.put("reportImage", logoUnicauca);

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
        Path rutaArchivo = ReportReader.getPath().resolve("reporte_observaciones.pdf").toAbsolutePath();
        JasperExportManager.exportReportToPdfFile(jasperPrint, rutaArchivo.toString());

        Resource recurso = null;
        boolean banderaRutaCorrecta=true;
        try {
          recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
          banderaRutaCorrecta=false;
        }

        if(banderaRutaCorrecta==false || !recurso.exists() || !recurso.isReadable()) {
          response.put("mensaje", "Error no se pudo cargar el archivo");
          reporteInput.close();
          return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        reporteInput.close();
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
      }
      else{
        logoUnicauca.close();
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      response.put("message", "Error al obtener observacion a evidencia");
      response.put("error", e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }
  }

  @Operation(summary = "Obtiene el reporte de riesgos")
  @PostMapping( path = "Descargar_Reporte_Riesgos")
  public ResponseEntity<?> Descargar_Reporte_Riesgos(@RequestBody final ReporteRiesgosDTO data) {
    Map<String, Object> response = new HashMap<>();
    try {

      File logo = ReportReader.getImage("logo", "riesgos");
      InputStream logoUnicauca = new FileInputStream(logo);

      File reporte = ReportReader.getReport("MapaRiesgos", "riesgos");
      InputStream reporteInput = new FileInputStream(reporte);

      if(logoUnicauca != null && reporteInput != null){
        List<RiesgoDTO> riesgos = riesgoManagerFacade.listarRiesgos();
        List<RiesgoToMapaRiesgoDTO> list = new ArrayList<>();
        Integer count = 1;
        for (RiesgoDTO riesgo : riesgos) {
          String causas = riesgoManagerFacade.listarCausas(riesgo.getRiesgo().getId()).stream().map(c -> c.getCausa()).collect(Collectors.joining("\n\n"));
          String controles_existentes = riesgo.getRiesgo().getControles().stream().map(c -> c.getNombre()).collect(Collectors.joining("\n\n"));
          list.add(new RiesgoToMapaRiesgoDTO(
              count,
              riesgo.TipoProcesoString(),
              riesgo.TipoRiesgoString(),
              riesgo.getRiesgo().getNombre(),
              causas,
              riesgo.getRiesgo().getMayorConsecuencia(),
              riesgo.NivelRiesgoInheString(),
              controles_existentes,
              riesgo.NivelRiesgoResidualString(),
              riesgo.getRiesgo_residual().getTratamiento(),
              riesgo.ControlesString(),
              riesgo.ResponsablesString(),
              riesgo.PeriodicidadesString(),
              riesgo.TipoControlString(),
              riesgo.EvidenciaString()
          ));
        }

        JasperReport report = (JasperReport) JRLoader.loadObject(reporte);
        JRBeanArrayDataSource ds = new JRBeanArrayDataSource(list.toArray());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ds", ds);
        parameters.put("logoUnicauca", logoUnicauca);
        parameters.put("version", data.getVersion());
        parameters.put("vigencia", data.getVigencia());

        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
        Path rutaArchivo = ReportReader.getPath().resolve("mapa_de_riesgos.pdf").toAbsolutePath();
        // Path rutaArchivo = Paths.get("reports/riesgos").resolve("mapa_de_riesgos.pdf").toAbsolutePath();
        JasperExportManager.exportReportToPdfFile(jasperPrint, rutaArchivo.toString());

        Resource recurso = null;
        boolean banderaRutaCorrecta=true;
        try {
          recurso = new UrlResource(rutaArchivo.toUri());
        } catch (MalformedURLException e) {
          banderaRutaCorrecta=false;
        }

        if(banderaRutaCorrecta==false || !recurso.exists() || !recurso.isReadable()) {
          response.put("mensaje", "Error no se pudo cargar el archivo");
          reporteInput.close();
          return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
        reporteInput.close();
        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
      }
      else{
        logoUnicauca.close();
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      response.put("message", "Error al obtener observacion a evidencia");
      response.put("error", e.getMessage());
      return ResponseEntity.badRequest().body(response);
    }
  }


  @PutMapping("/consecuencias/{idRiesgo}")
  public ResponseEntity<Object> editarMayorConsecuencia(
      @PathVariable("idRiesgo") final Long idRiesgo, @RequestBody final String consecuencia) {
    riesgoManagerFacade.actualizarMayorConsecuencia(idRiesgo, consecuencia);
    return ResponseUtils.ok(true);
  }
}
