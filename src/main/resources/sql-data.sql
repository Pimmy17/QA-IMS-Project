INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`items` (`item_name`, `price`) VALUES ('Bop-it', 5.99);
INSERT INTO `ims`.`orders` (`fk_customer_id`) VALUES (1);
INSERT INTO `ims`.`orders_items` (`orders_id`, `items_id`, `quantity`) VALUES (1, 3, 1);