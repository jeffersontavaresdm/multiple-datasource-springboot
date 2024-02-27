DROP TABLE IF EXISTS table_02;

CREATE TABLE table_02
(
    id         SERIAL PRIMARY KEY,
    text       VARCHAR(250) NOT NULL,
    table01_id BIGINT       NOT NULL
);

INSERT INTO table_02(text, table01_id)
VALUES ('TEST_T2_01', 2);

INSERT INTO table_02(text, table01_id)
VALUES ('TEST_T2_02', 2);

INSERT INTO table_02(text, table01_id)
VALUES ('TEST_T3_03', 3);