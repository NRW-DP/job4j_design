create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price int
);

insert into type(name) 
values ('СЫР'), ('МОЛОКО'), ('КОЛБАСА'), ('ШОКОЛАД');

insert into product(name, type_id, expired_date, price)
values ('Российский', 1, date '2022-08-10', 650);
insert into product(name, type_id, expired_date, price)
values ('Гауда', 1, date '2022-08-24', 856);
insert into product(name, type_id, expired_date, price)
values ('Камамбер', 1, date '2022-08-14', 932);
insert into product(name, type_id, expired_date, price)
values ('Стародубский', 1, date '2022-08-07', 558);
insert into product(name, type_id, expired_date, price)
values ('Мороженое', 2, date '2022-08-13', 77);
insert into product(name, type_id, expired_date, price)
values ('Сметана', 2, date '2022-08-07', 97);
insert into product(name, type_id, expired_date, price)
values ('Кефир', 2, date '2022-08-12', 67);
insert into product(name, type_id, expired_date, price)
values ('Столичная', 3, date '2022-08-02', 487);
insert into product(name, type_id, expired_date, price)
values ('Докторская', 3, date '2022-08-12', 312);
insert into product(name, type_id, expired_date, price)
values ('Охотничья', 3, date '2022-08-21', 397);
insert into product(name, type_id, expired_date, price)
values ('Аленка', 4, date '2022-08-14', 75);
insert into product(name, type_id, expired_date, price)
values ('Dove', 4, date '2022-08-03', 98);
insert into product(name, type_id, expired_date, price)
values ('Milka', 4, date '2022-08-16', 82);

SELECT * FROM type;
SELECT * FROM product;

-- 1. Write a query to get all products with the type "СЫР"
SELECT * FROM product WHERE type_id = (SELECT id FROM type WHERE name = 'СЫР');

-- 2. Write a query to retrieve all products that have the word "Мороженое"
SELECT * FROM product WHERE name LIKE '%Mороженое%';

-- 3. Write a query that retrieves all products that have already expired
SELECT * FROM product WHERE expired_date < CURRENT_DATE;

-- 4. Output of the most expensive product
SELECT * FROM product WHERE price = (SELECT MAX(price) FROM product);

-- 5. Write a query that outputs for each type the number of products belonging to it. 
-- In the form name_type, quantity
SELECT type.name AS name_type, COUNT(product.id) AS quantity
FROM product
JOIN type ON product.type_id = type.id
GROUP BY type.name;

-- 6. Write a query to get all products with type "СЫР" and "МОЛОКО"
SELECT * FROM product 
WHERE type_id IN (SELECT id FROM type WHERE name IN ('СЫР', 'МОЛОКО'));

-- 7. Output of product types that have less than 10 pieces left:
SELECT t.name, count(t.name) FROM product AS p
JOIN type AS t ON p.type_id= t.id
GROUP BY t.name
HAVING COUNT(t.name) < 10;

-- 8. Output all products and their type.
SELECT product.name, type.name AS typeOfProduct FROM product
JOIN type ON product.type_id = type.id;

