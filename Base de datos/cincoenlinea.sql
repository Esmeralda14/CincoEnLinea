-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cincoenlinea
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cincoenlinea
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cincoenlinea` DEFAULT CHARACTER SET utf8 ;
USE `cincoenlinea` ;

-- -----------------------------------------------------
-- Table `cincoenlinea`.`Jugadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cincoenlinea`.`Jugadores` (
  `usuario` VARCHAR(45) NOT NULL,
  `clave` VARCHAR(64) NOT NULL,
  `puntuacionTotal` INT NULL,
  PRIMARY KEY (`usuario`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
