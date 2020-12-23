SET SCHEMA GQSHOP;

INSERT INTO `GQSHOP`.`FOOD_MENU` (id,name,description,image_url,created_at) VALUES (UUID(),'Big Mac','Big Mac signature burger','/images/food_menu/t-mcdonalds-Big-Mac.jpg',CURRENT_TIMESTAMP);
INSERT INTO `GQSHOP`.`FOOD_MENU` (id,name,description,image_url,created_at) VALUES (UUID(),'Cheeseburger','Our simple, classic cheeseburger ','/images/food_menu/t-mcdonalds-Cheeseburger.jpg',CURRENT_TIMESTAMP);
INSERT INTO `GQSHOP`.`FOOD_MENU` (id,name,description,image_url,created_at) VALUES (UUID(),'Coca-Cola','Refreshing McDonald''s soda option','/images/food_menu/t-mcdonalds-Coca-Cola-Classic-Small.jpg',CURRENT_TIMESTAMP);

