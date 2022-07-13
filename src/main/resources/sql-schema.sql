drop schema ims;

CREATE SCHEMA IF NOT EXISTS `ims`;
USE `ims`;
CREATE TABLE IF NOT EXISTS `ims`.`customers`
(
   `id` INT (11) NOT NULL AUTO_INCREMENT,
   `first_name` VARCHAR (40) DEFAULT NULL,
   `surname` VARCHAR (40) DEFAULT NULL,
   PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`items`
(
   `id` INT (11) NOT NULL AUTO_INCREMENT,
   `item_name` VARCHAR (40) DEFAULT NULL,
   `price` DECIMAL
   (
      6,
      2
   )
   DEFAULT 0.00,
   PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders`
(
   order_id int UNIQUE AUTO_INCREMENT,
   PRIMARY KEY (order_id),
   fk_customer_id int NOT NULL,
   FOREIGN KEY (fk_customer_id) REFERENCES customers (id)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders_items` (
	ref_id int UNIQUE AUTO_INCREMENT,
	PRIMARY KEY (ref_id),
	orders_id int NOT NULL,
	items_id int NOT NULL,
	quantity int NOT NULL,
	FOREIGN KEY (orders_id) REFERENCES orders (order_id),
	FOREIGN KEY (items_id) REFERENCES items (id)
);