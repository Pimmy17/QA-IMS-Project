DROP TABLE IF EXISTS `customers`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `orders_items`;

CREATE TABLE IF NOT EXISTS `customers` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40) DEFAULT NULL,
    `surname` VARCHAR(40) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `items` (
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
CREATE TABLE IF NOT EXISTS `orders` (
   `order_id` INT (11) NOT NULL UNIQUE AUTO_INCREMENT,
   PRIMARY KEY (`order_id`),
   `fk_customer_id` INT (11) NOT NULL,
   FOREIGN KEY (`fk_customer_id`) REFERENCES `customers` (`id`)
);
CREATE TABLE IF NOT EXISTS `orders_items` (
	`ref_id` INT (11) NOT NULL UNIQUE AUTO_INCREMENT,
	PRIMARY KEY (`ref_id`),
	`orders_id` INT (11) NOT NULL,
	`items_id` INT (11) NOT NULL,
	`quantity` INT (11) NOT NULL,
	FOREIGN KEY (`orders_id`) REFERENCES `orders` (`order_id`),
	FOREIGN KEY (`items_id`) REFERENCES `items` (`id`)
);