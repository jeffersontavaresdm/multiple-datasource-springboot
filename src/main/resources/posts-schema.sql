DROP TABLE IF EXISTS post;

CREATE TABLE post
(
    id   SERIAL PRIMARY KEY,
    text VARCHAR(1000) NOT NULL
);

INSERT INTO post (text)
VALUES ('Tutorial: How to use multiples data sources');

INSERT INTO post (text)
VALUES ('TEST POST 01');

INSERT INTO post (text)
VALUES ('TEST POST 02');