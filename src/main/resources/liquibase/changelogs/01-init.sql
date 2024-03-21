-- Create Author Table
CREATE TABLE author (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        surname VARCHAR(255) NOT NULL,
                        is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- Create Article Table
CREATE TABLE article (
                         id SERIAL PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         content VARCHAR(255) NOT NULL,
                         is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
                         author_id BIGINT,
                         FOREIGN KEY (author_id) REFERENCES author(id) ON DELETE SET NULL ON UPDATE CASCADE
);