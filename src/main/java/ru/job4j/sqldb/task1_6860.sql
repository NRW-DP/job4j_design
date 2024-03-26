CREATE TABLE CompanyEmployees (
    employee_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100),
    position VARCHAR(50),
    date_of_birth DATE,
    salary DECIMAL(10, 2)
);

INSERT INTO CompanyEmployees (full_name, position, date_of_birth, salary) 
VALUES ('Anna Johnson', 'Marketing Manager', '1985-09-20', 65000.00);

SELECT * FROM CompanyEmployees; 

UPDATE CompanyEmployees
SET salary = 70000.00
WHERE full_name = 'Anna Johnson';

SELECT * FROM CompanyEmployees;

DELETE FROM CompanyEmployees;

SELECT * FROM CompanyEmployees;