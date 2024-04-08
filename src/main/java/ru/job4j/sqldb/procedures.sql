CREATE TABLE  products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_2', 'producer_2', 15, 32);
call insert_data('product_4', 'producer_4', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);
call insert_data('product_5', 'producer_5', 0, 1);

SELECT * FROM products;

CREATE OR REPLACE PROCEDURE delete_data_by_id(p_id integer)
LANGUAGE 'plpgsql' AS
$$
	BEGIN
		DELETE FROM products WHERE id = p_id;
	END;
$$;

CREATE OR REPLACE FUNCTION delete_products_with_zero_count()
RETURNS void 
LANGUAGE 'plpgsql' AS
$$
	BEGIN
		DELETE FROM products WHERE count = 0;
	END;
$$;


CALL delete_data_by_id(2);
SELECT delete_products_with_zero_count();