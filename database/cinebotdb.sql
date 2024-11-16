SET @OLD_UNIQUE_cHEcKS=@@UNIQUE_cHEcKS, UNIQUE_cHEcKS=0;

SET @OLD_FOREIgN_KEY_cHEcKS=@@FOREIgN_KEY_cHEcKS, FOREIgN_KEY_cHEcKS=0;


-- --------------------------------------------------------
-- ONLY_FULL_gROUP_BY: Requiere que las columnas que no se agreguen en una consulta gROUP BY estén dentro de una función de agregación, asegurando que los resultados sean consistentes.
-- STRIcT_TRANS_TABLES: Hace que las transacciones fallen si se producen errores como la inserción de datos inválidos o la violación de restricciones
-- NO_ZERO_IN_DATE y NO_ZERO_DATE: Impiden la inserción de fechas no válidas, como "0000-00-00"
-- ERROR_FOR_DIVISION_BY_ZERO: genera un error si hay una división por cero en lugar de devolver NULL.
-- NO_ENgINE_SUBSTITUTION: Impide que MySQL sustituya el motor de almacenamiento especificado por otro si el motor predeterminado no está disponible.
-- --------------------------------------------------------

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_gROUP_BY,STRIcT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENgINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema cinebotdb
-- Usar cREATE TABLE Y cREARE ScHEMA, es lo mismo
-- -----------------------------------------------------
cREATE ScHEMA IF NOT ExISTS `cinebotdb` DEFAULT cHARAcTER SET utf8 ;
USE `cinebotdb` ;

-- -----------------------------------------------------
-- Tabla `cinebotdb`.`usuario`
-- -----------------------------------------------------
cREATE TABLE IF NOT ExISTS `cinebotdb`.`usuario` (
  `id` INT NOT NULL AUTO_INcREMENT,
  `nombre` VARcHAR(45) NOT NULL,
  `apellido` VARcHAR(45) NOT NULL,
  `email` VARcHAR(45) NOT NULL,
  `password` VARcHAR(45) NOT NULL,
  `Telefono` VARcHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENgINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `cinebotdb`.`genero`
-- -----------------------------------------------------
cREATE TABLE IF NOT ExISTS `cinebotdb`.`genero` (
  `id` INT NOT NULL AUTO_INcREMENT,
  `nombre` VARcHAR(45)NOT NULL,
  PRIMARY KEY (`id`))
ENgINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `cinebotdb`.`pelicula`
-- -----------------------------------------------------
cREATE TABLE IF NOT ExISTS `cinebotdb`.`pelicula` (
  `id` INT NOT NULL AUTO_INcREMENT,
  `nombre` VARcHAR(45) NOT NULL,
  `genero_id` INT NOT NULL,
  `director` VARcHAR(45) NOT NULL,
  `duracion` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEx `fk_pelicula_genero1_idx` (`genero_id` ASc) VISIBLE,
  cONSTRAINT `fk_pelicula_genero1`
    FOREIgN KEY (`genero_id`)
    REFERENcES `cinebotdb`.`genero` (`id`)
    ON DELETE NO AcTION
    ON UPDATE NO AcTION)
ENgINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `cinebotdb`.`cine`
-- -----------------------------------------------------
cREATE TABLE IF NOT ExISTS `cinebotdb`.`cine` (
  `id` INT NOT NULL AUTO_INcREMENT,
  `nombre` VARcHAR(45) NOT NULL,
  `calle` VARcHAR(45) NOT NULL,
  `numero` VARcHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENgINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinebotdb`.`cartelera`
-- -----------------------------------------------------
cREATE TABLE IF NOT ExISTS `cinebotdb`.`cartelera` (
  `id` INT NOT NULL AUTO_INcREMENT,
  `fecha` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `pelicula_id` INT NOT NULL,
  `cine_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEx `fk_cartelera_pelicula1_idx` (`pelicula_id` ASc) VISIBLE,
  INDEx `fk_cartelera_cine1_idx` (`cine_id` ASc) VISIBLE,
  cONSTRAINT `fk_cartelera_pelicula1`
    FOREIgN KEY (`pelicula_id`)
    REFERENcES `cinebotdb`.`pelicula` (`id`)
    ON DELETE NO AcTION
    ON UPDATE NO AcTION,
  cONSTRAINT `fk_cartelera_cine1`
    FOREIgN KEY (`cine_id`)
    REFERENcES `cinebotdb`.`cine` (`id`)
    ON DELETE NO AcTION
    ON UPDATE NO AcTION)
ENgINE = InnoDB;


-- -----------------------------------------------------
-- Tabla `cinebotdb`.`generoxusuario`
-- -----------------------------------------------------
cREATE TABLE IF NOT ExISTS `cinebotdb`.`generoxusuario` (
  `id` INT NOT NULL AUTO_INcREMENT,
  `usuario_id` INT NOT NULL,
  `genero_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEx `fk_generoxusuario_usuario_idx` (`usuario_id` ASc) VISIBLE,
  INDEx `fk_generoxusuario_genero1_idx` (`genero_id` ASc) VISIBLE,
  cONSTRAINT `fk_generoxusuario_usuario`
    FOREIgN KEY (`usuario_id`)
    REFERENcES `cinebotdb`.`usuario` (`id`)
    ON DELETE NO AcTION
    ON UPDATE NO AcTION,
  cONSTRAINT `fk_generoxusuario_genero1`
    FOREIgN KEY (`genero_id`)
    REFERENcES `cinebotdb`.`genero` (`id`)
    ON DELETE NO AcTION
    ON UPDATE NO AcTION)
ENgINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIgN_KEY_cHEcKS=@OLD_FOREIgN_KEY_cHEcKS;
SET UNIQUE_cHEcKS=@OLD_UNIQUE_cHEcKS;
