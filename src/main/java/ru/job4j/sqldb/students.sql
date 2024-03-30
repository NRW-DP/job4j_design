
CREATE TABLE Students (
    student_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    course_id INT
);

CREATE TABLE Courses (
    course_id SERIAL PRIMARY KEY,
    course_name VARCHAR(100),
    teacher VARCHAR(100)
);

INSERT INTO Students (first_name, last_name, course_id)
VALUES
    ('Алекс', 'Иванов', 3),
    ('Елена', 'Петрова', 2),
    ('Иван', 'Сидоров', 1);

INSERT INTO Courses (course_name, teacher)
VALUES
    ('Математика', 'Ковалев'),
    ('Физика', 'Смирнова'),
    ('История', 'Попов');

INSERT INTO Students (course_id)
VALUES
    (3),
    (2),
    (1);

SELECT s.first_name, s.last_name, c.course_name, c.teacher
FROM Students AS s
INNER JOIN Courses AS c ON s.course_id = c.course_id;

SELECT * from Students;
SELECT * from Courses;
