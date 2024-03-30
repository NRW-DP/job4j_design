create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);


INSERT INTO devices (name, price) VALUES
    ('Smartphone', 1099.99),
    ('Laptop', 8422.99),
    ('Tablet', 5999.99);


INSERT INTO people (name) VALUES
    ('Ivan Ivanov'),
    ('Petr Petrov '),
    ('Anna Sidorova');


INSERT INTO devices_people (device_id, people_id) VALUES
    (1, 1), -- The smartphone belongs to Ivanov
    (2, 2), -- The laptop belongs to Petrov 
    (3, 3), -- The tablet belongs to Sidorova
	(1, 3), -- The smartphone belongs to Sidorova
    (2, 2), -- The laptop belongs to Petrov 
    (3, 1); -- The tablet belongs to Ivanov

SELECT * FROM devices;
SELECT * FROM people;
SELECT * FROM devices_people;

-- The average cost of a device
SELECT AVG(price) AS average_price
FROM devices;

-- The average cost of the device per person
SELECT p.name AS person_name, AVG(d.price) AS average_price
FROM people AS p
JOIN devices_people AS dp ON p.id = dp.people_id
JOIN devices AS d ON dp.device_id = d.id
GROUP BY p.name;


SELECT p.name AS person_name, AVG(d.price) AS average_price
FROM people AS p
JOIN devices_people AS dp ON p.id = dp.people_id
JOIN devices AS d ON dp.device_id = d.id
GROUP BY person_name
HAVING AVG(d.price) > 5000;













