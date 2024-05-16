CREATE TABLE movie
(
    id       SERIAL PRIMARY KEY,
    name     text,
    director text
);

CREATE TABLE book
(
    id     SERIAL PRIMARY KEY,
    title  text,
    author text
);

INSERT INTO movie (name, director)
VALUES ('Марсианин', 'Ридли Скотт'),
       ('Матрица', 'Братья Вачовски'),
       ('Властелин колец', 'Питер Джексон'),
       ('Гарри Поттер и узник Азкабана', 'Альфонсо Куарон'),
       ('Железный человек', 'Джон Фавро');

INSERT INTO book (title, author)
VALUES ('Гарри Поттер и узник Азкабана', 'Джоан Роулинг'),
       ('Властелин колец', 'Джон Толкин'),
       ('1984', 'Джордж Оруэлл'),
       ('Марсианин', 'Энди Уир'),
       ('Божественная комедия', 'Данте Алигьери');
	   
SELECT * FROM movie;
SELECT * FROM book;


-- TASK 1 (выведите названия всех фильмов, которые сняты по книге) 
SELECT name FROM movie
WHERE name IN (SELECT title FROM book)
UNION 
SELECT title FROM book
WHERE title IN (SELECT name FROM movie);

-- TASK 2 (выведите все названия книг, у которых нет экранизации)
SELECT title FROM book
WHERE title NOT IN (SELECT name FROM movie)
EXCEPT
SELECT name FROM movie
WHERE name IN (SELECT title FROM book);

-- TASK 3 (выведите все уникальные названия произведений из таблиц movie и book
-- (т.е фильмы, которые сняты не по книге, и книги без экранизации)) 
SELECT title FROM book
WHERE title NOT IN (SELECT name FROM movie)
UNION
SELECT name FROM movie
WHERE name NOT IN (SELECT title FROM book);
