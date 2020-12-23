CREATE SCHEMA IF NOT EXISTS GQSHOP;


SET SCHEMA GQSHOP;

--CREATE SCHEMA IF NOT EXISTS `GQSHOP` DEFAULT CHARACTER SET utf8 ;
--USE `mydb` ;

/************************************************/
/* EXCHANGE  */
/************************************************/

--CREATE TABLE GQSHOP.FOODMENU (
--    id            		UUID            NOT NULL,    
--    name          		VARCHAR2(100)   NOT NULL,
--    description      	VARCHAR2(10)    NOT NULL,
--    created_datetime  	datetime
--);

--CREATE TABLE IF NOT EXISTS `GQSHOP`.`FOOD_MENU` (
--  `id` VARCHAR(36) NOT NULL,
--  `id` VARCHAR(36) NOT NULL,
--  `name` VARCHAR(255) NOT NULL,
--  `description` VARCHAR(2048) NULL,
--  PRIMARY KEY (`id`),
--  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
CREATE TABLE IF NOT EXISTS `GQSHOP`.`FOOD_MENU` (
  `id` VARCHAR(36) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(2048) NULL,  
  `image_url` VARCHAR(2048) NULL,
  `idx` INT NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));
  
