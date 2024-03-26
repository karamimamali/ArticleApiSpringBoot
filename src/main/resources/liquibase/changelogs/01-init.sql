-- Create Author Table
CREATE TABLE AUTHOR_ENTITY (
                              id BIGSERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              surname VARCHAR(255) NOT NULL,
                              is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- Create Article Table
CREATE TABLE ARTICLE_ENTITY (
                               id BIGSERIAL PRIMARY KEY,
                               title VARCHAR(255) NOT NULL,
                               content TEXT NOT NULL,
                               is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
                               author_id BIGINT,
                               FOREIGN KEY (author_id) REFERENCES AUTHOR_ENTITY(id)
);
