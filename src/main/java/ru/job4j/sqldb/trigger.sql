create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

-- TASK 1 - trigger statement level
CREATE OR REPLACE FUNCTION add_tax_statement() 
	RETURNS TRIGGER AS 
$$
	BEGIN
		UPDATE products 
		SET price = price + (price * 0.1) -- +10%
		WHERE id IN (SELECT id FROM products FOR UPDATE);
    	RETURN NEW;
	END;
$$ LANGUAGE plpgsql;

-- after
CREATE TRIGGER add_tax_trigger
AFTER INSERT ON products
EXECUTE FUNCTION add_tax_statement(); 

ALTER TABLE products DISABLE TRIGGER add_tax_trigger;
ALTER TABLE products ENABLE TRIGGER add_tax_trigger;


insert into products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);
SELECT * FROM products;

-- TASK 2 - trigger row level
-- For row-level always uses 'NEW' as we modify the row
CREATE OR REPLACE FUNCTION add_tax_row() 
	RETURNS TRIGGER AS 
$$
	BEGIN
    NEW.price = NEW.price * 1.2; -- +20%
    RETURN NEW;
	END;
$$ LANGUAGE plpgsql;

-- before 
CREATE TRIGGER add_tax_row_trigger
BEFORE INSERT ON products
FOR EACH ROW
EXECUTE FUNCTION add_tax_row(); 

insert into products (name, producer, count, price)
VALUES ('product_2', 'producer_2', 4, 100);
SELECT * FROM products;

-- TASK 3
create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);

CREATE OR REPLACE FUNCTION add_product_to_history()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO history_of_price (name, price, date)
    VALUES (NEW.name, NEW.price, CURRENT_TIMESTAMP);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER add_product_to_history_trigger
AFTER INSERT ON products
FOR EACH ROW
EXECUTE FUNCTION add_product_to_history();

INSERT INTO products (name, price) VALUES ('Product_3', 250);

SELECT * FROM history_of_price;
