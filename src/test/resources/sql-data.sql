INSERT INTO `customers` (`first_name`, `surname`) VALUES ('jordan', 'harrison');
INSERT INTO `items` (`item_name`, `price`) VALUES ('Bop-it', 5.99), ('Hungry Hippos', 8.45);
INSERT INTO `orders` (`fk_customer_id`) VALUES (1);
INSERT INTO `orders_items` (`orders_id`, `items_id`, `quantity`) VALUES (1, 1, 1);