CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

-- TASK 1 -- 
INSERT INTO customers (first_name, last_name, age, country) 
VALUES 
    ('Maria', 'Ivanova', 32, 'Ukraine'),
    ('Alisher', 'Aliev', 27, 'Uzbekistan'),
    ('Nurlan', 'Nurlanov', 31, 'Kazakhstan'),
	('Elena', 'Petrova', 29, 'Russia'),
    ('Arman', 'Tulepov', 34, 'Kazakhstan');

SELECT * FROM customers;

SELECT * FROM customers 
WHERE age = (SELECT MIN(age) FROM customers);

-- TASK 2
CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders (amount, customer_id) 
VALUES 
    (100, 1),
    (150, 3), 
    (200, 2); 
	
SELECT * FROM orders;

SELECT * FROM customers 
WHERE id NOT IN (SELECT customer_id FROM orders);

