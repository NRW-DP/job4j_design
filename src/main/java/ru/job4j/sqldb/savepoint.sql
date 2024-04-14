
CREATE TABLE my_table (
    id SERIAL PRIMARY KEY,
    data TEXT
);

BEGIN TRANSACTION;
INSERT INTO my_table (data) VALUES ('Data 1');

SAVEPOINT savepoint_1;
INSERT INTO my_table (data) VALUES ('Data 2');
ROLLBACK TO SAVEPOINT savepoint_1;

SAVEPOINT savepoint_2;
INSERT INTO my_table (data) VALUES ('Data 3');
INSERT INTO my_table (data) VALUES ('Data 4');

COMMIT;
SELECT * FROM my_table;