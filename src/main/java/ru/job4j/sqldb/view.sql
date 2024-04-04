
CREATE TABLE Authors (
    author_id SERIAL PRIMARY KEY,
    author_name VARCHAR(100)
);

CREATE TABLE Books (
    book_id SERIAL PRIMARY KEY,
    book_title VARCHAR(255),
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES Authors(author_id)
);

CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(100)
);

CREATE TABLE Borrowings (
    borrowing_id SERIAL PRIMARY KEY,
    book_id INT,
    user_id INT,
    borrow_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES Books(book_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);


INSERT INTO Authors (author_name) VALUES 
    ('Alexander Pushkin'),
    ('Leo Tolstoy'),
    ('Fyodor Dostoevsky');

INSERT INTO Books (book_title, author_id) VALUES 
    ('Eugene Onegin', 1),
    ('War and Peace', 2),
    ('Crime and punishment', 3);

INSERT INTO Users (user_name) VALUES 
    ('Ivan Ivanov'),
    ('Petr Petrov'),
    ('Maria Sidorova');

INSERT INTO Borrowings (book_id, user_id, borrow_date, return_date) VALUES 
    (1, 1, '2024-03-01', '2024-03-15'),
    (2, 2, '2024-03-05', '2024-03-20'),
    (3, 3, '2024-03-10', NULL);


-- We get a list of users with dates who have borrowed a certain author 
CREATE VIEW AuthorBorrowings AS
SELECT Users.user_name, Books.book_title, Authors.author_name, Borrowings.borrow_date, Borrowings.return_date
FROM Users
JOIN Borrowings ON Users.user_id = Borrowings.user_id
JOIN Books ON Borrowings.book_id = Books.book_id
JOIN Authors ON Books.author_id = Authors.author_id;

-- Запрос к представлению
SELECT user_name, book_title, borrow_date, return_date
FROM AuthorBorrowings
WHERE author_name = 'Alexander Pushkin';