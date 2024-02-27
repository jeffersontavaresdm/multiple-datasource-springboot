DROP TABLE IF EXISTS comment;

CREATE TABLE comment
(
    id      SERIAL PRIMARY KEY,
    text    VARCHAR(1000) NOT NULL,
    post_id BIGINT        NOT NULL
);

INSERT INTO comment(text, post_id)
VALUES ('TEST 01', 2);

INSERT INTO comment(text, post_id)
VALUES ('TEST 02', 2);

INSERT INTO comment(text, post_id)
VALUES ('TEST 03', 3);