DROP SCHEMA IF EXISTS `gqshop` CASCADE;
CREATE SCHEMA IF NOT EXISTS `gqshop` ;

CREATE TABLE IF NOT EXISTS `gqshop`.`food_menu` (
  `id` VARCHAR(36) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(2048) NULL DEFAULT NULL,
  `image_url` VARCHAR(2048) NULL DEFAULT NULL,
  `idx` INT(11) NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_UNIQUE` (`idx` ASC));

CREATE TABLE IF NOT EXISTS `gqshop`.`orders` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(36) NOT NULL DEFAULT 'todo',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `gqshop`.`orders_has_food_menu` (
  `idx` INT(11) NOT NULL AUTO_INCREMENT,
  `orders_id` INT(11) NOT NULL,
  `food_menu_id` VARCHAR(36) NOT NULL,
  `amount` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idx`));

create INDEX IF NOT EXISTS fk_orders_has_food_menu_food_menu1_idx on gqshop.orders_has_food_menu(food_menu_id);
create INDEX IF NOT EXISTS fk_orders_has_food_menu_orders_idx on gqshop.orders_has_food_menu(orders_id);

ALTER TABLE `GQSHOP`.`ORDERS_HAS_FOOD_MENU` ADD CONSTRAINT  fk_orders_has_food_menu_food_menu1_idx
FOREIGN KEY  (`orders_id`)
REFERENCES `gqshop`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `GQSHOP`.`ORDERS_HAS_FOOD_MENU` ADD CONSTRAINT fk_orders_has_food_menu_orders_idx
    FOREIGN KEY (`food_menu_id`)
    REFERENCES `gqshop`.`food_menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;