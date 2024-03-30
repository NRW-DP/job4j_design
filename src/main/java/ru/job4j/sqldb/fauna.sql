CREATE TABLE fauna (
    id serial PRIMARY KEY,
    name text,
    avg_age int,
    discovery_date date
);

INSERT INTO fauna (name, avg_age, discovery_date) VALUES
    ('Cat', 6000, '2000-05-12'),
    ('Dog', 5000, '1998-08-25'),
    ('Fish', 3000, '2010-11-03'),
    ('Bird', 8000, '1985-02-17'),
    ('Elephant', 20000, '1955-09-30'),
    ('Snake', 4000, NULL),
    ('Horse', 15000, '1965-07-22'),
	('Octopus', 35000, '1912-01-11');

SELECT * FROM fauna;

-- 1) Retrieval of data whose name name contains the substring "Fish"
SELECT * FROM fauna WHERE name LIKE '%Fish%';

-- 2) Extraction of data with life expectancy in the range of 10,000 and 21,000
SELECT * FROM fauna WHERE avg_age BETWEEN 10000 AND 21000;

-- 3) Retrieval of data with no known discovery date - null
SELECT * FROM fauna WHERE discovery_date IS NULL;

-- 4) Retrieval of data from species with a discovery date earlier than 1950
SELECT * FROM fauna WHERE discovery_date < '1950-01-01';
