-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: maruc
-- Source Schemata: maruc
-- Created: Thu Sep 29 01:00:23 2022
-- Workbench Version: 8.0.28
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema maruc
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `maruc` ;
CREATE SCHEMA IF NOT EXISTS `maruc` ;

-- ----------------------------------------------------------------------------
-- Table maruc.actividad_riesgo
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`actividad_riesgo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `riesgo_id` INT NULL DEFAULT NULL,
  `actividad_id` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  `frecuencia` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.actividades
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`actividades` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `frecuencia_invertida` BIT(1) NULL DEFAULT b'0',
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre` (`nombre` ASC) VISIBLE);

-- ----------------------------------------------------------------------------
-- Table maruc.asesorias
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`asesorias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tema` TEXT NULL DEFAULT NULL,
  `fecha_solicitud` TIMESTAMP NULL DEFAULT now(),
  `fecha_agendada` TIMESTAMP NULL DEFAULT NULL,
  `oficina_asesora` ENUM('opdi', 'oci') NULL DEFAULT NULL,
  `solicitante` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `solicitante` (`solicitante` ASC) VISIBLE,
  CONSTRAINT `asesorias_ibfk_1`
    FOREIGN KEY (`solicitante`)
    REFERENCES `maruc`.`usuarios` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.causa_controlresidual
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`causa_controlresidual` (
  `causa_id` INT NOT NULL,
  `control_residual_id` INT NOT NULL,
  PRIMARY KEY (`causa_id`, `control_residual_id`),
  INDEX `control_residual_id` (`control_residual_id` ASC) VISIBLE,
  CONSTRAINT `causa_controlresidual_ibfk_1`
    FOREIGN KEY (`causa_id`)
    REFERENCES `maruc`.`causas` (`id`),
  CONSTRAINT `causa_controlresidual_ibfk_2`
    FOREIGN KEY (`control_residual_id`)
    REFERENCES `maruc`.`control_residual` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.causas
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`causas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `causa` TEXT NOT NULL,
  `porque1` VARCHAR(250) NULL DEFAULT NULL,
  `porque2` VARCHAR(250) NULL DEFAULT NULL,
  `porque3` VARCHAR(250) NULL DEFAULT NULL,
  `puntaje1` INT NULL DEFAULT '0',
  `puntaje2` INT NULL DEFAULT '0',
  `puntaje3` INT NULL DEFAULT '0',
  `sumatoria` INT NULL DEFAULT '0',
  `riesgo_id` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  `causa_inicial` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `riesgo_id` (`riesgo_id` ASC) VISIBLE,
  CONSTRAINT `causas_ibfk_1`
    FOREIGN KEY (`riesgo_id`)
    REFERENCES `maruc`.`riesgos` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.consecuencia_riesgo
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`consecuencia_riesgo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `riesgo_id` INT NULL DEFAULT NULL,
  `consecuencia_id` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `riesgo_id` (`riesgo_id` ASC) VISIBLE,
  INDEX `consecuencia_id` (`consecuencia_id` ASC) VISIBLE,
  CONSTRAINT `consecuencia_riesgo_ibfk_1`
    FOREIGN KEY (`riesgo_id`)
    REFERENCES `maruc`.`riesgos` (`id`),
  CONSTRAINT `consecuencia_riesgo_ibfk_2`
    FOREIGN KEY (`consecuencia_id`)
    REFERENCES `maruc`.`consecuencias` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.consecuencias
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`consecuencias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` TEXT NOT NULL,
  `tipo_campo` ENUM('texto', 'seleccion') NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `tipo_afectacion_id` INT NULL DEFAULT NULL,
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `tipo_afectacion_id` (`tipo_afectacion_id` ASC) VISIBLE,
  CONSTRAINT `consecuencias_ibfk_1`
    FOREIGN KEY (`tipo_afectacion_id`)
    REFERENCES `maruc`.`tipo_afectacion` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.control_residual
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`control_residual` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `riesgo_residual_id` INT NULL DEFAULT NULL,
  `nombre` VARCHAR(255) NOT NULL,
  `responsable` VARCHAR(255) NOT NULL,
  `tipo_de_control_id` INT NULL DEFAULT NULL,
  `periodicidad_id` INT NULL DEFAULT NULL,
  `indicador` VARCHAR(255) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `riesgo_residual_id` (`riesgo_residual_id` ASC) VISIBLE,
  INDEX `tipo_de_control_id` (`tipo_de_control_id` ASC) VISIBLE,
  INDEX `periodicidad_id` (`periodicidad_id` ASC) VISIBLE,
  CONSTRAINT `control_residual_ibfk_2`
    FOREIGN KEY (`riesgo_residual_id`)
    REFERENCES `maruc`.`riesgo_residual` (`id`),
  CONSTRAINT `control_residual_ibfk_3`
    FOREIGN KEY (`tipo_de_control_id`)
    REFERENCES `maruc`.`tipo_de_control` (`id`),
  CONSTRAINT `control_residual_ibfk_4`
    FOREIGN KEY (`periodicidad_id`)
    REFERENCES `maruc`.`periodicidad` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.controles
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`controles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `responsable` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `riesgo_id` INT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  `causa` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `nombre` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `periodicidad` VARCHAR(100) NULL DEFAULT NULL,
  `tipo_de_control_id` INT NULL DEFAULT NULL,
  `difusion_del_control_id` INT NULL DEFAULT NULL,
  `periodicidad_de_ejecucion` INT NULL DEFAULT NULL,
  `periodicidad_de_seguimiento` INT NULL DEFAULT NULL,
  `cumple_o_ejecuta` BIT(1) NULL DEFAULT NULL,
  `puntaje_tipo_control` DECIMAL(3,2) NULL DEFAULT NULL,
  `puntaje_difusion_control` DECIMAL(3,2) NULL DEFAULT NULL,
  `puntaje_periodicidad_seguimiento` DECIMAL(3,2) NULL DEFAULT NULL,
  `puntaje_periodicidad_ejecucion` DECIMAL(3,2) NULL DEFAULT NULL,
  `puntaje_se_cumple_o_ejecuta` DECIMAL(3,2) NULL DEFAULT NULL,
  `puntaje_ejecucion_sistemas_digitales` DECIMAL(3,2) NULL DEFAULT NULL,
  `ejecucion_sistemas_digitales_id` INT NULL DEFAULT NULL,
  `emplea_o_ejecuta_id` INT NULL DEFAULT NULL,
  `aplica` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `riesgo_id` (`riesgo_id` ASC) VISIBLE,
  INDEX `control_FK` (`tipo_de_control_id` ASC) VISIBLE,
  INDEX `difusion_control_id` (`difusion_del_control_id` ASC) VISIBLE,
  INDEX `periocidad_ejecucion` (`periodicidad_de_ejecucion` ASC) VISIBLE,
  INDEX `control_FK_2` (`ejecucion_sistemas_digitales_id` ASC) VISIBLE,
  INDEX `control_FK_3` (`emplea_o_ejecuta_id` ASC) VISIBLE,
  CONSTRAINT `control_FK`
    FOREIGN KEY (`tipo_de_control_id`)
    REFERENCES `maruc`.`tipo_de_control` (`id`),
  CONSTRAINT `control_FK_1`
    FOREIGN KEY (`difusion_del_control_id`)
    REFERENCES `maruc`.`difusion_control` (`id`),
  CONSTRAINT `control_FK_2`
    FOREIGN KEY (`ejecucion_sistemas_digitales_id`)
    REFERENCES `maruc`.`ejecucion_sistemas_digitales` (`id`),
  CONSTRAINT `control_FK_3`
    FOREIGN KEY (`emplea_o_ejecuta_id`)
    REFERENCES `maruc`.`emplea_o_ejecuta` (`id`),
  CONSTRAINT `controles_ibfk_1`
    FOREIGN KEY (`riesgo_id`)
    REFERENCES `maruc`.`riesgos` (`id`),
  CONSTRAINT `controles_ibfk_2`
    FOREIGN KEY (`difusion_del_control_id`)
    REFERENCES `maruc`.`difusion_control` (`id`),
  CONSTRAINT `controles_ibfk_3`
    FOREIGN KEY (`periodicidad_de_ejecucion`)
    REFERENCES `maruc`.`periodicidad` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.dependencias
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`dependencias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `descripcion` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre` (`nombre` ASC) VISIBLE,
  UNIQUE INDEX `codigo` (`codigo` ASC) VISIBLE);

-- ----------------------------------------------------------------------------
-- Table maruc.difusion_control
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`difusion_control` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `valor` DECIMAL(2,2) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.ejecucion_sistemas_digitales
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`ejecucion_sistemas_digitales` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `valor` DECIMAL(2,2) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.emplea_o_ejecuta
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`emplea_o_ejecuta` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `valor` DECIMAL(2,2) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.estatus_info_riesgo
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`estatus_info_riesgo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `codigo` VARCHAR(5) CHARACTER SET 'utf8' NOT NULL,
  `descripcion` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `valor` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.evaluacion
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`evaluacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `porcentaje_avance` FLOAT NULL DEFAULT '0',
  `observacion` VARCHAR(100) NULL DEFAULT NULL,
  `evidencia_id` INT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_evidencia` (`evidencia_id` ASC) VISIBLE,
  CONSTRAINT `evaluacion_ibfk_1`
    FOREIGN KEY (`evidencia_id`)
    REFERENCES `maruc`.`evidencia` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.evidencia
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`evidencia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `porcentaje_avance_oci` FLOAT NULL DEFAULT '0',
  `control_residual_id` INT NOT NULL,
  `evidencia` VARCHAR(200) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  `porcentaje_avance_opdi` FLOAT NULL DEFAULT '0',
  `cumplimiento_oci` TINYINT(1) NULL DEFAULT '0',
  `cumplimiento_opdi` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `evidencia_FK` (`control_residual_id` ASC) VISIBLE,
  CONSTRAINT `evidencia_FK`
    FOREIGN KEY (`control_residual_id`)
    REFERENCES `maruc`.`control_residual` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.evidencia_observacion
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`evidencia_observacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `observacion_id` INT NOT NULL,
  `evidencia_id` INT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `observacion_id` (`observacion_id` ASC) VISIBLE,
  INDEX `evidencia_observacion_FK` (`evidencia_id` ASC) VISIBLE,
  CONSTRAINT `evidencia_observacion_FK`
    FOREIGN KEY (`evidencia_id`)
    REFERENCES `maruc`.`evidencia` (`id`),
  CONSTRAINT `evidencia_observacion_FK_1`
    FOREIGN KEY (`observacion_id`)
    REFERENCES `maruc`.`observacion` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.monitoreo
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`monitoreo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `porcentaje_avance` FLOAT NULL DEFAULT '0',
  `observacion` VARCHAR(100) NULL DEFAULT NULL,
  `evidencia_id` INT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_evidencia` (`evidencia_id` ASC) VISIBLE,
  CONSTRAINT `monitoreo_ibfk_1`
    FOREIGN KEY (`evidencia_id`)
    REFERENCES `maruc`.`evidencia` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.notificacion
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`notificacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `notificacion` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `enlace` VARCHAR(100) NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `leida` TINYINT(1) NULL DEFAULT NULL,
  `usuario_id` INT NULL DEFAULT NULL,
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `notificacion_FK` (`usuario_id` ASC) VISIBLE,
  CONSTRAINT `notificacion_FK`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `maruc`.`usuarios` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.observacion
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`observacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `observacion` VARCHAR(500) NULL DEFAULT NULL,
  `corregida` TINYINT(1) NULL DEFAULT NULL,
  `tipo_observacion_id` INT NULL DEFAULT NULL,
  `tipo_actor_id` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `tipo_observacion_id` (`tipo_observacion_id` ASC) VISIBLE,
  INDEX `tipo_actor_id` (`tipo_actor_id` ASC) VISIBLE,
  CONSTRAINT `observacion_ibfk_1`
    FOREIGN KEY (`tipo_observacion_id`)
    REFERENCES `maruc`.`tipo_observacion` (`id`),
  CONSTRAINT `observacion_ibfk_2`
    FOREIGN KEY (`tipo_actor_id`)
    REFERENCES `maruc`.`tipo_actor` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.opcion_consecuencia_riesgo
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`opcion_consecuencia_riesgo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `riesgo_id` INT NULL DEFAULT NULL,
  `opcion_id` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `opcion_consecuencia_riesgo_FK` (`riesgo_id` ASC) VISIBLE,
  INDEX `opcion_consecuencia_riesgo_FK_1` (`opcion_id` ASC) VISIBLE,
  CONSTRAINT `opcion_consecuencia_riesgo_FK`
    FOREIGN KEY (`riesgo_id`)
    REFERENCES `maruc`.`riesgos` (`id`),
  CONSTRAINT `opcion_consecuencia_riesgo_FK_1`
    FOREIGN KEY (`opcion_id`)
    REFERENCES `maruc`.`opciones_consecuencias` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.opciones_consecuencias
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`opciones_consecuencias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(150) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `puntaje` INT NOT NULL,
  `seleccionada` BIT(1) NULL DEFAULT b'0',
  `consecuencia_id` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `consecuencia_id` (`consecuencia_id` ASC) VISIBLE,
  CONSTRAINT `opciones_consecuencias_ibfk_1`
    FOREIGN KEY (`consecuencia_id`)
    REFERENCES `maruc`.`consecuencias` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.periodicidad
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`periodicidad` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `valor` DECIMAL(2,2) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.riesgo_observacion
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`riesgo_observacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `riesgo_id` INT NOT NULL,
  `observacion_id` INT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `riesgo_id` (`riesgo_id` ASC) VISIBLE,
  INDEX `observacion_id` (`observacion_id` ASC) VISIBLE,
  CONSTRAINT `riesgo_observacion_ibfk_1`
    FOREIGN KEY (`riesgo_id`)
    REFERENCES `maruc`.`riesgos` (`id`),
  CONSTRAINT `riesgo_observacion_ibfk_2`
    FOREIGN KEY (`observacion_id`)
    REFERENCES `maruc`.`observacion` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.riesgo_residual
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`riesgo_residual` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tratamiento` ENUM('asumir', 'reducir', 'evitar', 'compartir') NULL DEFAULT NULL,
  `riesgo_id` INT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `riesgo_residual_un` (`riesgo_id` ASC) VISIBLE,
  CONSTRAINT `riesgo_residual_ibfk_1`
    FOREIGN KEY (`riesgo_id`)
    REFERENCES `maruc`.`riesgos` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.riesgos
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`riesgos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(250) NOT NULL,
  `relacion_con_objetivo` BIT(1) NULL DEFAULT b'0',
  `objetivo` TEXT NOT NULL,
  `tipo_riesgo_id` INT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  `activo` BIT(1) NULL DEFAULT NULL,
  `motivacion` VARCHAR(100) NULL DEFAULT NULL,
  `responsabilidad` VARCHAR(100) NULL DEFAULT NULL,
  `oportunidad` VARCHAR(100) NULL DEFAULT NULL,
  `tratamiento` VARCHAR(100) NULL DEFAULT NULL,
  `tipo_proceso_id` INT NULL DEFAULT NULL,
  `tipo_tratamiento_id` INT NULL DEFAULT NULL,
  `usuario_id` INT NULL DEFAULT NULL,
  `estatus_info_riesgo_id` INT NOT NULL,
  `mayor_consecuencia` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `tipo_de_riesgo_id` (`tipo_riesgo_id` ASC) VISIBLE,
  INDEX `riesgos_FK` (`tipo_proceso_id` ASC) VISIBLE,
  INDEX `riesgos_FK_1` (`tipo_tratamiento_id` ASC) VISIBLE,
  INDEX `riesgos_FK_2` (`usuario_id` ASC) VISIBLE,
  INDEX `riesgos_FK_3` (`estatus_info_riesgo_id` ASC) VISIBLE,
  CONSTRAINT `riesgos_FK`
    FOREIGN KEY (`tipo_proceso_id`)
    REFERENCES `maruc`.`tipo_proceso` (`id`),
  CONSTRAINT `riesgos_FK_1`
    FOREIGN KEY (`tipo_tratamiento_id`)
    REFERENCES `maruc`.`tipo_tratamiento` (`id`),
  CONSTRAINT `riesgos_FK_2`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `maruc`.`usuarios` (`id`),
  CONSTRAINT `riesgos_FK_3`
    FOREIGN KEY (`estatus_info_riesgo_id`)
    REFERENCES `maruc`.`estatus_info_riesgo` (`id`),
  CONSTRAINT `riesgos_ibfk_1`
    FOREIGN KEY (`tipo_riesgo_id`)
    REFERENCES `maruc`.`tipo_riesgo` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.roles
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`roles` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(25) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre` (`nombre` ASC) VISIBLE,
  UNIQUE INDEX `codigo` (`codigo` ASC) VISIBLE);

-- ----------------------------------------------------------------------------
-- Table maruc.soporte_evidencia
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`soporte_evidencia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `evidencia_id` INT NOT NULL,
  `ruta_soporte` VARCHAR(100) CHARACTER SET 'utf8mb4' NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_evidencia` (`evidencia_id` ASC) VISIBLE,
  CONSTRAINT `soporte_evidencia_ibfk_1`
    FOREIGN KEY (`evidencia_id`)
    REFERENCES `maruc`.`evidencia` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.tipo_actor
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`tipo_actor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `codigo` VARCHAR(5) CHARACTER SET 'utf8' NOT NULL,
  `descripcion` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tipo_actor_UN` (`codigo` ASC) VISIBLE);

-- ----------------------------------------------------------------------------
-- Table maruc.tipo_afectacion
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`tipo_afectacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.tipo_de_control
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`tipo_de_control` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `valor` DECIMAL(2,2) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.tipo_observacion
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`tipo_observacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `codigo` VARCHAR(5) CHARACTER SET 'utf8' NOT NULL,
  `descripcion` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.tipo_proceso
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`tipo_proceso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `nombre` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `codigo` VARCHAR(5) CHARACTER SET 'utf8' NOT NULL,
  `descripcion` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.tipo_riesgo
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`tipo_riesgo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `descripcion` TEXT NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.tipo_tratamiento
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`tipo_tratamiento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) CHARACTER SET 'utf8' NOT NULL,
  `codigo` VARCHAR(5) CHARACTER SET 'utf8' NOT NULL,
  `descripcion` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.usuarios
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombres` VARCHAR(255) NOT NULL,
  `apellidos` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `rol_id` INT NULL DEFAULT NULL,
  `dependencia_id` INT NULL DEFAULT NULL,
  `version` INT NULL DEFAULT NULL,
  `uuid` VARCHAR(36) NULL DEFAULT NULL,
  `verificado` BIT(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email` (`email` ASC) VISIBLE,
  INDEX `rol_id` (`rol_id` ASC) VISIBLE,
  INDEX `depedencia_id` (`dependencia_id` ASC) VISIBLE,
  CONSTRAINT `usuarios_ibfk_1`
    FOREIGN KEY (`rol_id`)
    REFERENCES `maruc`.`roles` (`id`),
  CONSTRAINT `usuarios_ibfk_2`
    FOREIGN KEY (`dependencia_id`)
    REFERENCES `maruc`.`dependencias` (`id`));

-- ----------------------------------------------------------------------------
-- Table maruc.version_riesgo
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `maruc`.`version_riesgo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NOT NULL,
  `ruta_version` VARCHAR(100) CHARACTER SET 'utf8mb4' NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT now(),
  `update_at` TIMESTAMP NULL DEFAULT now(),
  `version` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`));
SET FOREIGN_KEY_CHECKS = 1;
