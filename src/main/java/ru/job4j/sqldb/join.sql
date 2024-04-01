CREATE TABLE departments (
    department_id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE employees (
    employee_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    department_id INT REFERENCES departments(department_id)
);


INSERT INTO departments (name) VALUES
('Development Department'),
('Marketing Department'),
('Sales Department'),
('Customer Service Department');

INSERT INTO employees (name, department_id) VALUES
('Ivanov', 1),
('Petrov', 1),
('Sidorova', 2),
('Kozlov', 3),
('Smirnova', 2);


SELECT * FROM departments;
SELECT * FROM employees;

-- 2. Execute queries with left, right, full, cross connections

/* LEFT JOIN: Returns all records from the left table (departments), 
   and the corresponding records from the right table (employees), if any.
*/
SELECT departments.name AS department_name, employees.name AS employee_name
FROM departments LEFT JOIN employees ON departments.department_id = employees.department_id;

/* RIGHT JOIN: Returns all records from the right table (employees), 
	and the corresponding records from the left table (departments), if any.
*/
SELECT departments.name AS department_name, employees.name AS employee_name
FROM departments RIGHT JOIN employees ON departments.department_id = employees.department_id;

/* FULL JOIN: Returns all records from both tables, merging them according to the join condition. 
	If there is no corresponding record in one of the tables, the NULL value will be used
*/
SELECT departments.name AS department_name, employees.name AS employee_name
FROM departments FULL JOIN employees ON departments.department_id = employees.department_id;

-- CROSS JOIN: Returns the Cartesian product of all rows from both tables (without condition)
SELECT departments.name AS department_name, employees.name AS employee_name
FROM departments CROSS JOIN employees;

-- 3. Using left join to find departments that do not have employees
SELECT departments.name AS department_name 
FROM departments LEFT JOIN employees ON departments.department_id = employees.department_id
WHERE employees.employee_id IS NULL;

-- 4. Queries that produce the same result using LEFT JOIN and RIGHT JOIN:
SELECT departments.name AS department_name, employees.name AS employee_name
FROM departments LEFT JOIN employees ON departments.department_id = employees.department_id;

SELECT departments.name AS department_name, employees.name AS employee_name
FROM employees RIGHT JOIN departments ON departments.department_id = employees.department_id;


-- 5.

CREATE TABLE teens (
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(10) NOT NULL
);

INSERT INTO teens (name, gender) VALUES
('Vasya', 'male'),
('Masha', 'female'),
('Petya', 'male'),
('Olya', 'female');

SELECT t1.name AS person1, t1.gender AS gender1, t2.name AS person2, t2.gender AS gender2
FROM teens t1 CROSS JOIN teens t2
WHERE t1.name < t2.name AND t1.gender != t2.gender;


