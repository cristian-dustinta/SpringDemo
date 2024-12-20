-- Create the 'library' table
CREATE TABLE library (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create the 'book' table with a foreign key reference to the 'library' table
CREATE TABLE book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    library_id BIGINT,
    FOREIGN KEY (library_id) REFERENCES library(id)
);