INSERT INTO `ims`.`customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `ims`.`items` (`item_name`, `price`) VALUES ('Bop-it', 5.99);
INSERT INTO `ims`.`orders` (`fk_customer_id`, `fk_item_id`, `purchase_date`, `quantity`) VALUES (1, 1, `2022-05-01`, 1);