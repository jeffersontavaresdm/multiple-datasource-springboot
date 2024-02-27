DROP TABLE IF EXISTS table_01;

CREATE TABLE table_01
(
    id   SERIAL PRIMARY KEY,
    text VARCHAR(1000) NOT NULL
);

INSERT INTO table_01 (text)
VALUES ('Tutorial: Using multiples data sources');

INSERT INTO table_01 (text)
VALUES ('TEST_T1_01');

INSERT INTO table_01 (text)
VALUES ('TEST_T1_02');