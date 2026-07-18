-- TODO: 13 - Add seed data for the orders table
--  INSERT INTO orders (description, status, total_amount, customer_email, order_date, internal_notes)
--  VALUES ('Laptop', 'PENDING', 999.99, 'john@mail.com', '2024-01-15', 'rush delivery');
--  Add at least 3 more rows with different statuses

INSERT INTO orders (description, status, total_amount, customer_email, order_date, internal_notes)
VALUES ('Laptop', 'PENDING', 999.99, 'john@mail.com', '2024-01-15', 'rush delivery');

INSERT INTO orders (description, status, total_amount, customer_email, order_date, internal_notes)
VALUES ('Wireless Mouse', 'CONFIRMED', 49.99, 'alice.smith@mail.com', '2024-02-10', 'Gift wrapping requested');

INSERT INTO orders (description, status, total_amount, customer_email, order_date, internal_notes)
VALUES ('Mechanical Keyboard', 'SHIPPED', 129.50, 'michael.brown@mail.com', '2024-03-05', 'Tracking number sent');

INSERT INTO orders (description, status, total_amount, customer_email, order_date, internal_notes)
VALUES ('27-inch Monitor', 'DELIVERED', 349.99, 'sarah.jones@mail.com', '2024-04-18', 'Delivered to front desk');

INSERT INTO orders (description, status, total_amount, customer_email, order_date, internal_notes)
VALUES ('Gaming Headset', 'CANCELLED', 89.95, 'david.wilson@mail.com', '2024-05-02', 'Cancelled due to payment failure');

INSERT INTO orders (description, status, total_amount, customer_email, order_date, internal_notes)
VALUES ('External SSD 1TB', 'PENDING', 159.99, 'emma.taylor@mail.com', '2024-06-21', 'Awaiting inventory restock');