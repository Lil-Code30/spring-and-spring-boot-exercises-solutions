CREATE TABLE  Book (
    id INT IDENTITY  PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100),
    year INT
);

INSERT INTO book (title, author, year) VALUES ('The Great Gatsby', 'F. Scott Fitzgerald', 1925);
INSERT INTO book (title, author, year) VALUES ('To Kill a Mockingbird', 'Harper Lee', 1960);
INSERT INTO book (title, author, year) VALUES ('1984', 'George Orwell', 1949);
