

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;


-- --------------------------------------------------------
-- ONLY_FULL_GROUP_BY: Requiere que las columnas que no se agreguen en una consulta GROUP BY estén dentro de una función de agregación, asegurando que los resultados sean consistentes.
-- STRICT_TRANS_TABLES: Hace que las transacciones fallen si se producen errores como la inserción de datos inválidos o la violación de restricciones
-- NO_ZERO_IN_DATE y NO_ZERO_DATE: Impiden la inserción de fechas no válidas, como "0000-00-00"
-- ERROR_FOR_DIVISION_BY_ZERO: Genera un error si hay una división por cero en lugar de devolver NULL.
-- NO_ENGINE_SUBSTITUTION: Impide que MySQL sustituya el motor de almacenamiento especificado por otro si el motor predeterminado no está disponible.
-- --------------------------------------------------------

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema cinebotdb
-- Usar CREATE TABLE Y CREARE SCHEMA, es lo mismo
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinebotdb` DEFAULT CHARACTER SET utf8 ;
USE `cinebotdb` ;

-- -----------------------------------------------------
-- Tabla `cinebotdb`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinebotdb`.`Usuario` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `Telefono` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `cinebotdb`.`Genero`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinebotdb`.`Genero` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45)NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `cinebotdb`.`Pelicula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinebotdb`.`Pelicula` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `Genero_id` INT NOT NULL,
  `director` VARCHAR(45) NOT NULL,
  `duracion` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Pelicula_Genero1_idx` (`Genero_id` ASC) VISIBLE,
  CONSTRAINT `fk_Pelicula_Genero1`
    FOREIGN KEY (`Genero_id`)
    REFERENCES `cinebotdb`.`Genero` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `cinebotdb`.`Cine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinebotdb`.`Cine` (
  `id` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `calle` VARCHAR(45) NOT NULL,
  `numero` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinebotdb`.`Cartelera`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinebotdb`.`Cartelera` (
  `id` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `Carteleracol` VARCHAR(45) NOT NULL,
  `Pelicula_id` INT NOT NULL,
  `Cine_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Cartelera_Pelicula1_idx` (`Pelicula_id` ASC) VISIBLE,
  INDEX `fk_Cartelera_Cine1_idx` (`Cine_id` ASC) VISIBLE,
  CONSTRAINT `fk_Cartelera_Pelicula1`
    FOREIGN KEY (`Pelicula_id`)
    REFERENCES `cinebotdb`.`Pelicula` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cartelera_Cine1`
    FOREIGN KEY (`Cine_id`)
    REFERENCES `cinebotdb`.`Cine` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `cinebotdb`.`GeneroXUsuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinebotdb`.`GeneroXUsuario` (
  `id` INT NOT NULL,
  `Usuario_id` INT NOT NULL,
  `Genero_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_GeneroXUsuario_Usuario_idx` (`Usuario_id` ASC) VISIBLE,
  INDEX `fk_GeneroXUsuario_Genero1_idx` (`Genero_id` ASC) VISIBLE,
  CONSTRAINT `fk_GeneroXUsuario_Usuario`
    FOREIGN KEY (`Usuario_id`)
    REFERENCES `cinebotdb`.`Usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GeneroXUsuario_Genero1`
    FOREIGN KEY (`Genero_id`)
    REFERENCES `cinebotdb`.`Genero` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
