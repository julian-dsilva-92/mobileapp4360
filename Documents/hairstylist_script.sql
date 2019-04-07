-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mobile_app
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mobile_app
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mobile_app` ;
-- -----------------------------------------------------
-- Schema sys
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sys
-- -----------------------------------------------------
USE `mobile_app` ;

-- -----------------------------------------------------
-- Table `mobile_app`.`customers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mobile_app`.`customers` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `profile_pic` VARCHAR(45) NULL,
  `notes` VARCHAR(45) NULL,
  `ph_number` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`customer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mobile_app`.`hairstylists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mobile_app`.`hairstylists` (
  `hairstylist_id` INT NOT NULL AUTO_INCREMENT,
  `hairstylist_name` VARCHAR(45) NULL,
  `ph_number` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`hairstylist_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mobile_app`.`appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mobile_app`.`appointments` (
  `idappointments` INT NOT NULL AUTO_INCREMENT,
  `time` VARCHAR(45) NULL,
  `date` DATE NULL,
  PRIMARY KEY (`idappointments`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mobile_app`.`hairstyles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mobile_app`.`hairstyles` (
  `hairstyle_ID` INT NOT NULL,
  `hairstyle_color` VARCHAR(45) NULL,
  `date` DATE NULL,
  `hair_products` VARCHAR(45) NULL,
  `hairstyle_notes` VARCHAR(45) NULL,
  PRIMARY KEY (`hairstyle_ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mobile_app`.`hairstyle_hairstylists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mobile_app`.`hairstyle_hairstylists` (
  `idhairstyle_hairstylists` INT NOT NULL AUTO_INCREMENT,
  `hairstyle_ID` INT NULL,
  `hairstylist_id` INT NULL,
  PRIMARY KEY (`idhairstyle_hairstylists`),
  INDEX `customer_hairstylist_1_idx` (`hairstylist_id` ASC) ,
  INDEX `customer_hairstylist_2_idx` (`hairstyle_ID` ASC) ,
  CONSTRAINT `customer_hairstylist_1`
    FOREIGN KEY (`hairstylist_id`)
    REFERENCES `mobile_app`.`hairstylists` (`hairstylist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_hairstylist_2`
    FOREIGN KEY (`hairstyle_ID`)
    REFERENCES `mobile_app`.`hairstyles` (`hairstyle_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mobile_app`.`customer_hairstyles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mobile_app`.`customer_hairstyles` (
  `idcustomer_hairstyles` INT NOT NULL AUTO_INCREMENT,
  `customer_id` INT NULL,
  `hairstyle_ID` INT NULL,
  PRIMARY KEY (`idcustomer_hairstyles`),
  INDEX `customer_hairstyles1_idx` (`customer_id` ASC) ,
  INDEX `customer_hairstyles2_idx` (`hairstyle_ID` ASC) ,
  CONSTRAINT `customer_hairstyles1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `mobile_app`.`customers` (`customer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `customer_hairstyles2`
    FOREIGN KEY (`hairstyle_ID`)
    REFERENCES `mobile_app`.`hairstyles` (`hairstyle_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mobile_app`.`hairstylists_appointments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mobile_app`.`hairstylists_appointments` (
  `idhairstylists_appointments` INT NOT NULL AUTO_INCREMENT,
  `hairstylist_id` INT NULL,
  `idappointment` INT NULL,
  PRIMARY KEY (`idhairstylists_appointments`),
  INDEX `hairstylists_appt1_idx` (`hairstylist_id` ASC) ,
  INDEX `hairstylists_appt2_idx` (`idappointment` ASC) ,
  CONSTRAINT `hairstylists_appt1`
    FOREIGN KEY (`hairstylist_id`)
    REFERENCES `mobile_app`.`hairstylists` (`hairstylist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `hairstylists_appt2`
    FOREIGN KEY (`idappointment`)
    REFERENCES `mobile_app`.`appointments` (`idappointments`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `sys`.`sys_config`
-- -----------------------------------------------------



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
