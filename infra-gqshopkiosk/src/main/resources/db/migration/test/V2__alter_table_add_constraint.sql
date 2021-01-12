#create INDEX IF NOT EXISTS fk_orders_has_food_menu_food_menu1_idx on gqshop.orders_has_food_menu(food_menu_id);
#create INDEX IF NOT EXISTS fk_orders_has_food_menu_orders_idx on gqshop.orders_has_food_menu(orders_id);

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